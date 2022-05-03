package com.example.demo.until;

import com.example.demo.Dao.GoodsMapper;
import com.example.demo.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.TimerTask;

public class TimerTaskUntil extends TimerTask {
    private String shangpin_ID;
    public TimerTaskUntil(String shangpin_ID){
        this.shangpin_ID = shangpin_ID;
    }
    @Autowired
    private GoodsMapper goodsMapper;
    public void run(){
        System.out.println("倒计时开始");
        Goods goods = goodsMapper.shopsel(shangpin_ID);
        if (goods.getToken().equals("1")){
            System.out.println("你的订单还未完成，若在一小时之内不完成，系统将默认已收货");
        }
    }
}
