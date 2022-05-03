package com.example.demo.Service;

import com.example.demo.Dao.GoodsMapper;
import com.example.demo.Dao.NeedShopMapper;
import com.example.demo.Dao.OrderMapper;
import com.example.demo.Dao.UserMapper;
import com.example.demo.entity.Goods;
import com.example.demo.entity.User;
import com.sun.el.parser.BooleanNode;
import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
@Service
public class GoodService {
    //商品上架时的
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NeedShopMapper needShopMapper;
    public void imgAdd(String goodsID,String imgAdress){
        Goods goods =goodsMapper.shopsel(goodsID);
        try {
            goodsMapper.imgadd1(goodsID, imgAdress);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public String goodIDCreate(){
        //截取当前的毫秒数
        long millis = System.currentTimeMillis();
        //添加三位随机数
        Random random = new Random();
        int i = random.nextInt(999);
        //不足三位则前面补0
        String str = millis+String.format("%03d",i);
        return str;
    }
    public String goodDown(String sendID,String goodID){
        try {
            goodsMapper.goodDown(sendID, goodID);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            goodsMapper.goodDown1(sendID,goodID);
        }catch(Exception e){
            e.printStackTrace();
        }
            return "已下架";
    }
    public JSONArray sendOrderGet(String sendID){
        //查询卖家上架商品
        JSONArray jsonArray = new JSONArray();
        List<Goods> list = new ArrayList<Goods>();
        list = goodsMapper.sendOrdersGet(sendID,"0");
        jsonArray.add(list);
        return jsonArray;
    }
    public Boolean uploadImg(String goodsID,MultipartFile file) {

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



    /**
     * 压缩图片(需要引入依赖thumbnailator)
     */
    public static void compressImage(String path) {
        try {
            BufferedImage thumbnail = Thumbnails.of(path).scale(0.5f).asBufferedImage();
            ImageIO.write(thumbnail, "jpg", new File(path));
            System.out.println("<-图片压缩成功->");
        } catch (IOException e) {
            System.out.println("图片压缩出现IO异常->" + e);
        }
    }

    /**
     * 存储图片至本地
     * 处理照片是否压缩
     */
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

    /**
     * 图片(传入图片本地地址)转码Base64
     */
    private static String imageToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            // 从所包含的输入流中读取一定数量的字节，并存储到缓冲区数组data中。
            int i = in.read(data);
            System.out.println("输入流中读取的字节数-> " + i);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Base64.Encoder encoder = Base64.getEncoder();
//        return encoder.encode(Objects.requireNonNull(data));
        return encoder.encodeToString(data);
    }
    public Boolean goodSel(String goodornnedID){
        Goods goods = goodsMapper.shopsel(goodornnedID);
        if (goods != null){
            return true;
        }else{
            return false;
        }
    }
}

