package com.example.demo.web;

import com.example.demo.dto.EmpDto;
import com.example.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/test")
public class EmpController {
    @Autowired
    private EmpService service;
    @PostMapping("/save")
    public String saveEmp(@RequestBody EmpDto dto){
        return service.saveEmp(dto);
    }
}
