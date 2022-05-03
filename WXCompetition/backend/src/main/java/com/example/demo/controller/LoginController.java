package com.example.demo.controller;

import com.example.demo.Dao.UserMapper;
import com.example.demo.Service.UserService;
import com.example.demo.entity.User;
import com.example.demo.until.UrlConnection;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author Buer
 * @update 2021.04.22
 */
@RestController
public class LoginController {
    User user = new User();
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;

    /**
     * 传入code与appid,进行用户注册
     */
    @PostMapping("/wxLogin") // openid
    public JSONObject wxLogin(@RequestParam(value = "code") String code,@RequestParam("appid") String appid) {
        UrlConnection urlConnection = new UrlConnection();
        //最终返回json
        JSONObject jsonobject = null;
        //用于获取openid 和sessionkey
        JSONObject jsonObject1=null;
//        String appid = "wx9711147800b283cd";// 小程序id
        String serect = "0177e97f86883a8c36248ab4d0b2853d";// 小程序密钥1d8110d937085086fdd5e739593a5c10
        String url = "https://api.weixin.qq.com/sns/jscode2session"
            + "?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        // 组合请求连接
        String realurl = url.replace("APPID", appid).replace("SECRET", serect).replace("JSCODE", code);
        String result = urlConnection.getInfo(realurl);// 请求信息
        jsonObject1 = JSONObject.fromObject(result);
        //获取sessionkey
        String sessionkey = jsonObject1.getString("session_key");
        //获取openid
        String openid = jsonObject1.getString("openid");
        //拼接openid 和 sessionkey 最终形成token返回给前端
        String result01 = "{\"token\":\""+openid+"\"}";
        jsonobject = JSONObject.fromObject(result01);
//        user.setWxID(jsonobject.getString("openid"));
        //将生成的token存入到redis中
        stringRedisTemplate.opsForValue().set(openid,openid);
        String token = stringRedisTemplate.opsForValue().get(sessionkey+openid);
        if (!userService.userVerify(openid)){
            System.out.println("该用户不存在");
            userMapper.userInsert(openid);
        }
        jsonobject.put("phone",userMapper.getUserPhoneNumber(openid));
        return jsonobject;
    }

    /**
     * 传入用户用户名和微信头像url，记录字段
     */
    @PostMapping(path = "/wxLogin1", produces = "application/json;charset=UTF-8") // 用户名和头像
    public String wxLogin1(@RequestBody JSONObject info) {
        // System.out.println(info);// 测试传入对象
        user.setUsername(info.getString("nickName"));
        user.setImgadress(info.getString("avatarUrl"));
        user.setWxID(info.getString("WxID"));
        return "Success";
    }

    /**
     * 传入用户电话号码，记录字段
     */
    @GetMapping("/wxLogin2") // 用户电话
    public String wxLogin2(@RequestParam(value = "phoneNumber") String phoneNumber) {
        user.setPhonenumber(phoneNumber);
        return "Success";
    }
    @PostMapping("/updateUser")
    public JSONObject updateUser(@RequestParam("wxID")String wxID,@RequestParam("phone")String phonenumber,
                                     @RequestParam("username")String username){
        JSONObject jsonObject = new JSONObject();
        int i = userMapper.updUser(wxID, phonenumber,username);
        if (i!=0){
            jsonObject.put("phone",phonenumber);
            return jsonObject;
        }else
            jsonObject.put("error","获取失败");
            return jsonObject;
    }

    /**
     * 传入用户学校，记录字段
     */
    @GetMapping("/wxLogin3") // 所在学校
    public String wxLogin3(@RequestParam(value = "universityName") String universityName) {
//        user.setUniversityname(universityName);
//        int i = userMapper.updateUser(user);// 信息获取完全后注册
//        if (i == 0) {
//            return "Failure";
//        }
        return "Success";
    }
}