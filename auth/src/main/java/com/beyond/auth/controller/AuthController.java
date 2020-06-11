package com.beyond.auth.controller;

import com.beyond.auth.service.AuthService;
import com.beyond.common.result.AuthUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author beyond
 * @date 2020/6/10 19:03
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(final AuthService authService) {this.authService = authService;}

    @GetMapping
    public AuthUser verifyAuth(String token) {
        return authService.verifyAuth(token);
    }

}
