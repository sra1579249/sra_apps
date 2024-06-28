package com.example.demo.service;

import com.example.demo.dao.EmpDao;
import com.example.demo.dto.EmpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpService {

    @Autowired
    private EmpDao dao;

    public String saveEmp(EmpDto dto){
        return dao.saveEmp(dto);
    }

}
