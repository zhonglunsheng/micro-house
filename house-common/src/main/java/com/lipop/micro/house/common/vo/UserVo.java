package com.lipop.micro.house.common.vo;

import com.lipop.micro.house.common.model.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * 描述:
 * ${DESCRIPTION}
 *
 * @author zhonglunsheng
 * @create 2018-08-23 19:18
 */
public class UserVo extends User {

    private MultipartFile AvatarFile;
    private String agencyName;

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public MultipartFile getAvatarFile() {
        return AvatarFile;
    }

    public void setAvatarFile(MultipartFile avatarFile) {
        AvatarFile = avatarFile;
    }
}
