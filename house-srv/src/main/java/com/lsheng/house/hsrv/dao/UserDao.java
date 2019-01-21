package com.lsheng.house.hsrv.dao;

import com.lsheng.house.hsrv.common.RestResponse;
import com.lsheng.house.hsrv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.lsheng.house.hsrv.service.GenericRest;
import com.lsheng.house.hsrv.utils.Rests;

@Repository
public class UserDao {

  @Autowired
  private GenericRest rest;
  
  @Value("${user.service.name}")
  private String userServiceName;

  public User getAgentDetail(Long agentId) {
   RestResponse<User> response = Rests.exc(() -> {
      String url = Rests.toUrl(userServiceName, "/agency/agentDetail" + "?id=" + agentId);
      ResponseEntity<RestResponse<User>> responseEntity = rest.get(url, new ParameterizedTypeReference<RestResponse<User>>() {});
      return responseEntity.getBody();
    });
   return response.getResult();
  }


  
  
}
