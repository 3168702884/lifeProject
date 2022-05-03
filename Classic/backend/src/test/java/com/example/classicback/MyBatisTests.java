package com.example.classicback;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.classicback.dao.TestUserMapper;
import com.example.classicback.entity.TestUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@SpringBootTest
public class MyBatisTests {
    @Autowired
    private TestUserMapper testUserMapper;

    @Test
    public void selectArticle() {
        List<TestUser> testUsers = testUserMapper.selectList(null);
//        System.out.println(users);
        testUsers.forEach(System.out::println);
    }

    @Test//测试插入
    public void insertTest() {
        TestUser testUser = new TestUser();
        testUser.setName("aaa");
        testUser.setAge(18);
        testUser.setEmail("2803708553@qq.com");
        Integer result = testUserMapper.insert(testUser); //会帮我们自动生成id
        System.out.println("result:" + result); //受影响的行数
        System.out.println("user:" + testUser); //通过日志发现id会自动回填
        System.out.println("userName:" + testUser.getName());
    }

    @Test//测试插入
    public void insertGzxTest() {
        TestUser testUser = new TestUser();
//        user.setId(7L);
        testUser.setName("ggzx");
        testUser.setAge(18);
        testUser.setEmail("2803708553@qq.com");
        Integer result = testUserMapper.insert(testUser); //会帮我们自动生成id
        System.out.println("result:" + result); //受影响的行数
        System.out.println("user:" + testUser); //通过日志发现id会自动回填
        System.out.println("userName:" + testUser.getName());
    }

    @Test//测试更新
    public void updateTest() {
        TestUser testUser = new TestUser();
        testUser.setId(2L);//怎么改id？？
        //通过条件自动拼接动态Sql
        testUser.setName("root");
        testUser.setAge(12);
        testUser.setEmail("root@qq.com");
        int i = testUserMapper.updateById(testUser);//updateById，但是参数是个user
        System.out.println(i);
    }

    @Test//通过id查询多个用户
    public void testSelectBatchIds() {
        List<TestUser> testUsers = testUserMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        testUsers.forEach(System.out::println);
        //System.out.println(users);
    }

    @Test//通过条件查询之一  map
    public void testMap() {
        HashMap<String, Object> map = new HashMap<>();
        //自定义要查询的
//        map.put("name","gzx");
        map.put("age", 21);
        List<TestUser> testUsers = testUserMapper.selectByMap(map);
//        users.forEach(System.out::println);
        System.out.println("条件查询" + testUsers);
    }

    @Test//测试分页查询
    public void testPage() {
        //参数一current：当前页   参数二size：页面大小
        //使用了分页插件之后，所有的分页操作都变得简单了
        Page<TestUser> page = new Page<>(2, 5);
        testUserMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
        System.out.println("总页数==>" + page.getTotal());
    }

    //    删除，批量删除，条件删除，添加插件配置后删除变成逻辑删除
    @Test
    public void testDeleteById() {
        testUserMapper.deleteById(6L);
    }

    @Test
    public void testDeleteBatchIds() {
        testUserMapper.deleteBatchIds(Arrays.asList(1359507762519068675L, 1359507762519068676L));
    }

    @Test
    public void testD() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", "18");
        map.put("name", "lol");
        testUserMapper.deleteByMap(map);
    }
//
}
