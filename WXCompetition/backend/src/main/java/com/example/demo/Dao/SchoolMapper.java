package com.example.demo.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SchoolMapper {
    @Update("create table if not exists school(" +
            "name varchar(150) not null," +
            "address varchar(300) not null," +
            "schoolcode char(20) not null," +
            "primary key(schoolcode))")
    int schoolcreate();
    @Insert("Insert into school values(#{name},#{address},#{schoolcode})")
    int schoolInsert(String name,String address,String schoolcode);
}
