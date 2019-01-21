package com.lsheng.house.hsrv.controller;

import java.util.List;

import com.lsheng.house.hsrv.service.HouseService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Objects;
import com.lsheng.house.hsrv.common.CommonConstants;
import com.lsheng.house.hsrv.common.HouseUserType;
import com.lsheng.house.hsrv.common.LimitOffset;
import com.lsheng.house.hsrv.common.RestCode;
import com.lsheng.house.hsrv.common.RestResponse;
import com.lsheng.house.hsrv.model.City;
import com.lsheng.house.hsrv.model.Community;
import com.lsheng.house.hsrv.model.House;
import com.lsheng.house.hsrv.model.HouseQueryReq;
import com.lsheng.house.hsrv.model.HouseUserReq;
import com.lsheng.house.hsrv.model.ListResponse;
import com.lsheng.house.hsrv.model.UserMsg;
import com.lsheng.house.hsrv.service.RecommendService;

@RequestMapping("house")
@RestController
public class HouseController {
  
  @Autowired
  private HouseService houseService;
  
  @Autowired
  private RecommendService recommendService;

  @RequestMapping("list")
  public RestResponse<ListResponse<House>> houseList(@RequestBody HouseQueryReq req){
    Integer limit  = req.getLimit();
    Integer offset = req.getOffset();
    House query  = req.getQuery();
    Pair<List<House>, Long> pair = houseService.queryHouse(query, LimitOffset.build(limit, offset));
    return RestResponse.success(ListResponse.build(pair.getKey(), pair.getValue()));
  }
  
  @RequestMapping("detail")
  public RestResponse<House> houseDetail(long id){
    House house = houseService.queryOneHouse(id);
    recommendService.increaseHot(id);
    return RestResponse.success(house);
  }
  
  @RequestMapping("addUserMsg")
  public RestResponse<Object> houseMsg(@RequestBody UserMsg userMsg){
    houseService.addUserMsg(userMsg);
    return RestResponse.success();
  }
  
  @ResponseBody
  @RequestMapping("rating")
  public RestResponse<Object> houseRate(Double rating,Long id){
    houseService.updateRating(id,rating);
    return RestResponse.success();
  }
  
  
  @RequestMapping("allCommunitys")
  public RestResponse<List<Community>> toAdd(){
    List<Community> list = houseService.getAllCommunitys();
    return RestResponse.success(list);
  }
  
  @RequestMapping("allCitys")
  public RestResponse<List<City>> allCitys(){
    List<City> list = houseService.getAllCitys();
    return RestResponse.success(list);
  }
  
  /**
   * 房产新增
   * @param house
   * @return
   */
  @RequestMapping("add")
  public RestResponse<Object> doAdd(@RequestBody House house){
    house.setState(CommonConstants.HOUSE_STATE_UP);
    if (house.getUserId() == null) {
      return RestResponse.error(RestCode.ILLEGAL_PARAMS);
    }
    houseService.addHouse(house,house.getUserId());
    return RestResponse.success();
  }
  
  
  @RequestMapping("bind")
  public RestResponse<Object> delsale(@RequestBody HouseUserReq req){
    Integer bindType = req.getBindType();
    HouseUserType houseUserType = Objects.equal(bindType, 1) ? HouseUserType.SALE : HouseUserType.BOOKMARK;
    if (req.isUnBind()) {
      houseService.unbindUser2Houser(req.getHouseId(),req.getUserId(),houseUserType);
    }else {
      houseService.bindUser2House(req.getHouseId(), req.getUserId(), houseUserType);
    }
    return RestResponse.success();
  }
  
  @RequestMapping("hot")
  public RestResponse<List<House>> getHotHouse(Integer size){
   List<House> list =   recommendService.getHotHouse(size);
    return RestResponse.success(list);
  }
  
  @RequestMapping("lastest")
  public RestResponse<List<House>> getLastest(){
    return RestResponse.success(recommendService.getLastest());
  }
  
}
