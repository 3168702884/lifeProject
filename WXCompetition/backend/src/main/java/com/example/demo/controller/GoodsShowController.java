package com.example.demo.controller;

import com.example.demo.Dao.GoodsMapper;
import com.example.demo.Dao.UserMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Buer
 * @update 2021.04.23
 * @remark 前端需要验证传入数据有效性（不允许为空）
 */
@RestController
public class GoodsShowController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    /**
     * @param wxID:用于token的验证并且验证该用户是否存在
     * */
    @GetMapping("/verify")
    public String verify(@RequestParam(value = "wxID") String wxID) {
        try {
            userMapper.verifytUser(wxID);
        } catch (Exception e) {
            return "Failure";
        }
        return "Success";
    }
    /**
     * @param page:用于传递商品分页是第几页了
     * @param wxID :通过用户id来查询用户对应的学校，并且通过该学校查询到该学校发布的商品
     * 用于商品的展示
     * */
    @GetMapping("/showShop") // 查询全部商品-分页-逆序
    public JSONArray showShop(@RequestParam("wxID") String wxID, @RequestParam(value = "page") int page, HttpServletResponse response) {
        JSONArray jsonArray = JSONArray.fromObject(goodsMapper.selectShop(wxID,page*6));
        JSONObject jsonError = new JSONObject();//校验查询是否为空
        if (jsonArray.isEmpty()) {//校验机制
            jsonError.put("error", "没有查询到内容");
        }
        page++;
        //查询第二页是否为空，如果为空则添加page状态码
        JSONArray jsonArray1 = JSONArray.fromObject(goodsMapper.selectShop(wxID,page*6));
        if (jsonArray1.isEmpty()){
            response.setHeader("loadStatus","nomore");
        }else{
            response.setHeader("loadStatus","loadmore");
        }
        return jsonArray;
    }
    /**
     * @param str:前端传模糊查询的关键字，之后再返回给前端
     * @param wxID :1.该参数用于token验证 2.还是用该id查询到用户对应的学校
     * 用于模糊查询
     * */
    @GetMapping("/linkShowShop") // 模糊查询商品-分页-逆序
    public JSONArray linkShowShop(@RequestParam("wxID")String wxID,@RequestParam(value = "keyword") String str) {
        //分页查询中的页数，
        int page = 0;
        //判断下一页是否还有数据
        int page1=1;
        str = '%' + str + '%';
        //返回的总数据
        JSONArray jsonArray = JSONArray.fromObject(goodsMapper.likeShop(wxID,page*6,(page+1)*6,str));
        int i = 0;
        if (jsonArray.isEmpty()) {// 校验机制
            JSONObject jsonError = new JSONObject();
            jsonError.put("error", "没有查询到内容");
            jsonArray.add(jsonError);
        }
        return jsonArray;
    }
}