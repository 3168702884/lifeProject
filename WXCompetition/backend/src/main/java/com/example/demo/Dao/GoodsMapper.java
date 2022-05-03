package com.example.demo.Dao;

import com.example.demo.entity.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Component
public interface GoodsMapper {
    //商品上架表的创建，用于存储上架商品
    @Update("create table if not exists shop(" +
            "shangpin_ID char(50) not null," +
            "shangpin char(100) not null," +
            "jianjie varchar(300) not null," +
            "address varchar(300) not null," +
            "send_phonenumber varchar(11) not null," +
            "price double not null," +
            "last_price double not null," +
            "kucun int not null," +
            "id int auto_increment," +
            "token enum('0','1') not null default 0"+
            "primary key(id))")
    public int shopCreate();
    @Insert("Insert into shop(shangpin_ID,shangpin,jianjie,address,send_phonenumber,send_ID) " +
            "values(#{shangpin_ID},#{shangpin},#{jianjie},#{address},#{send_phonenumber},#{sendID}" +
            ")")
    public int shopInsert( String shangpin_ID, String shangpin, String jianjie,
                           String address, String send_phonenumber,String sendID);
    @Select("select * from shop where shangpin_ID=#{shangpin_ID}")
    public Goods shopsel(String shangpin_ID);
    @Update("update shop set token=#{token} where shangpin_ID=#{shangpin_ID}")
    public int shoptokenchange(String token,String shangpin_ID);
    // 逆序分页展示全部商品
    @Select("select * from shop where address = (select universityname from user where wxID = #{wxID}) and token='0' and send_ID!=#{wxID} order by id desc  limit #{start},6")
    public List<Goods> selectShop(String wxID, int start);
    // 逆序分页模糊查询
    @Select("select * from shop where address = (select universityname from user where wxID = #{wxID}) and shangpin like #{str} and token='0' order by id desc limit #{start},#{end}")
    public List<Goods> likeShop(String wxID, int start, int end, String str);
    //更改商品图片1的数据
    @Update("update shop set imgadress1 = #{imgadress1} where shangpin_ID = #{goodsID}")
    public int imgadd1(String goodsID,String imgadress1);
    //更改商品图片2的数据
    @Update("update shop set imgadress1 = #{imgadress2} where shangpin_ID = #{goodsID}")
    public int imgadd2(String goodsID,String imgadress2);
    //更改商品图片3的数据
    @Update("update shop set imgadress1 = #{imgadress3} where shangpin_ID = #{goodsID}")
    public int imgadd3(String goodsID,String imgadress3);
    //卖家点击下架处理,1.更改商品状态
    @Update("update shop set token='1' where send_ID = #{sendID} and shangpin_ID = #{goodID}")
    int goodDown(String sendID,String goodID);
    //更改返回按钮状态
    @Update("update shop set sendButtonStatu = '已下架' where send_ID=#{sendID} and shangpin_ID=#{goodID}")
    int goodDown1(String sendID,String goodID);
    //获取作为卖家的订单
    @Select("Select * from shop where send_ID=#{send_ID} and sendButtonStatu!='已下架'")
    public ArrayList<Goods> sendOrdersGet(String send_ID, String token);
    //更改商品状态为有人联系
    @Update("update shop set sendButtonStatu='有人联系' where shangpin_ID=#{goodID}")
    int someoneContact(String goodID);
    //更改商品状态为正在联系
    @Update("update shop set sendButtonStatu='正在联系' where shangpin_ID=#{goodID}")
    int connacting(String goodID);

}
