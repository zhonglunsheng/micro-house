package com.lipop.microhouse.model;

import java.util.Date;

public class HouseMsg {
    private Long id;

    private String msg;

    private Date createTime;

    private Long agentId;

    private Long houseId;

    private String userName;

    public HouseMsg(Long id, String msg, Date createTime, Long agentId, Long houseId, String userName) {
        this.id = id;
        this.msg = msg;
        this.createTime = createTime;
        this.agentId = agentId;
        this.houseId = houseId;
        this.userName = userName;
    }

    public HouseMsg() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}