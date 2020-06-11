package com.beyond.common.result;

import java.io.Serializable;

/**
 * @author beyond
 * @date 2020/6/10 19:19
 */
public class AuthUser implements Serializable {

    private long userId;

    public AuthUser() {
    }

    public AuthUser(final long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(final long userId) {
        this.userId = userId;
    }
}
