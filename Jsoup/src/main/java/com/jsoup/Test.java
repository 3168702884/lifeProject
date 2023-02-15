package com.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import static org.jsoup.Jsoup.*;

public class Test {
    public static void main (String[] args) throws Exception{

        /*可以通过传入参数拼接URL传入不同的网址*/
        String url = "https://www.zcool.com.cn/";

        /* 解析地址 */
        Document document = parse(new URL(url), 10000);
        /*获取元素*/
        /*获取ID选择器时可以直接内容.出来获取tag元素进行获取*/
        Elements content = document.getElementsByClass("card-img");
        /*获取类选择时需要这种写法*/
        /*如果网站是懒加载，在获取图片的类时要获取懒加载类如：lazy-img*/
        Elements imgs = content.select("img");
        int id=0;
        for (Element img : imgs) {
            /*获取img下的src地址*/
            String src = img.attr("src");
            //获取输入流,把URL作为内容输入到输入流
            URL target=new URL(src);
            URLConnection urlConnection = target.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            id++;
            //获取输出流，定义输出流的文件夹
            OutputStream outputStream=new FileOutputStream("G:\\JAVA\\IDEA\\workspace\\pachong-jsoup\\src\\main\\resources\\static\\"+id+".jpg");
            int temp=0;
            while ((temp=inputStream.read())!=-1){
                outputStream.write(temp);
            }
            System.out.println(id+".jpg下载完了");

            outputStream.close();
            inputStream.close();
        }

    }
}
 /*一些使用实例
        * File input = new File("/tmp/input.html");
            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

            Elements links = doc.select("a[href]"); //带有href属性的a元素
            Elements pngs = doc.select("img[src$=.png]");
        //扩展名为.png的图片

            Element masthead = doc.select("div.masthead").first();
        //class等于masthead的div标签

            Elements resultLinks = doc.select("h3.r > a"); //在h3元素之后的a元素
            *
            *
            * String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
　　Document doc = Jsoup.parse(html);//解析HTML字符串返回一个Document实现
　　Element link = doc.select("a").first();//查找第一个a元素

　　String text = doc.body().text(); // "An example link"//取得字符串中的文本
　　String linkHref = link.attr("href"); // "http://example.com/"//取得链接地址
　　String linkText = link.text(); // "example""//取得链接地址中的文本

　　String linkOuterH = link.outerHtml();
 　　   // "<a href="http://example.com"><b>example</b></a>"
　　String linkInnerH = link.html(); // "<b>example</b>"//取得链接内的html内容*/
