package com.lipop.microhouse.model;

import java.util.Date;

public class House {
    private Long id;

    private String name;

    private Boolean type;

    private Integer price;

    private String images;

    private Integer area;

    private Integer beds;

    private Integer baths;

    private Double rating;

    private String remarks;

    private String properties;

    private String floorPlan;

    private String tags;

    private Date createTime;

    private Integer cityId;

    private Integer communityId;

    private String address;

    private Boolean state;

    public House(Long id, String name, Boolean type, Integer price, String images, Integer area, Integer beds, Integer baths, Double rating, String remarks, String properties, String floorPlan, String tags, Date createTime, Integer cityId, Integer communityId, String address, Boolean state) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.images = images;
        this.area = area;
        this.beds = beds;
        this.baths = baths;
        this.rating = rating;
        this.remarks = remarks;
        this.properties = properties;
        this.floorPlan = floorPlan;
        this.tags = tags;
        this.createTime = createTime;
        this.cityId = cityId;
        this.communityId = communityId;
        this.address = address;
        this.state = state;
    }

    public House() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getBaths() {
        return baths;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

    public String getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan == null ? null : floorPlan.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}