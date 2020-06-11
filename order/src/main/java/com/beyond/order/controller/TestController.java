package com.beyond.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author beyond
 * @date 2020/6/10 17:56
 */
@RestController
@RequestMapping("/api/order")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping
    public String index() {
        return "index";
    }

}
