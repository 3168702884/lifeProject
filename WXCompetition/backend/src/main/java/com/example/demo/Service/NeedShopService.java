package com.example.demo.Service;

import com.example.demo.Dao.NeedShopMapper;
import com.example.demo.Dao.OrderMapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.entity.Goods;
import com.example.demo.entity.NeedShop;
import com.example.demo.entity.User;
import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class NeedShopService {
    @Autowired
    private NeedShopMapper needShopMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    public JSONArray showNeeds(String wxID, int page, HttpServletResponse response){
        JSONArray jsonArray = JSONArray.fromObject(needShopMapper.showNeeds(wxID,page*6));
        JSONObject jsonError = new JSONObject();//校验查询是否为空
        if (jsonArray.isEmpty()) {//校验机制
            jsonError.put("error", "没有查询到内容");
        }
        page++;
        //查询第二页是否为空，如果为空则添加page状态码
        JSONArray jsonArray1 = JSONArray.fromObject(needShopMapper.showNeeds(wxID,page*6));
        if (jsonArray1.isEmpty()){
            response.setHeader("loadStatus","nomore");
        }else{
            response.setHeader("loadStatus","loadmore");
        }
        return jsonArray;
    }

    /**
     *
     * @param buy_ID
     * @param needID
     * @return
     */
    public JSONObject wantBuy( String buy_ID,  String needID){
        JSONObject json = new JSONObject();
        String matter = "信息已发送，等待卖方确认";
        //查询买家信息
        User user = userMapper.userselect(buy_ID);
        NeedShop needShop = new NeedShop();
        try{
            //获取对应对应的商品信息
            needShop = needShopMapper.needDetail(needID);
        }catch(Exception e){
            matter = "获取商品失败";
            json.put("matter",matter);
            e.printStackTrace();
            return json;
        }
        try {
            System.out.println(user);
            orderMapper.orderInsert(needID, needShop.getShangpin(),buy_ID, needShop.getSend_ID()
                    ,user.getPhonenumber(), needShop.getSend_phonenumber(), needShop.getJianjie(),"0","0",needShop.getImgadress1());
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
    //需求信息下架
    public String needShopDown(String sendID,String goodID){
        try{
            needShopMapper.goodDown(sendID, goodID);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            needShopMapper.goodDown1(sendID, goodID);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "已下架";
    }
    public Boolean uploadImg(String goodsID, MultipartFile file) {

        // 判断文件类型
        String contentType = file.getContentType();

        System.out.println("contentType = " + contentType);
        // 上传图片的文件名
        String fileName = file.getOriginalFilename();
        // 判断文件是否为空
        if (file.isEmpty()) {
            // 上传失败
            return false;
        }
        String filePath = "G:/JAVA/IDEA/workspace/imgs";
        try {
            uploadFile(filePath, file.getBytes(), fileName);
        } catch (Exception e) {
            System.out.println("保存异常->" + e);
        }
        String imgAdress = "http://192.168.43.111:8080/Images/" + fileName;
        try {
            System.out.println(goodsID);
            imgAdd(goodsID, imgAdress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 上传成功
        return true;
    }
    public static void uploadFile(String filePath,byte[] file, String fileName) throws Exception {
        // 图片本地存储地址

        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            // 创建抽象路径名指定的目录，包括创建必需但不存在的父目录
            Boolean isSuccess = targetFile.mkdirs();
            System.out.println(isSuccess);
        }
        System.out.println(filePath + "/" + fileName);
        // 保存图片
        FileOutputStream out = new FileOutputStream(filePath + "/" + fileName);
        // 输出
        out.write(file);
        // 关闭文件流
        out.flush();
        out.close();
        // 判断照片大小是否需要压缩
        int lth = 61440;
        if (lth < file.length) {
            compressImage(filePath + "/" + fileName);
        }

    }
    public static void compressImage(String path) {
        try {
            BufferedImage thumbnail = Thumbnails.of(path).scale(0.5f).asBufferedImage();
            ImageIO.write(thumbnail, "jpg", new File(path));
            System.out.println("<-图片压缩成功->");
        } catch (IOException e) {
            System.out.println("图片压缩出现IO异常->" + e);
        }
    }
    public void imgAdd(String goodsID,String imgAdress){
        NeedShop needShop =needShopMapper.needDetailGet(goodsID);
        try {
            needShopMapper.imgadd1(goodsID,imgAdress);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
