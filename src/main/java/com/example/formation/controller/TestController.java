package com.example.formation.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class TestController {

    @GetMapping("/public")
    private String publicApi(){
        return "publicApi";
    }

    @GetMapping("/private")
    private String privateApi(){
        return "privateApi";
    }

    @GetMapping("/admin")
    private String adminApi(){
        return "Allowed just for admin";
    }
}
