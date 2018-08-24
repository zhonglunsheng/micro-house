package com.lipop.micro.house.biz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lipop.micro.house.biz.dao.CommunityMapper;
import com.lipop.micro.house.biz.dao.HouseMapper;
import com.lipop.micro.house.common.common.HouseUserType;
import com.lipop.micro.house.common.model.*;
import com.lipop.micro.house.common.page.PageData;
import com.lipop.micro.house.common.page.PageParams;
import com.lipop.micro.house.common.utils.BeanHelper;
import com.lipop.micro.house.common.vo.HouseMsgVo;
import com.lipop.micro.house.common.vo.HouseVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;


@Service
public class HouseService {
	
	@Autowired
	private HouseMapper houseMapper;

	@Autowired
	private CommunityMapper communityMapper;
	
	@Value("${file.prefix}")
	private String imgPrefix;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private AgencyService agencyService;
	
	@Autowired
	private MailService mailService;
	

	/**
	 * 1.查询小区
	 * 2.添加图片服务器地址前缀
	 * 3.构建分页结果
	 * @param query
	 * @param pageParams
	 */
	public PageData<HouseVo> queryHouse(HouseVo query, PageParams pageParams) {
		List<HouseVo> houses = Lists.newArrayList();
		if (!Strings.isNullOrEmpty(query.getName())) {
			Community community = new Community();
			community.setName(query.getName());
			List<Community> communities = communityMapper.selectCommunityList(community);
			if (!communities.isEmpty()) {
				query.setCommunityId(communities.get(0).getId());
			}
		}
		houses = queryAndSetImg(query,pageParams);//添加图片服务器地址前缀
		Long count = communityMapper.selectCommunityPageCount(query);
		return PageData.buildPage(houses, count, pageParams.getPageSize(), pageParams.getPageNum());
	}

	public List<HouseVo> queryAndSetImg(HouseVo query, PageParams pageParams) {
		List<House> houseList =   houseMapper.selectPageHouses(query, pageParams);
		List<HouseVo> houseVos = new ArrayList<>();
		houseList.forEach(h ->{
			HouseVo houseVo = new HouseVo();
			BeanUtils.copyProperties(h, houseVo);
			houseVo.setFirstImg(imgPrefix + houseVo.getFirstImg());
			String image[] = houseVo.getImages().split(",");
			List<String> imageList = Lists.newArrayList();
			for (int i = 0; i < image.length; i++) {
				imageList.add(imgPrefix + image[i]);
			}
			List<String> floorPlanList = Lists.newArrayList();
			String floorPlan[] = houseVo.getFloorPlan().split(",");
			for (int i = 0; i < floorPlan.length; i++) {
				floorPlanList.add(imgPrefix + floorPlan[i]);
			}
			houseVo.setImageList(imageList);
			houseVo.setFloorPlanList(floorPlanList);
			houseVos.add(houseVo);
		});
		return houseVos;
	}
	
	public List<Community> getAllCommunitys() {
		Community community = new Community();
		return communityMapper.selectCommunityList(community);
	}

	/**
	 * 添加房屋图片
	 * 添加户型图片
	 * 插入房产信息
	 * 绑定用户到房产的关系
	 * @param house
	 * @param user
	 */
	public void addHouse(HouseVo house, User user) {
		if (CollectionUtils.isNotEmpty(house.getHouseFiles())) {
			String images = Joiner.on(",").join(fileService.getImgPaths(house.getHouseFiles()));
		    house.setImages(images);
		}
		if (CollectionUtils.isNotEmpty(house.getFloorPlanFiles())) {
			String images = Joiner.on(",").join(fileService.getImgPaths(house.getFloorPlanFiles()));
		    house.setFloorPlan(images);
		}
		BeanHelper.onInsert(house);
		houseMapper.insert(house);
		bindUser2House(house.getId(),user.getId(),false);
	}

	public void bindUser2House(Long houseId, Long userId, boolean collect) {
      HouseUser existhouseUser =  houseMapper.selectHouseUser(userId,houseId,collect ? HouseUserType.BOOKMARK.value : HouseUserType.SALE.value);
	  if (existhouseUser != null) {
		  return;
	  }
	  HouseUser houseUser = new HouseUser();
	  houseUser.setHouseId(houseId);
	  houseUser.setUserId(userId);
	  houseUser.setType(collect ? HouseUserType.BOOKMARK.value : HouseUserType.SALE.value);
	  BeanHelper.setDefaultProp(houseUser, HouseUser.class);
	  BeanHelper.onInsert(houseUser);
	  houseMapper.insertHouseUser(houseUser);
	}

	public HouseUser getHouseUser(Long houseId){
		HouseUser houseUser =  houseMapper.selectSaleHouseUser(houseId);
		return houseUser;
	}
	
	public HouseVo queryOneHouse(Long id) {
		HouseVo query = new HouseVo();
		query.setId(id);
		List<HouseVo> houses = queryAndSetImg(query, PageParams.build(1, 1));
		if (!houses.isEmpty()) {
			return houses.get(0);
		}
		return null;
	}

	public void addUserMsg(HouseMsgVo userMsg) {
        BeanHelper.onInsert(userMsg);
        houseMapper.insertUserMsg(userMsg);
        User agent = agencyService.getAgentDeail(userMsg.getAgentId());
        mailService.sendMail("来自用户"+userMsg.getEmail()+"的留言", userMsg.getMsg(), agent.getEmail());
	}

	public void updateRating(Long id, Double rating) {
		House house = queryOneHouse(id);
		Double oldRating = house.getRating();
		Double newRating  = oldRating.equals(0D)? rating : Math.min((oldRating+rating)/2, 5);
		House updateHouse = new House();
		updateHouse.setId(id);
		updateHouse.setRating(newRating);
		BeanHelper.onUpdate(updateHouse);
		houseMapper.updateByPrimaryKeySelective(updateHouse);
	}

	public void unbindUser2House(Long id, Long userId, HouseUserType type) {
	  if (type.equals(HouseUserType.SALE)) {
	      houseMapper.downHouse(id);
	    }else {
	      houseMapper.deleteHouseUser(id, userId, type.value);
	    }
	    
	}

}
