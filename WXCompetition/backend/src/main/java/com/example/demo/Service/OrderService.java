package com.example.demo.Service;

import com.example.demo.Dao.GoodsMapper;
import com.example.demo.Dao.NeedShopMapper;
import com.example.demo.Dao.OrderMapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.entity.Goods;
import com.example.demo.entity.NeedShop;
import com.example.demo.entity.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    //获取买家ID
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodService goodService;
    @Autowired
    private NeedShopMapper needShopMapper;
    public List buyIDget(String sendID,String goodID){

        return orderMapper.buyIDget(sendID,goodID);
    }
    public JSONObject getBuyPhoneNumber(String sendID, String goodID){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("phoneNumber", orderMapper.buyNumberGet(sendID, goodID));
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
    //关于卖家具体卖给某个买家
    public int acceptSend(String sendID,String goodID,String buyID){
        /*
        * 1.更改卖家信号，告知买家，接受其请求
        * 2.更改其余卖家信号，告知其余买家已被拒绝
        * 3.更改商品状态，为下架状态
        * 4.更改表中sendButtonStatu为正在联系
         */
        boolean flag = goodService.goodSel(goodID);
        System.out.println("sendID:"+sendID+"  buyID:"+buyID+"  goodID:"+goodID);
        int i=0;
        try{
            int j = orderMapper.wantsend(buyID, "1", goodID);
            i++;
            System.out.println(j);
        }catch(Exception e){
            e.printStackTrace();
        }try{
            int j  = orderMapper.updateOtherSendSignal(sendID, buyID, goodID);
            i+=j;
        }catch (Exception e){
            e.printStackTrace();
        }
        if(flag){
            try{
                int j =goodsMapper.shoptokenchange("1",goodID);
                i+=j;
            }catch (Exception e){
                e.printStackTrace();
            }try{
                int j = goodsMapper.connacting(goodID);
                i+=j;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            try {
                int j = needShopMapper.needTokenChange(goodID);
                i+=j;
            }catch (Exception e){
                e.printStackTrace();
            }try{
                int j = needShopMapper.connacting(goodID);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("完成");
        return i;
    }
    public int reduceSend(String sendID,String buyID,String goodID){
        return orderMapper.reduceBuy(sendID, buyID, goodID);
    }

    /**
     *
     * @param buy_ID:通过该ID获取对应的买家信息
     * @param shangpin_ID:通过该商品ID，获取对应的卖家信息以及对应的商品信息
     * @return
     * 1.先获取对应的商品信息
     * 2.通过获取的买家信息和商品信息创建订单
     * 3.更改卖家商品状态为有人联系
     */
    public JSONObject wantBuy( String buy_ID,  String shangpin_ID){
        JSONObject json = new JSONObject();
        String matter = "信息已发送，等待卖方确认";
        //查询买家信息
        User user = userMapper.userselect(buy_ID);
        Goods goods = new Goods();
        NeedShop needShop = needShopMapper.needDetailGet(shangpin_ID);
        try{
            //获取对应对应的商品信息
            goods = goodsMapper.shopsel(shangpin_ID);
            System.out.println(needShop);
        }catch(Exception e){
            matter = "获取商品失败";
            json.put("matter",matter);
            e.printStackTrace();
            return json;
        }
        try {
            if (goods!=null) {
                System.out.println(user);
                orderMapper.orderInsert(shangpin_ID, goods.getShangpin(), buy_ID, goods.getSend_ID(),
                        user.getPhonenumber(), goods.getSend_phonenumber(), goods.getJianjie(), "0", "0", goods.getImgadress1());
                goodsMapper.someoneContact(goods.getShangpin_ID());
            }else if (needShop!=null){

                orderMapper.orderInsert(shangpin_ID, needShop.getShangpin(), buy_ID, needShop.getSend_ID(),
                        user.getPhonenumber(), needShop.getSend_phonenumber(), needShop.getJianjie(), "0", "0", needShop.getImgadress1());
                needShopMapper.someoneContact(needShop.getShangpin_ID());
            }

        }catch (Exception e){
            matter = "当前买家库存已处理，请重新挑选您需要的商品哦~";
            json.put("matter",matter);
            e.printStackTrace();
            return json;
        }try{

        }catch(Exception e){
            e.printStackTrace();
        }
        json.put("matter",matter);
        return json;
    }

    /**
     *
     */
    public int finishContacting(String sendID,String goodID){
        /*
            结束联系:
            1.先将该商品改成已下架的状态
            2.将对应的订单改成已交易的状态
         */
        int i=0;
        try{
            if (goodService.goodSel(goodID)){
                goodsMapper.shoptokenchange("1",goodID);
                goodsMapper.goodDown1(sendID, goodID);
            }
            else{
                needShopMapper.needTokenChange(goodID);
                needShopMapper.goodDown1(sendID, goodID);
            }
            i++;
        }catch (Exception e){
            e.printStackTrace();
        }try{
            orderMapper.finishContacting(sendID, goodID);
            i++;
        }catch(Exception e){
            e.printStackTrace();
        }
        return i;
    }
}
