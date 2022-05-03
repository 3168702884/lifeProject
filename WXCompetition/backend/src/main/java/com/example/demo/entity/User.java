package com.example.demo.entity;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
public class User {
    //用户名
    private String username;
    //用户注册大学
    private String universityname;
    //头像地址
    private String imgaddress;
    //微信登录ID
    private String wxID;
    //用户号码
    private String phonenumber;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setImgadress(String img) {
        this.imgaddress = img;
    }

    public void setUniversityname(String university) {
        this.universityname = universityname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWxID(String wxID) {
        this.wxID = wxID;
    }


    public String getImgaddress() {
        return imgaddress;
    }


    public String getUniversity() {
        return universityname;
    }

    public String getUsername() {
        return username;
    }

    public String getWxID() {
        return wxID;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", university='" + universityname + '\'' +
                ", imgaddress='" + imgaddress + '\'' +
                ", wxID='" + wxID + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
