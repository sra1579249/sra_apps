package com.example.basicjwtapp.service;

import com.example.basicjwtapp.dto.LoginDto;
import com.example.basicjwtapp.dto.ResponseDto;
import com.example.basicjwtapp.model.User;

public interface JwtService {
    User save(User user);
    ResponseDto login(LoginDto loginDto);

}
