package com.lipop.micro.house.common.model;

public class Community {
    private Integer id;

    private String cityCode;

    private String name;

    private String cityName;

    public Community(Integer id, String cityCode, String name, String cityName) {
        this.id = id;
        this.cityCode = cityCode;
        this.name = name;
        this.cityName = cityName;
    }

    public Community() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }
}