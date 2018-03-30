/**
 * Copyright (c) 2014-2018  墨博云舟 All Rights Reserved.
 */
package com.yb.chat.service.impl;

import com.google.common.base.Strings;

import com.github.pagehelper.PageInfo;
import com.yb.chat.client.UserServiceClient;
import com.yb.chat.client.response.CountUserResp;
import com.yb.chat.client.response.LogResp;
import com.yb.chat.client.response.UserResp;
import com.yb.chat.resp.OnLineTimeResp;
import com.yb.chat.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

/**
 * AdminServiceImpl:
 *
 * @author yangbo
 * @version 1.00
 * @since 2018/3/26 0026 11:34
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public List findOnline() {
        return (List) userServiceClient.online();
    }

    @Override
    public Object findAllUser(int c, int p) {
        return  userServiceClient.allUser(c, p);
    }
    /**
     * 查询最后登录时间
     * @param name 用户名
     *
     * @return 时间戳
     */
    @Override
    public Object userLastLoginTime(String name) {
        return userServiceClient.lastLoginTime(name);
    }

    @Override
    public Object log(String name, int c, int p, String sort) {
        if(Strings.isNullOrEmpty(name)) {
            name = "";
        }
        if(Strings.isNullOrEmpty(sort)) {
            sort = "";
        }
        PageInfo<LogResp> log = userServiceClient.log(name, c, p, sort);
        return log;
    }

    /**
     * 计算每个用户的在线时长
     * @return
     */
    @Override
    public List<OnLineTimeResp> getOnlineTime() {
        PageInfo<LogResp> log = userServiceClient.log("", 0, 0, "");
        List<String> users = userServiceClient.getUserName();
        List<LogResp> list = log.getList();
        List<OnLineTimeResp> onLineTimeResps = new ArrayList<>();
        long l = System.currentTimeMillis();
        for (String name : users) {
            OnLineTimeResp onLineTimeResp = new OnLineTimeResp();
            //临时列表
            List<LogResp> tmp = new ArrayList<>();
            for (int i = 0; i < list.size(); i ++) {
                //找出用户名放到一个list再计算
                LogResp resp = list.get(i);
                if (resp.getUserName().equals(name)) {
                    tmp.add(resp);
                }
            }
            long time = 0;
            for (int i = 0 ; i < tmp.size()-1; i ++) {
                LogResp resp1 = tmp.get(i);
                LogResp resp2 = tmp.get(i+1);
                if (resp1.getType() == 1 && resp2.getType() == 0) {
                   time += resp2.getTime() - resp1.getTime();
                }
            }
            onLineTimeResp.setName(name);
            onLineTimeResp.setTime(time);
            onLineTimeResps.add(onLineTimeResp);
        }
        System.out.println(System.currentTimeMillis() - l);
        Collections.sort(onLineTimeResps, new Comparator<OnLineTimeResp>() {
            @Override
            public int compare(OnLineTimeResp o1, OnLineTimeResp o2) {
                return o2.getTime().compareTo(o1.getTime());
            }
        });
        return onLineTimeResps;
    }

    /**
     * 统计每天在线人数
     *
     * @return
     */
    @Override
    public List<CountUserResp> getCountUserByDay() {
        return userServiceClient.getCountUserByDay();
    }
    /**
     * 统计每天访问人次
     *
     * @return
     */
    @Override
    public List<CountUserResp> getCountTimesByDay() {
        return userServiceClient.getCountTimesByDay();
    }
}
