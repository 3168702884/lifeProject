package com.example.demo.entity;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
public class Order {
    //商品名称
    private String shangpin;
    //商品ID号
    private String shangpin_ID;
    //买家的微信ID
    private String buy_ID;
    //卖家的微信ID
    private String send_ID;
    //买家电话号码
    private String buy_phonenumber;
    //卖家的电话号码
    private String send_phonenumber;
    //商品简介
    private String jianjie;
    //买信号
    private String buy_signal;
    //卖信号
    private String send_signal;
    //单价
    private double price;
    //总价
    private double lastprice;
    //库存
    private int number;
    //图片1
    private String imgadress1;
    //图片2
    private String imgadress2;
    //图片3
    private String imgadress3;

    public String getShangpin_ID() {
        return shangpin_ID;
    }


    public void setShangpin_ID(String shangpin_ID) {
        this.shangpin_ID = shangpin_ID;
    }

    public String getSend_ID() {
        return send_ID;
    }

    public void setSend_ID(String send_ID) {
        this.send_ID = send_ID;
    }

    public double getLastprice() {
        return lastprice;
    }

    public int getNumber() {
        return number;
    }

    public String getBuy_ID() {
        return buy_ID;
    }

    public String getBuy_phonenumber() {
        return buy_phonenumber;
    }

    public String getBuy_signal() {
        return buy_signal;
    }

    public String getJianjie() {
        return jianjie;
    }

    public String getSend_phonenumber() {
        return send_phonenumber;
    }

    public String getSend_signal() {
        return send_signal;
    }

    public double getPrice() {
        return price;
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

    public String getShangpin() {
        return shangpin;
    }

    public void setBuy_ID(String buy_ID) {
        this.buy_ID = buy_ID;
    }

    public void setBuy_phonenumber(String buy_phonenumber) {
        this.buy_phonenumber = buy_phonenumber;
    }

    public void setBuy_signal(String buy_signal) {
        this.buy_signal = buy_signal;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public void setLastprice(double lastprice) {
        this.lastprice = lastprice;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSend_phonenumber(String send_phonenumber) {
        this.send_phonenumber = send_phonenumber;
    }

    public void setSend_signal(String send_signal) {
        this.send_signal = send_signal;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
