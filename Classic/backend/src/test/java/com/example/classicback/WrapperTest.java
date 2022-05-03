package com.example.classicback;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.classicback.dao.TestUserMapper;
import com.example.classicback.entity.TestUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class WrapperTest {
    @Autowired
    private TestUserMapper testUserMapper;
    @Test
    void contextLoads() {
    }
    //查询name不为空，email不为空，age大于18的用户
    @Test
    public void testWrapper1() {
        //参数是一个wrapper ，条件构造器，和刚才的map对比学习！
        //查询name不为空，email不为空，age大于18的用户
        QueryWrapper<TestUser> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age",18);
        List<TestUser> testUserList = testUserMapper.selectList(wrapper);
        testUserList.forEach(System.out::println);
    }
    //查询name=wsk的用户
    @Test
    public void testWrapper2() {
        //查询name=wsk的用户
        QueryWrapper<TestUser> wrapper = new QueryWrapper<>();
        wrapper.eq("name","wsk");
        //查询一个数据selectOne，若查询出多个会报错
        //Expected one result (or null) to be returned by selectOne(), but found: *
        //若出现多个结果使用list或map
        TestUser testUser = testUserMapper.selectOne(wrapper);//查询一个数据，若出现多个结果使用list或map
        System.out.println(testUser);
    }
    //查询age在10-20之间的用户
    @Test
    public void testWrapper3() {
        //查询age在10-20之间的用户
        QueryWrapper<TestUser> wrapper = new QueryWrapper<>();
        wrapper.between("age", 10, 20);//区间
        Integer count = testUserMapper.selectCount(wrapper);//输出查询的数量selectCount
        System.out.println(count);
    }
    //模糊查询，qq%  左和右？
    @Test
    public void testWrapper4() {
        //模糊查询
        QueryWrapper<TestUser> wrapper = new QueryWrapper<>();
        wrapper
                .notLike("name","s")
                .likeRight("email","t");//qq%  左和右？
        List<Map<String, Object>> maps = testUserMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }
    //模糊查询id 在子查询中查出来(select id from user where id<5)
    @Test
    public void testWrapper5() {
        //模糊查询
        // SELECT id,name,age,email,version,deleted,create_time,update_time
        //FROM user
        //WHERE deleted=0 AND id IN
        //(select id from user where id<5)
        QueryWrapper<TestUser> wrapper = new QueryWrapper<>();
        //id 在子查询中查出来
        wrapper.inSql("id","select id from user where id<5");
        List<Object> objects = testUserMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }
    //通过id进行降序排序
    @Test
    public void testWrapper6() {
        QueryWrapper<TestUser> wrapper = new QueryWrapper<>();
        //通过id进行降序排序
        wrapper.orderByDesc("id");
        List<TestUser> testUserList = testUserMapper.selectList(wrapper);
        testUserList.forEach(System.out::println);
    }
}
