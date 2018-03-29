/**
 * Copyright (c) 2014-2018  墨博云舟 All Rights Reserved.
 */
package com.yb.chat.resp;

/**
 * OnLineTimeResp:
 *
 * @author yangbo
 * @version 1.00
 * @since 2018/3/29 0029 14:06
 */
public class OnLineTimeResp {
    private String name;
    private Long time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
