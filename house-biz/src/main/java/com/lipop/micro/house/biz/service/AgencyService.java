package com.lipop.micro.house.biz.service;

import java.util.List;

import com.lipop.micro.house.biz.dao.AgencyMapper;
import com.lipop.micro.house.biz.dao.UserMapper;
import com.lipop.micro.house.common.model.Agency;
import com.lipop.micro.house.common.model.User;
import com.lipop.micro.house.common.page.PageData;
import com.lipop.micro.house.common.page.PageParams;
import com.lipop.micro.house.common.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AgencyService {

  @Resource
  private AgencyMapper agencyMapper;

  @Resource
  private UserMapper userMapper;

  @Value("${file.prefix}")
  private String imgPrefix;

  /**
   * 访问user表获取详情 添加用户头像
   * 
   * @param userId
   * @return
   */
  public UserVo getAgentDeail(Long userId) {
    User user = new User();
    user.setId(userId);
    user.setType(2);
    List<User> list = userMapper.selectUserAgentListForPage(user, PageParams.build(1, 1));
    setImg(list);
    if (!list.isEmpty()) {
      User agent = list.get(0);
      //将经纪人关联的经纪机构也一并查询出来
      Agency agency = new Agency();
      agency.setId(agent.getAgencyId().intValue());
      List<Agency> agencies = agencyMapper.selectAgencyList(agency);
      if (!agencies.isEmpty()) {
          agent.setName(agencies.get(0).getName());
      }
      UserVo userVo = new UserVo();
      BeanUtils.copyProperties(agency, userVo);
      return userVo;
    }
    return null;
  }

  private void setImg(List<User> list) {
    for (User user:
         list) {
      user.setAvatar(imgPrefix + user.getAvatar());
    }

  }
  public PageData<User> getAllAgent(PageParams pageParams) {
    List<User> agents = userMapper.selectUserAgentListForPage(new User(), pageParams);
    setImg(agents);
    Long count = userMapper.selectUserAgentCount(new User());
    return PageData.buildPage(agents, count, pageParams.getPageSize(), pageParams.getPageNum());
  }
  public Agency getAgency(Integer id) {
    return agencyMapper.selectByPrimaryKey(id);
  }

  public List<Agency> getAllAgency() {
    return agencyMapper.selectAgencyList(new Agency());
  }
  public int add(Agency agency) {
    return  agencyMapper.insert(agency);
  }

  public void sendMsg(User agent, String msg, String name, String email) {
    // TODO Auto-generated method stub
    
  }

}
