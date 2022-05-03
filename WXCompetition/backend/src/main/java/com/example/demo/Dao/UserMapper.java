package com.example.demo.Dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Mapper
@Component
public interface UserMapper {
    //用户表的创建
    @Update("create table if not exists user(" +
            "universityname char(100) not null," +
            "username char(100) not null," +
            "phonenumber varchar(11) not null," +
            "imgadress char(100) not null," +
            "wxID char(50) not null," +
            "id int not null auto_increment," +
            "primary key(id))")
    public int createUniversity();
    //用户登录时将用户的信息返回
    @Select("select * from user where wxID=#{wxID}")
    public User userselect(String wxID);
    //用户学校的查询
    @Select("select universityname from user where wxID=#{wxID}")
    public String univerSelect(String wxID);
    // 用户的注册
    @Update("insert into user(universityname,wxID) values('重庆工程学院',#{wxID})")
    public int userInsert(String wxID);
    //获取用户的电话号码
    // 校验用户是否存在
    @Select("select id from user where wxID = #{wxID}")
    public String verifytUser(String wxID);
    @Select("select username from user where where wxID=#{wxID}")
    String usernameGetById(String wxID);
    @Select("selct universityname from user where wxID=#{wxID}")
    String universityGet(String wxID);
    @Update("update user set phonenumber = #{phoneNumber} ,username=#{username} where wxID=#{wxID}")
    int updUser(String wxID,String phoneNumber,String username);
    @Select("select phonenumber from user where wxID=#{wxID}")
    String getUserPhoneNumber(String wxID);
}
