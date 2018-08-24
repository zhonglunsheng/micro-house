package com.lipop.micro.house.common.model;

import java.util.Date;

public class User {
    private Long id;

    private String name;

    private String phone;

    private String email;

    private String aboutme;

    private String passwd;

    private String avatar;

    private Integer type;

    private Date createTime;

    private Integer enable;

    private Integer agencyId;

    private String confirmPasswd;

    private String Key;

    public User(Long id, String name, String phone, String email, String aboutme, String passwd, String avatar, Integer type, Date createTime, Integer enable, Integer agencyId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.aboutme = aboutme;
        this.passwd = passwd;
        this.avatar = avatar;
        this.type = type;
        this.createTime = createTime;
        this.enable = enable;
        this.agencyId = agencyId;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getConfirmPasswd() {
        return confirmPasswd;
    }

    public void setConfirmPasswd(String confirmPasswd) {
        confirmPasswd = confirmPasswd;
    }

    public User() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme == null ? null : aboutme.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }
}