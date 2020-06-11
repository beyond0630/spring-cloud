package com.beyond.auth.controller;

import com.beyond.auth.service.AuthService;
import com.beyond.common.result.AuthUser;
import org.springframework.web.bind.annotation.*;

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
    public AuthUser verifyAuth(@RequestHeader(value = "Authorization") String token) {
        return authService.verifyAuth(token);
    }

    @PostMapping
    public AuthUser login() {
        return new AuthUser(2);
    }

}
