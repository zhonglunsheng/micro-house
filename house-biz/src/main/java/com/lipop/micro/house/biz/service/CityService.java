package com.lipop.micro.house.biz.service;

import java.util.List;

import com.lipop.micro.house.common.model.City;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class CityService {
  
  public List<City> getAllCitys(){
    City city = new City();
    city.setCityCode("110000");
    city.setCityName("北京");
    city.setId(1);
    return Lists.newArrayList(city);
  }

}