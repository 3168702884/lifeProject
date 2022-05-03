package com.example.demo.controller;

import com.example.demo.Dao.NeedShopMapper;
import com.example.demo.Dao.OrderMapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.Service.GoodService;
import com.example.demo.Service.NeedShopService;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@Controller
public class NeedShopController {
    @Autowired
    private NeedShopService needShopService;
    @Autowired
    private NeedShopMapper needShopMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodService goodService;
    /**
     *
     * @param wxID:卖家ID
     * @param page:具体页数
     * @param response:响应头设置
     * @return:返回对应学校的发布学校信息
     */
    @PostMapping("/showNeeds")
    public JSONArray showNeeds(@RequestParam("wxID")String wxID, @RequestParam("page") int page, HttpServletResponse response){
        return needShopService.showNeeds(wxID,page,response);
    }

    /**
     *
     * @param wxID:用户ID
     * @param needID:用户需求ID
     * @return:返回商品详情
     */
    @PostMapping("/needsDetail")
    public JSONObject needsDetail(@RequestParam("wxID")String wxID,@RequestParam("needID")String needID){
        JSONObject jsonObject =JSONObject.fromObject(needShopMapper.needDetail(needID));
        jsonObject.put("buttonStatu","联系发布人");
        return jsonObject;
    }

    /**
     *
     * @param wxID:用户信息
     * @param str:关键字
     * @return:查询到的需求商品
     */
    @GetMapping("/linkShowNeeds")
    public JSONArray linkShowNeeds(@RequestParam("wxID")String wxID,@RequestParam("keyword")String str){
        //分页查询中的页数，
        int page = 0;
        //判断下一页是否还有数据
        int page1=1;
        str = '%' + str + '%';
        //返回的总数据
        JSONArray jsonArray = JSONArray.fromObject(needShopMapper.likeShop(wxID,page*6,(page+1)*6,str));
        int i = 0;
        if (jsonArray.isEmpty()) {// 校验机制
            JSONObject jsonError = new JSONObject();
            jsonError.put("error", "没有查询到内容");
            jsonArray.add(jsonError);
        }
        return jsonArray;
    }
    @PostMapping("/buyOrderDetail")
    public JSONObject buyOrderDetail(@RequestParam("wxID")String buyID
            ,@RequestParam("goodID")String goodID
            ,@RequestParam("buttonStatu")String buttonStatu){
        JSONObject jsonObject = new JSONObject();
        switch (buttonStatu){
            case "联系发布人":needShopService.wantBuy(buyID,goodID);break;
            case "正在联系": Order order = orderMapper.buyOrderGet(buyID,goodID);
                jsonObject.put("sendPhoneNumber",order.getSend_phonenumber());break;
            case "正在请求":break;
            case "结束请求":orderMapper.delShopByCustumer(buyID,goodID);break;
            default:jsonObject.put("error","buttonError");break;
        }
        return jsonObject;
    }
    @PostMapping("/releaseShop2")
    public Boolean needRelease(@RequestParam("wxID")String wxID,
                               @RequestParam("title")String title,
                               @RequestParam("goodIntroduction")String needIntroduction,
                               @RequestParam("file") MultipartFile file){
        System.out.println("需求发布");
        User user = userMapper.userselect(wxID);
        String university = userMapper.univerSelect(wxID);
        String goodsID = goodService.goodIDCreate();
        needShopMapper.needRelease(goodsID,title,needIntroduction,university,user.getPhonenumber(),wxID);
        needShopService.uploadImg(goodsID,file);
        return true;
    }
}
