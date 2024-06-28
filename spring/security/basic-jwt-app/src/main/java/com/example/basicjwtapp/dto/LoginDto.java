package com.example.basicjwtapp.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
