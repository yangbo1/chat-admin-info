/**
 * Copyright (c) 2014-2018  墨博云舟 All Rights Reserved.
 */
package com.yb.chat.service;


import com.yb.chat.client.response.CountUserResp;
import com.yb.chat.client.response.LogResp;
import com.yb.chat.resp.OnLineTimeResp;

import java.util.List;

/**
 * AdminService:
 *
 * @author yangbo
 * @version 1.00
 * @since 2018/3/26 0026 11:33
 */
public interface AdminService {
    /**
     * 查看在线列表
     * @return
     */
    List findOnline();

    /**
     * 查询所有用户 分页
     * @param c
     * @param p
     *
     * @return
     */
    Object findAllUser(int c, int p);
    /**
     * 查询最后登录时间
     * @param name 用户名
     *
     * @return 时间戳
     */
    Object userLastLoginTime(String name);

    /**
     * 查看日志
     * @param name
     * @param c
     * @param p
     * @param sort
     *
     * @return
     */
    Object log(String name, int c, int p, String sort);

    /**
     * 获取用户在线时长
     * @return
     */
    List<OnLineTimeResp> getOnlineTime();

    /**
     * 统计每天在线人数
     *
     * @return
     */
    List<CountUserResp> getCountUserByDay();
    /**
     * 统计每天访问人次
     *
     * @return
     */
    List<CountUserResp> getCountTimesByDay();

    String stopMessage(String message);

    String stopTime(String name);
}
