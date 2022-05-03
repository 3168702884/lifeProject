package com.example.demo.Dao;

import com.example.demo.entity.Goods;
import com.example.demo.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;

@Mapper
@Component
public interface OrderMapper {
    //交易订单表的创建，用于存储各个交易的状态
    @Update("create table if not exists JiaoYiZhong(" +
            "shangpin_ID char(50) not null," +
            "shangpin char(100) not null," +
            "buy_ID char(30) not null," +
            "send_ID char(30) not null," +
            "buy_phonenumber varchar(11) not null," +
            "send_phonenumber varchar(11) not null," +
            "jianjie varchar(300) not null," +
            "buy_signal enum('0','1','2') not null," +
            "send_signal enum('0','1','2') not null," +
            "last_price double not null," +
            "count int not null," +
            "dingdan char(30) not null "+
            "id int not null auto_increment," +
            "primary key(id))")
    public int JiaoYiZhongCreate();
    @Insert("Insert into jiaoyizhong (shangpin_ID,shangpin,buy_ID,send_ID," +
            "buy_phonenumber,send_phonenumber,jianjie,buy_signal,send_signal,imgadress1)values(#{shangpin_ID}," +
            "#{shangpin},#{buy_ID},#{send_ID},#{buy_phonenumber},#{send_phonenumber},#{jianjie}," +
            "#{buy_signal},#{send_signal},#{imgadress1})")
    public int orderInsert(String shangpin_ID,String shangpin,String buy_ID,
                                 String send_ID,String buy_phonenumber,String send_phonenumber,
                                 String jianjie,String buy_signal,String send_signal, String imgadress1);
    @Update("update jiaoyizhong set send_signal=#{send_signal} where shangpin_ID=#{shangpin_ID} and buy_ID=#{buyID}")
    public int wantsend(String buyID,String send_signal,String shangpin_ID);
    @Select("select * from jiaoyizhong where shangpin_ID=#{shangpin_ID}")
    public Order findBySpID(String shangpin_ID);
    @Update("update jiaoyizhong set buy_signal=#{buy_signal} where shangpin_ID=#{shangpin_ID} and buy_ID=#{buyID}")
    public int dontwantbuy(String buyID,String shangpin_ID,String buy_signal);
    @Update("update jiaoyizhong set send_signal=#{send_signal} where shangpin_ID=#{shangpin_ID} and buy_ID=#{buyID}")
    public int dontwantsend(String buyID,String shangpin_ID,String send_signal);
    //买方确认收货
    @Update("update table jiaoyizhong set buy_signal=#{buy_signal} where shangpin_ID=#{shangpin_ID}")
    public int finish(String buy_signal,String shangpin_ID);
    @Update("update jiaoyizhong set token=#{status} where shangpin_ID=#{goodsID}")
    public int updtoken(String goodsID,String status);
    //获取卖家电话号码
    @Select("select send_phonenumber,send_signal,buy_signal from jiaoyizhong where buy_ID=#{wxID} and shangpin_ID=#{shangpin_ID}")
    public Order numberGet(String wxID, String shangpin_ID);
    //获取作为买家的订单
    @Select("Select * from jiaoyizhong where buy_ID=#{wxID} and token=#{status} and send_signal!='2'")
    public ArrayList<Order> buyOrdersGet(String wxID,String status);
    //获取买家电话号码
    @Select("Select buy_phonenumber from jiaoyizhong where send_ID=#{wxID} and shangpin_ID=#{shangpin_ID} and send_Signal='1'")
    public String buyNumberGet(String wxID,String shangpin_ID);
    //买方的商品详情页
    @Select("select * from jiaoyizhong where shangpin_ID=#{goodID} and buy_ID=#{buyID}")
    public Order buyOrderGet(String buyID,String goodID);
    //卖方的商品详情页
    @Select("select * from jiaoyizhong where shangpin_ID=#{goodID} and send_ID=#{sendID}")
    public Order sendOrderGet(String goodID,String sendID);
    @Delete("delete from jiaoyizhong where buy_ID=#{buyID} and shangpin_ID=#{goodID}")
    public int delShopByCustumer(String buyID,String goodID);
    //获取买家的id
    @Select("select buy_ID from jiaoyizhong where send_ID = #{sendID} and send_Signal!='2' and shangpin_ID=#{goodID}")
    public List<String> buyIDget(String sendID,String goodID);
    //更改其余买家的sendSignal
    @Update("update jiaoyizhong set send_signal='2' where send_ID=#{sendID} and buy_ID!=#{buyID} and shangpin_ID=#{goodID}")
    int updateOtherSendSignal(String sendID,String buyID,String goodID);
    //拒绝买家的请求
    @Update("update jiaoyizhong set send_signal = '2' where send_ID=#{sendID} and buy_ID=#{buyID} and shangpin_ID=#{goodID}")
    int reduceBuy(String sendID,String buyID,String goodID);
    //结束联系
    @Update("update jiaoyizhong set token = '1' where send_ID=#{sendID} and shangpin_ID=#{goodID} and send_Signal = '1'")
    int finishContacting(String sendID,String goodID);
}
