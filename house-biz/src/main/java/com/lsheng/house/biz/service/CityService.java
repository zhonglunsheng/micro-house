package com.lsheng.house.biz.service;

import java.util.List;

import com.lsheng.house.common.model.City;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class CityService {
    /**
     * 获取城市列表
     *
     * @return
     */
    public List<City> getAllCitys() {
        City city = new City();
        city.setCityCode("110000");
        city.setCityName("北京");
        city.setId(1);
        return Lists.newArrayList(city);
    }

}
