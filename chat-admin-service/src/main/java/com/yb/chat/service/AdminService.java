/**
 * Copyright (c) 2014-2018  墨博云舟 All Rights Reserved.
 */
package com.yb.chat.service;

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
}
