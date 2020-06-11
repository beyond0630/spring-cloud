package com.beyond.gateway.client.service;

import com.beyond.common.result.AuthUser;
import com.beyond.gateway.client.fallback.AuthServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author beyond
 * @date 2020/6/10 19:26
 */
@FeignClient(value = "auth", fallbackFactory = AuthServiceFallBack.class)
public interface AuthServiceClient {

    @GetMapping(value = "/api/auth")
    AuthUser verifyAuth(@RequestHeader(value = "Authorization") String token);
}
