package com.example.jwtdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/secure")
public class SecureController {
    @GetMapping("/test")
    public String test(){
        return "testing done for the secure endpoint";
    }
}
