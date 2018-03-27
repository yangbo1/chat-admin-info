/**
 * Copyright (c) 2014-2018  墨博云舟 All Rights Reserved.
 */
package com.yb.chat.service.impl;

import com.yb.chat.client.UserServiceClient;
import com.yb.chat.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
