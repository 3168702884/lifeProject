package com.example.demo.Service;

import com.example.demo.Dao.OrderMapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private OrderMapper orderMapper;
    public JSONArray userGetByBuyId(String sendID,String goodID){
        JSONArray jsonArray = new JSONArray();
        List list = orderService.buyIDget(sendID,goodID);
        List<User> userList =  new ArrayList<User>();
        //遍历买家ID
        Iterator<String> iterable = (Iterator<String>) list.iterator();
        while(iterable.hasNext()){
            String buyID = iterable.next();
            //通过买家ID获取买家用户名
            userList.add(userMapper.userselect(buyID));
        }
        jsonArray = JSONArray.fromObject(userList);
        return jsonArray;
    }
    //登录时进行redis缓存
    public void tokenCreate(String token,String tokenvalue){
        stringRedisTemplate.opsForValue().set(token,tokenvalue);
    }
    public JSONObject getBuyPhoneNumber(String sendID,String goodID){
       JSONObject jsonObject = new JSONObject();
       try {
           jsonObject.put("phoneNumber", orderMapper.buyNumberGet(sendID, goodID));
       }catch (Exception e){
           e.printStackTrace();
       }
       return jsonObject;
    }
    public void userInsert(){

    }
    public Boolean userVerify(String wxID){
        //用查询出的id校验用户是否存在
        System.out.println(userMapper.verifytUser(wxID));
        if (null==userMapper.verifytUser(wxID)){
            System.out.println("返回false");
            return false;
        }
        return true;
    }
}
