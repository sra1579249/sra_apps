package com.example.basicjwtapp.dto;

import lombok.Data;

@Data
public class ResponseDto {

    private String token;
    private String tokenType = "Bearer";

}
