package com.beyond.auth.service;

import com.beyond.common.result.AuthUser;

/**
 * @author beyond
 * @date 2020/6/10 19:03
 */
public interface AuthService {

    AuthUser verifyAuth(final String token);
}
