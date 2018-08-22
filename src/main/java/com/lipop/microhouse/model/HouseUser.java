package com.lipop.microhouse.model;

import java.util.Date;

public class HouseUser {
    private Long id;

    private Long houseId;

    private Long userId;

    private Date createTime;

    private Boolean type;

    public HouseUser(Long id, Long houseId, Long userId, Date createTime, Boolean type) {
        this.id = id;
        this.houseId = houseId;
        this.userId = userId;
        this.createTime = createTime;
        this.type = type;
    }

    public HouseUser() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}