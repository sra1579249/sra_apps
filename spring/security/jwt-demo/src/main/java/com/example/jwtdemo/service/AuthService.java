package com.example.jwtdemo.service;

import com.example.jwtdemo.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}