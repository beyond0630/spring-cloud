package com.beyond.auth.service.impl;

import com.beyond.auth.service.AuthService;
import com.beyond.common.result.AuthUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author beyond
 * @date 2020/6/10 19:03
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthUser verifyAuth(final String token) {
        if (!StringUtils.isEmpty(token)) {
            return new AuthUser(1);
        }
        return null;
    }
}
