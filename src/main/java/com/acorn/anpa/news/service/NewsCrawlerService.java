package com.acorn.anpa.news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsCrawlerService {

    public List<Map<String, String>> crawlNaverNews(String keyword, String date) throws IOException {
    	List<Map<String, String>> newsList = new ArrayList<>();
    	
        for (int page = 1; page <= 5; page++) { // 예를 들어 5페이지까지 가져온다고 가정
        	
	        String url = "https://search.naver.com/search.naver?&where=news&query=" + keyword 
	                + "&start=" + (page - 1) * 10 + 1 + "&nso=so%3Ar%2Cp%3Afrom" + date + "to" + date;
	        Document doc = Jsoup.connect(url).get();
	        
	        Elements newsElements = doc.select("ul.list_news > li.bx");
	
	        for (Element element : newsElements) {
	            String title = element.select("a.news_tit").text();
	            String contents = element.select("a.api_txt_lines").text();
	            String link = element.select("a.news_tit").attr("href");
	            
	            Map<String, String> newsItem = new HashMap<>();
	            newsItem.put("link", link);
	            newsItem.put("contents", contents);
	            newsItem.put("title", title);
	            newsList.add(newsItem);
	        }
	        
        }
        return newsList;
    }
}

