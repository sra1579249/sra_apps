package com.example.demo.dao;

import com.example.demo.dto.EmpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class EmpDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public String saveEmp(EmpDto dto){
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = null;
            preparedStatement = con.prepareStatement("insert into emp (eid,ename) values (?,?)");
            preparedStatement.setInt(1,dto.getEid());
            preparedStatement.setString(2,dto.getEname());
            return preparedStatement;
        });
        return  "emp saved successfully";
    }

}
