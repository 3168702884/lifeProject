package com.example.demo.controller;

import com.example.demo.Dao.NeedShopMapper;
import com.example.demo.Dao.OrderMapper;
import com.example.demo.Dao.GoodsMapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.Service.GoodService;
import com.example.demo.Service.NeedShopService;
import com.example.demo.Service.OrderService;
import com.example.demo.Service.UserService;
import com.example.demo.entity.NeedShop;
import com.example.demo.entity.Order;
import com.example.demo.entity.Goods;
import com.example.demo.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@EnableTransactionManagement//开启事务管理
public class OrderController {
    //状态码，默认为0，为未查看，1为同意，2为不同意
    String s0="0",s1="1",s2="2";
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    private Goods goods = null;
    private User user = null;
    //关于“我想买”的事务
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodService goodService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private NeedShopService needShopService;
    @Autowired
    private NeedShopMapper needShopMapper;
    //买家点击按钮事件
    /**
     * @param buyID:买家的ID，同构对应买家ID，查询到买家的信息
     * @param goodID :商品ID，通过对应的商品ID，查询到商品信息
     * */
    @PostMapping("buyOrderGet")
    public JSONObject buyOrderGet(@RequestParam("wxID")String buyID
            ,@RequestParam("goodID")String goodID
            ,@RequestParam("buttonStatu")String buttonStatu){
        System.out.println(buttonStatu);
        JSONObject jsonObject = new JSONObject();
        if (buttonStatu.equals("正在联系")){
            Order order = orderMapper.buyOrderGet(buyID,goodID);
            jsonObject.put("sendPhoneNumber",order.getSend_phonenumber());
        }else if (buttonStatu.equals("联系发布人")){
            orderService.wantBuy(buyID,goodID);
            jsonObject.put("buttonStatu","正在请求");
        }else if (buttonStatu.equals("正在请求")){
            orderMapper.delShopByCustumer(buyID,goodID);
        }
        return jsonObject;
    }

    /**
     * 卖家按钮点击事件
     * @param sendID
     * @param goodID
     * @param buttonStatus
     * @return
     */
    @PostMapping("sendOrderGet")
    public JSONArray sendOrderGet(@RequestParam("wxID")String sendID,
                                   @RequestParam("goodID")String goodID,
                                   @RequestParam("buttonStatu")String buttonStatus){
        JSONArray jsonArray = new JSONArray();
        //卖家下架处理
        if (buttonStatus.equals("我要下架")){
            //修改商品状态，为已下架
            goodService.goodDown(sendID, goodID);
            jsonArray = goodService.sendOrderGet(sendID);
        }
        else if (buttonStatus.equals("有人联系")){
            /*
            * 获取有哪些用户在请求购买商品
            * 1.首先从订单表中获取买家ID
            * 2.再通过买家ID获取他的用户名，并且封装成数组，返回给前端。
            * */
        }
        else if (buttonStatus.equals("正在联系")){
            /*
               通过卖家ID和商品ID获取对应的用户电话号码
            */
            JSONObject jsonObject = orderService.getBuyPhoneNumber(sendID,goodID);
            jsonArray =JSONArray.fromObject(jsonObject);
        }
        //重新获取一次卖家订单

        return jsonArray;
    }

    /**
     *
     * @param sendID:卖家ID
     * @param goodID:买家ID
     * @return:更新商品
     */
    @PostMapping("/UnShelve")
    public JSONArray UnShelve(@RequestParam("wxID")String sendID,@RequestParam("goodID")String goodID){
        //修改商品状态，为已下架
        System.out.println(goodService.goodSel(goodID));
        if (goodService.goodSel(goodID)) {
            goodService.goodDown(sendID, goodID);
        }else {
            needShopService.needShopDown(sendID, goodID);
        }
        return goodService.sendOrderGet(sendID);
    }

    /** *
     *
     * @param sendID:卖家ID
     * @param goodID:商品ID
     * @return :买家电话号码
     */
    @PostMapping("/isContacting")
    public JSONObject isContacting(@RequestParam("wxID")String sendID,String goodID){
        /*
               通过卖家ID和商品ID获取对应的用户电话号码
        */
        return orderService.getBuyPhoneNumber(sendID,goodID);
    }

    /**
     *
     * @param sendID:卖家ID
     * @param goodID:买家ID
     * @return:返回是否已经结束联系
     */
    @PostMapping("/finishContacting")
    public Boolean finishContacting(@RequestParam("wxID")String sendID, String goodID){
        int i =orderService.finishContacting(sendID,goodID);
        switch (i){
            case 0:return false;
            case 1:return false;
            case 2:return true;
            default:return false;
        }
    }

    /**
     *
     * @param sendID:卖家ID
     * @param goodID:商品ID
     * @return:通过卖家ID和商品ID对应的有多少人想买
     */
    @PostMapping("/someoneContacting")
    public JSONArray someContacting(@RequestParam("wxID")String sendID,@RequestParam("goodID")String goodID){
        /*
         * 获取有哪些用户在请求购买商品
         * 1.首先从订单表中获取买家ID
         * 2.再通过买家ID获取他的用户名，并且封装成数组，返回给前端。
         * */
        System.out.println(userService.userGetByBuyId(sendID,goodID));
        return userService.userGetByBuyId(sendID,goodID);
    }

    /**
     *
     * @param sendID:卖家ID
     * @param goodID:商品ID
     * @param buyID:买家ID
     * @return:是否已经接受对方请求
     */
    @PostMapping("/accept")
    public boolean accept(@RequestParam("wxID")String sendID,
                          @RequestParam("goodID")String goodID,@RequestParam("buy_ID")String buyID){
        int i=0;
        i=orderService.acceptSend(sendID, goodID, buyID);
        if (i==4){
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * @param sendID:卖家ID
     * @param goodID:商品ID
     * @param buyID:买家ID
     * @return:返回是否已经拒绝对应买家请求
     */
    @PostMapping("/reduce")
    public boolean reduce(@RequestParam("wxID")String sendID,
                          @RequestParam("goodID")String goodID,@RequestParam("buy_ID")String buyID){
        int i = orderService.reduceSend(sendID,buyID,goodID);
        if (i!=1){
            return false;
        }else{
            return true;
        }
    }
    /**
     *
     * @param buyID
     * @param goodID
     * @return
     */
    @PostMapping("buyOrderGet1")
    public JSONObject buyOrderGet1(@RequestParam("wxID")String buyID,@RequestParam("goodID")String goodID){
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        Order order = orderMapper.buyOrderGet(buyID,goodID);
        Goods goods = goodsMapper.shopsel(goodID);
        NeedShop needShop = needShopMapper.needDetailGet(goodID);
        String buySignal = " ";
        ArrayList arrayList = new ArrayList();
        String sendSignal = " ";
        if(goods!=null) {
            if (!" ".equals(goods.getImgadress1())) {
                arrayList.add(goods.getImgadress1());
            }
            System.out.println(arrayList);
            if (!arrayList.isEmpty()) {
                jsonObject.put("image", arrayList);
            }
            jsonObject.put("title", goods.getShangpin());
            jsonObject.put("goodIntroduction", goods.getJianjie());
        }
        if (needShop!=null){
            if (!" ".equals(needShop.getImgadress1())) {
                arrayList.add(needShop.getImgadress1());
            }
            System.out.println(arrayList);
            if (!arrayList.isEmpty()) {
                jsonObject.put("image", arrayList);
            }
            jsonObject.put("title", needShop.getShangpin());
            jsonObject.put("goodIntroduction", needShop.getJianjie());
            System.out.println("needshop:"+jsonObject);

        }
        if (order!=null){
            buySignal = order.getBuy_signal();
            sendSignal = order.getSend_signal();
        }
        if (buySignal.equals("0")&&sendSignal.equals("0")){
            jsonObject.put("buttonStatu","正在请求");
        }
        else if (buySignal.equals("0")&&sendSignal.equals("1")){
            jsonObject.put("buttonStatu","正在联系");
        }
        else if (buySignal.equals("1")){
            jsonObject.put("buttonStatu","您已拒绝");
        }
        else if (buySignal.equals("0")&&sendSignal.equals("2")){
            jsonObject.put("buttonStatu","已被拒绝");
        }else if (order == null){
            jsonObject.put("buttonStatu","联系发布人");
        }
        jsonObject1.put("informationList",jsonObject);
        return jsonObject1;
    }

    /**
     * 信息交易状态
     */
    @PostMapping("sendOrderGet1")
    private JSONObject sendOrderGet1(@RequestParam("wxID")String sendID,@RequestParam("goodID")String goodID){
        JSONObject jsonObject = new JSONObject();
        Order  order = orderMapper.sendOrderGet(goodID,sendID);
        String buySignal = order.getBuy_signal();
        String sendSignal = order.getSend_signal();
        if(sendSignal.equals("2")){
            jsonObject.put("buttonStatu","您已拒绝对方交易");
        }
        else if (sendSignal.equals("1")&&buySignal.equals("1")){
            jsonObject.put("buttonStatu","拒绝");
        }
        else if (sendSignal.equals("0")&&buySignal.equals("0")){
            jsonObject.put("buttonStatu","接受");
        }
        else if (sendSignal.equals("0")&&buySignal.equals("1")){
            jsonObject.put("buttonStatu","对方已经取消交易");
        }
        else if (sendSignal.equals("1")&&buySignal.equals("1")){
            jsonObject.put("buttonStatu","对方已经取消交易");
        }

        return jsonObject;
    }



    @PostMapping("/sendOrders")
    public JSONArray sendOrder(@RequestParam("wxID")String sendID,HttpServletResponse response){
        List<Goods> list = new ArrayList<Goods>();
        list = goodsMapper.sendOrdersGet(sendID,"0");
        List<NeedShop> list1 = new ArrayList<NeedShop>();
        list1 = needShopMapper.needShopGets(sendID);
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        List list3 = new ArrayList();
        response.setHeader("result","0");
        if (list.size()!=0){
            list3.addAll(list);
            response.setHeader("result","1");
        }
        if (list1.size()!=0){
            list3.addAll(list1);
            response.setHeader("result","1");
        }
        jsonArray = JSONArray.fromObject(list3);
        System.out.println(list3);

        return jsonArray;
    }
//    //获取对应的卖家电话号码
//    @GetMapping("/sendNumberGet")
//    public JSONObject numberGet(@RequestParam("wxID") String wxID,@RequestParam("shangpin_ID") String shangpin_ID){
//        JSONObject jsonObject = new JSONObject();
//        Order order = orderMapper.numberGet(wxID,shangpin_ID);
//        jsonObject.put("sendPhoneNumber", order.getSend_phonenumber());
//        jsonObject.put("sendSignal", order.getSend_signal());
//        jsonObject.put("buySignal", order.getBuy_signal());
//        return jsonObject;
//    }
//    //获取作为买家的订单
    @PostMapping("/buyOrders")
    public JSONArray buyOrder(@RequestParam("wxID")String buyID, HttpServletResponse response){
        JSONArray jsonArray = null;
        jsonArray = JSONArray.fromObject(orderMapper.buyOrdersGet(buyID,"0"));
        response.setHeader("result","1");
        if(jsonArray.isEmpty()){
            response.setHeader("result","0");
        }
        return jsonArray;
    }
//    @PostMapping("/buyNumberGet")
//    public JSONObject buyNumberGet(@RequestParam("wxID")String wxID,@RequestParam("goodID")String goodsID){
//        JSONObject jsonObject = null;
//        Order order = orderMapper.buyNumberGet(wxID,goodsID);
//        jsonObject.put("buyNumber",order.getBuy_phonenumber());
//        return jsonObject;
//    }
//    @PostMapping("/theOrderBuy")
//    public JSONObject theOrderBuy(@RequestParam("wxID")String wxID,@RequestParam("goodID")String goodID){
//        JSONObject jsonObject = null;
//        Order order = orderMapper.buyOrderGet(wxID,goodID);
//        jsonObject = JSONObject.fromObject(order);
//        if (order.getSend_signal()=="2"&&order.getBuy_signal()=="0"){
//            jsonObject.put("buttonStatus","卖家已拒绝与您交易");
//        }
//        else if(order.getBuy_signal()=="1"&&order.getSend_signal()=="0"){
//            jsonObject.put("buttonStatus","您已拒绝");
//        }
//        else if(order.getBuy_signal()=="1"&&order.getSend_signal()=="1"){
//            jsonObject.put("buttonStatus","您已拒绝");
//            goodsMapper.shoptokenchange("0",goodID);
//        }
//        else if(order.getBuy_signal()=="1"&&order.getSend_signal()=="2"){
//            jsonObject.put("buttonStatus","您已拒绝");
//        }
//        else if (order.getSend_signal()=="0"&&order.getBuy_signal()=="0"){
//            jsonObject.put("buttonSrarus","正在请求");
//        }
//        else if (order.getSend_signal()=="1"&&order.getBuy_signal()=="1"){
//            jsonObject.put("buttonStatus","正在联系中");
//        }
//        return jsonObject;
//    }
//    @PostMapping("/theOrderSend")
//    public JSONObject theOrderSend(@RequestParam("wxID")String sendID
//            ,@RequestParam("goodID")String goodID){
//        JSONObject jsonObject = null;
//        Order order = orderMapper.sendOrderGet(goodID,sendID);
//        if (order.getSend_signal()=="0"&&order.getBuy_signal()=="0"){
//            jsonObject.put()
//        }
//    }
}
