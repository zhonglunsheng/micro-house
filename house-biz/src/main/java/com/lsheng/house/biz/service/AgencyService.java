package com.lsheng.house.biz.service;

import java.util.List;

import com.lsheng.house.biz.mapper.AgencyMapper;
import com.lsheng.house.common.model.Agency;
import com.lsheng.house.common.model.User;
import com.lsheng.house.common.page.PageData;
import com.lsheng.house.common.page.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {

    @Autowired
    private AgencyMapper agencyMapper;

    @Value("${file.prefix}")
    private String imgPrefix;

    /**
     * 获取经纪人及相应经纪机构详情
     *
     * @param userId
     * @return
     */
    public User getAgentDeail(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setType(2);
        List<User> list = agencyMapper.selectAgent(user, PageParams.build(1, 1));
        setImg(list);
        if (!list.isEmpty()) {
            User agent = list.get(0);
            //将经纪人关联的经纪机构也一并查询出来
            Agency agency = new Agency();
            agency.setId(agent.getAgencyId().intValue());
            List<Agency> agencies = agencyMapper.select(agency);
            if (!agencies.isEmpty()) {
                agent.setAgencyName(agencies.get(0).getName());
            }
            return agent;
        }
        return null;
    }

    private void setImg(List<User> list) {
        list.forEach(i -> {
            i.setAvatar(imgPrefix + i.getAvatar());
        });

    }

    /**
     * 获取经济人分页列表
     *
     * @param pageParams
     * @return
     */
    public PageData<User> getAllAgent(PageParams pageParams) {
        List<User> agents = agencyMapper.selectAgent(new User(), pageParams);
        setImg(agents);
        Long count = agencyMapper.selectAgentCount(new User());
        return PageData.buildPage(agents, count, pageParams.getPageSize(), pageParams.getPageNum());
    }

    /**
     * 获取经济人详情
     *
     * @param id
     * @return
     */
    public Agency getAgency(Integer id) {
        Agency agency = new Agency();
        agency.setId(id);
        List<Agency> agencies = agencyMapper.select(agency);
        if (agencies.isEmpty()) {
            return null;
        }
        return agencies.get(0);
    }

    /**
     * 获取所有经济机构
     *
     * @return
     */
    public List<Agency> getAllAgency() {
        return agencyMapper.select(new Agency());
    }

    /**
     * 添加经济机构
     *
     * @param agency
     * @return
     */
    public int add(Agency agency) {
        return agencyMapper.insert(agency);
    }

    /**
     * 邮件发送
     *
     * @param agent
     * @param msg
     * @param name
     * @param email
     */
    public void sendMsg(User agent, String msg, String name, String email) {
        // TODO Auto-generated method stub

    }

}
