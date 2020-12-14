package com.yky.utils;

import com.yky.pojo.TodayHeadlines;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yky
 * @CreateTime: 2020-08-14
 * @Description: 解析新浪搜索网页工具类
 */
@Component
public class SinaWeiboHtmlParseUtils {

    public static List<TodayHeadlines> parseSina(String keyword,String page) throws IOException {
        //https://search.sina.com.cn/?q=&c=news&from=channel&col=&range=all&source=&country=&size=10&stime=&etime=&time=&dpc=0&a=&ps=0&pf=0&page=2
        //String url = "https://search.sina.com.cn/?q=" + keyword +"&c=news&from=channel&ie=utf-8";
        String url =  "https://search.sina.com.cn/?q="+keyword+"&c=news&from=channel&col=&range=all&source=&country=&size=10&stime=&etime=&time=&dpc=0&a=&ps=0&pf=0&page="+page;
        //解析网页  document ==> 就是js页面对象
        Document document = Jsoup.parse(new URL(url), 30000);
        //Document document = Jsoup.connect(url).get();
        Element result = document.getElementById("result");
        Elements elements = result.getElementsByClass("box-result clearfix");
        //封装成list集合
        List<TodayHeadlines> contents = new ArrayList<>();
        //获取元素中的内容
        for (Element el : elements) {
            TodayHeadlines todayHeadlines = new TodayHeadlines();
            String link = el.getElementsByTag("a").attr("href");
            String title = el.getElementsByTag("a").eq(0).text();
            String time = el.getElementsByClass("fgray_time").eq(0).text();
            String content = el.getElementsByClass("content").eq(0).text();
            String imgLink = el.getElementsByTag("img").attr("src");
            // ==todo==如果图片为空，补充一张图片
            //if(StringUtils.isEmpty(imgLink)){
            //    imgLink = "";
            //}

            todayHeadlines.setTitle(title);
            todayHeadlines.setLink(link);
            todayHeadlines.setTime(time);
            todayHeadlines.setContent(content);
            todayHeadlines.setImgIlink(imgLink);
            contents.add(todayHeadlines);
        }
        return contents;
    }

}
