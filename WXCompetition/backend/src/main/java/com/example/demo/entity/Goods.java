package com.example.demo.entity;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
public class Goods {
    //交易大学名称
    private String university_name;
    //商品名称
    private String shangpin;
    //商品ID号
    private String shangpin_ID;
    //卖家的用户名
    private String maijia;
    //商品简介
    private String jianjie;
    //卖家的电话号码
    private String send_phonenumber;
    //卖家id
    private String send_ID;
    //价格
    private double price;
    //库存
    private int kucun;
    //商品是否已经交易
    private String token;
    //图片1
    private String imgadress1;
    //图片2
    private String imgadress2;
    //图片3
    private String imgadress3;
    //buttonStatu
    private String sendButtonStatu;
    public String getJianjie() {
        return jianjie;
    }

    public String getSend_phonenumber() {
        return send_phonenumber;
    }


    public String getShangpin() {
        return shangpin;
    }

    public String getShangpin_ID() {
        return shangpin_ID;
    }

    public String getSend_ID() {
        return send_ID;
    }

    public String getToken() {
        return token;
    }

    public String getImgadress1() {
        return imgadress1;
    }

    public String getImgadress2() {
        return imgadress2;
    }

    public String getImgadress3() {
        return imgadress3;
    }

    public String getSendButtonStatu() {
        return sendButtonStatu;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "university_name='" + university_name + '\'' +
                ", shangpin='" + shangpin + '\'' +
                ", shangpin_ID='" + shangpin_ID + '\'' +
                ", maijia='" + maijia + '\'' +
                ", jianjie='" + jianjie + '\'' +
                ", send_phonenumber='" + send_phonenumber + '\'' +
                ", send_ID='" + send_ID + '\'' +
                ", price=" + price +
                ", kucun=" + kucun +
                ", token='" + token + '\'' +
                ", imgadress1='" + imgadress1 + '\'' +
                ", imgadress2='" + imgadress2 + '\'' +
                ", imgadress3='" + imgadress3 + '\'' +
                '}';
    }
}
