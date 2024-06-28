package com.example.basicjwtapp.controller;

import com.example.basicjwtapp.dto.LoginDto;
import com.example.basicjwtapp.dto.ResponseDto;
import com.example.basicjwtapp.model.User;
import com.example.basicjwtapp.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @GetMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(jwtService.login(loginDto));
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.ok(jwtService.save(user));
    }

}
