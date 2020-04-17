package com.kuang;

import org.apache.velocity.runtime.directive.Foreach;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * java爬虫测试类
 */
public class JsoupTest {
    public static void main(String[] args) throws IOException {
        //电脑需要联网
        String url = "https://search.jd.com/Search?keyword=java";

        //jsoup返回的document就是浏览器的document对象
        Document document = Jsoup.parse(new URL(url), 30000);

        Element list = document.getElementById("J_goodsList");
        Elements li = list.getElementsByTag("li");

        for (Element el : li){
            String img = el.getElementsByTag("img").eq(0).attr("source-data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String name = el.getElementsByClass("p-name").eq(0).text();
            System.out.println("======================");
            System.out.println(img);
            System.out.println(name);
            System.out.println(price);
        }

    }
}
