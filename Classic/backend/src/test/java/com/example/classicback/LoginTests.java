package com.example.classicback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.classicback.dao.TestUserMapper;
import com.example.classicback.dao.UserMapper;
import com.example.classicback.entity.TestUser;
import com.example.classicback.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
public class LoginTests {
    @Autowired
    private UserMapper userMapper;

    @Test//测试插入
    public void insertTest() {
        User user = new User();
//        user.setId(1L);
        user.setUsername("gzx");
        user.setAccess("admin");
        Integer result = userMapper.insert(user); //会帮我们自动生成id
        System.out.println("result:" + result); //受影响的行数
    }
    @Test
    public void selectArticle() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
