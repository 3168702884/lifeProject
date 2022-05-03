package com.example.demo.Dao;

import com.example.demo.entity.Goods;
import com.example.demo.entity.NeedShop;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Component
public interface NeedShopMapper {
    @Select("select * from needShop where address = (select universityname from user where wxID = #{wxID}) and token='0' and send_ID!=#{wxID} order by id desc  limit #{page},6")
    public ArrayList<NeedShop> showNeeds(String wxID,int page);
    //通过对应needID查询到对应的need商品
    @Select("select * from needShop where send_ID = #{needID}")
    public NeedShop needDetail(String needID);
    //逆序模糊查询needsShop中的数据
    @Select("select * from needShop where address = (select universityname from user where wxID = #{wxID}) and shangpin like #{str} and token='0' order by id desc limit #{start},#{end}")
    public List<NeedShop> likeShop(String wxID, int start, int end, String str);
    //卖家点击下架处理,1.更改商品状态
    @Update("update needShop set token='1' where send_ID = #{sendID} and shangpin_ID = #{goodID}")
    int goodDown(String sendID,String goodID);
    //更改返回按钮状态
    @Update("update needShop set sendButtonStatu = '已下架' where send_ID=#{sendID} and shangpin_ID=#{goodID}")
    int goodDown1(String sendID,String goodID);
    //更改状态为1，为下架状态
    @Update("update needShop set token = '1' where shangpin_ID=#{needID}")
    int needTokenChange(String needID);
    @Update("update needShop set sendButtonStatu='正在联系' where shangpin_ID=#{goodID}")
    int connacting(String goodID);
    @Select("Select * from needShop where send_ID=#{send_ID} and sendButtonStatu!='已下架'")
    ArrayList<NeedShop> needShopGets(String needID);
    //通过needID获取对应的需求信息
    @Select("select * from needshop where shangpin_ID = #{goodID} and sendButtonStatu!='已下架'")
    NeedShop needDetailGet(String goodID);
    @Insert("Insert into needshop(shangpin_ID,shangpin,jianjie,address,send_phonenumber,send_ID) " +
            "values(#{needID},#{needs},#{needIntroduction},#{address},#{sendPhonenumber},#{sendID}" +
            ")")
    int needRelease( String needID, String needs, String needIntroduction,
                           String address, String sendPhonenumber,String sendID);
    //更改商品图片1的数据
    @Update("update needshop set imgadress1 = #{imgadress1} where shangpin_ID = #{goodsID}")
    public int imgadd1(String goodsID,String imgadress1);
    //更改商品状态为有人联系
    @Update("update needshop set sendButtonStatu='有人联系' where shangpin_ID=#{goodID}")
    int someoneContact(String goodID);
}
