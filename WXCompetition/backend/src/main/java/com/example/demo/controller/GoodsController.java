package com.example.demo.controller;

import com.example.demo.Dao.GoodsMapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.Service.GoodService;
import com.example.demo.entity.Goods;
import com.example.demo.entity.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 起初用于表单的发布，随着功能的更新该类可以废弃
 */
@RestController
public class GoodsController {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodService goodService;
    /**
     * 发布信息
a     */
    @PostMapping("/releaseShop")
    public Boolean shopUp(@RequestParam("wxID")String wxID,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("title")String title,
                             @RequestParam("goodIntroduction")String goodIntroduction){
        User user = userMapper.userselect(wxID);
        String university = userMapper.univerSelect(wxID);
        String goodsID = goodService.goodIDCreate();
        goodsMapper.shopInsert(goodsID,title,goodIntroduction
                ,university,user.getPhonenumber(),user.getWxID());
        goodService.uploadImg(goodsID,file);
        return true;
    }
    /**
     *
     */
    @PostMapping("/detailsGet")
    public JSONObject goodGet(@RequestParam("wxID")String wxID,@RequestParam("goodID")String goodID){
        JSONObject jsonObject = null;
        Goods goods = goodsMapper.shopsel(goodID);
        jsonObject = JSONObject.fromObject(goods);
        jsonObject.put("buttonStatu","联系发布人");
        return jsonObject;
    }

}
