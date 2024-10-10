package com.acorn.anpa.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.news.service.NewsCrawlerService;

import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("crawl")
public class NewsCrawlerController implements PLog{

    @Autowired
    NewsCrawlerService newsCrawlerService;

    @RequestMapping(
            value = "/news.do",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8"
    )
    @ResponseBody
    public ResponseEntity<List<Map<String, String>>> crawlNews(@RequestParam(required = false) String keyword, @RequestParam(required = false) String date) {
        // 기본값 설정
        if (keyword == null || keyword.isEmpty()) {
            keyword = "화재";
            log.debug("┌──────────────");
            log.debug("│" + keyword);
            log.debug("└──────────────");
        }

        if (date == null || date.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            date = sdf.format(Calendar.getInstance().getTime());
            log.debug("┌──────────────");
            log.debug("│" + date);
            log.debug("└──────────────");
        }else {
            // 날짜 포맷 변환
            date = convertDateFormat(date);  // 호출 결과를 date에 할당
            log.debug("┌──────────────");
            log.debug("│" + date);
            log.debug("└──────────────");
        }
    	
        try {
            List<Map<String, String>> newsList = newsCrawlerService.crawlNaverNews(keyword, date);
            log.debug("┌──────────────");
            log.debug("│ResultKeyWord : " + keyword);
            log.debug("└──────────────");
            log.debug("┌──────────────");
            log.debug("│ResultDate : " + date);
            log.debug("└──────────────");
            return ResponseEntity.ok(newsList);
        } catch (IOException e) {
            e.printStackTrace();
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", "Error while crawling news: " + e.getMessage());
            return ResponseEntity.status(500)
                    .body(Collections.singletonList(errorMap));
        }
    }
    
    // 날짜 형식 YYYYMMDD 변환
    private String convertDateFormat(String date) {
        String[] formats = {"yyyy.MM.dd", "yyyy/MM/dd", "yyyy-MM-dd"};
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMdd");
        Date parsedDate = null;

        for (String format : formats) {
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat(format);
                parsedDate = inputFormat.parse(date);
                return outputFormat.format(parsedDate); // 성공적으로 파싱되면 변환된 문자열 반환
            } catch (ParseException e) {
                // 다른 형식으로 시도
            }
        }

        // 모든 형식이 실패할 경우 예외 처리
        throw new IllegalArgumentException("Invalid date format: " + date);
    } 
}
