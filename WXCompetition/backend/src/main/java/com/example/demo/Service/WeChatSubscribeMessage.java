package com.example.demo.Service;//package com.example.demo.Service;
//
//import net.sf.json.JSONObject;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import com.example.demo.utils.HttpSends;
//
///**
// * @author Buer
// * @update 2021/4/27/02713:46
// */
//@Component
//public class WeChatSubscribeMessage {
//    // 全局配置appid
//    @Value("${app.openid}")
//    private String appid;
//    // 全局配置secret
//    @Value("${app.secret}")
//    private String secret;
//    // 全局配置templateID
//    @Value("${app.template}")
//    private String template;
//    // 全局配置获取access_token的url
//    @Value("${app.url2}")
//    private String url2;
//    // 全局配置发送模板消息的url
//    @Value("${app.url3}")
//    private String url3;
//
//    /*获取access_token*/
//    public void getAccessToken() {
//        HttpSends httpSends = new HttpSends();
//        // 转换登录请求网址
//        String url = url2.replace("APPID", appid).replace("APPSECRET", secret);
//        // 请求信息,返回字符串
//        String result = httpSends.sendGet(url);
//        // 字符串转JSON格式
//        JSONObject jsonobject = JSONObject.fromObject(result);
//        sendSubscribeMessage(jsonobject.getString("access_token"));
//    }
//
//    /*获取access_token发送订阅消息*/
//    public void sendSubscribeMessage(String token) {
//        HttpSends httpSends = new HttpSends();
//        // 字符串格式模板数据
//        String str =
//            "{\"touser\":\"OPENID\",\"template_id\":\"TEMPLATE\",\"page\":\"index\",\"miniprogram_state\":\"developer\",\"lang\":\"zh_CN\",\"data\":{\"thing2\":{\"value\":\"SELLER\"},\"time3\":{\"value\":\"TIME\"},\"thing4\":{\"value\":\"GOODS\"},\"thing5\":{\"value\":\"订单时间快到了，请尽快完成交易！\"}}}";
//        // 替换用户openid,模板id,卖家昵称，截止日期，商品名称
//        String param = str.replace("OPENID", "obnVb5MyM-kq0X3RJG5Jyq9S7wj8").replace("TEMPLATE", template)
//            .replace("SELLER", "梦轩小屋").replace("TIME", "2021-04-27 20:50:55").replace("GOODS", "二手电竞椅");
//        // 转换登录请求网址
//        String url = url3.replace("ACCESS_TOKEN", token);
//        String result = httpSends.sendPost(url, param);
//        // 判断订阅消息是否发送成功
//        boolean isSuccess = result.contains("ok");
//        // 答应返回数据
//        if (isSuccess) {
//            System.out.println("模板消息发送成功->" + result);
//        } else {
//            System.out.println("模板消息发送失败->" + result);
//        }
//    }
//}
