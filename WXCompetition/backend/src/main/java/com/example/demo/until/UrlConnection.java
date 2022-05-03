package com.example.demo.until;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Buer
 */
public class UrlConnection {
    public String getInfo(String urlParam) {

        java.net.URLConnection con = null;

        BufferedReader buffer = null;
        StringBuffer resultBuffer = null;

        try {
            URL url = new URL(urlParam);
            con = url.openConnection();

            // 设置请求需要返回的数据类型和字符集类型
            con.setRequestProperty("Content-Type", "application/json;charset=GBK");
            // 允许写出
            con.setDoOutput(true);
            // 允许读入
            con.setDoInput(true);
            // 不使用缓存
            con.setUseCaches(false);
            // 得到响应流
            InputStream inputStream = con.getInputStream();
            // 将响应流转换成字符串
            resultBuffer = new StringBuffer();
            String line;
            buffer = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            while ((line = buffer.readLine()) != null) {
                resultBuffer.append(line);
            }
            // keyChange.keychange(resultBuffer);
            // user.setWxID(resultBuffer.toString());
            return resultBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }
}
