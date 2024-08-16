package com.acorn.anpa.main.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.firedata.service.FireDataService;
import com.acorn.anpa.monthfiredata.service.MonthFireDataService;

@Controller
@RequestMapping("main")
public class MainController implements PLog {
	
	@Autowired
	FireDataService fireDataService;

	@Autowired
	MonthFireDataService monthFireDataService;
	
	public MainController() {
		log.debug("┌──────────────────────────────────────────┐");
		log.debug("│ mainController()                     │");
		log.debug("└──────────────────────────────────────────┘");
	}
	
	@RequestMapping(
			value = "/index.do",
			method = RequestMethod.GET,
			produces = "text/plain;charset=UTF-8"
	)		// produces : 화면으로 전송할 때 encoding
	public String mainData(Model model) throws SQLException{
		String viewName = "main/index";
		
		Firedata firedata = fireDataService.doMainData();
		model.addAttribute("firedata", firedata);
		
		// 현재 날짜 구하기
		LocalDate sysDate = LocalDate.now();
		
        // 날짜를 저장할 리스트를 생성합니다.
        List<String> allDates = new ArrayList<>();

        // yyyy mm 형식으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        
        // 현재 날짜와 6개월 전까지의 날짜를 계산합니다.
        for (int i = 0; i <= 6; i++) {
            LocalDate allDate = sysDate.minusMonths(i);
            String formattedAllDate = allDate.format(formatter);
            allDates.add(formattedAllDate);
        }
		
        // Firedata 객체를 생성하고 각각의 regDt를 설정합니다.
        List<Firedata> inVOList = new ArrayList<>();
        for (String dateStr : allDates) {
            Firedata inVO = new Firedata();
            inVO.setRegDt(dateStr);
            inVOList.add(inVO);
        }

		// 현재 월 장소 대분류 화재 순위
		List<Firedata> rankLbData = monthFireDataService.locBigData(inVOList.get(0));
		model.addAttribute("rankLbData", rankLbData);
		
		// 현재 월 장소 중분류 화재 순위
		List<Firedata> rankLmData = monthFireDataService.locMidData(inVOList.get(0));
		model.addAttribute("rankLmData", rankLmData);		
		
		// 현재 월 요인 중분류 화재 순위
		List<Firedata> rankFmData = monthFireDataService.factorMidData(inVOList.get(0));
		model.addAttribute("rankFmData", rankFmData);
		
	    Map<String, Firedata> monthFrDataMap = new HashMap<>();

	    // 각 inVO 객체를 조회하고 Map에 저장합니다.
	    for (int i = 0; i < inVOList.size(); i++) {
	        Firedata inVO = inVOList.get(i);
	        Firedata monthFrData = monthFireDataService.todayMonthData(inVO);

	        // 키를 "monthFrData01", "monthFrData02" 형식으로 생성합니다.
	        String key = "monthFrData" + String.format("%02d", i);
	        monthFrDataMap.put(key, monthFrData);
	    }

	    // 모델에 데이터를 추가합니다. 현재달부터 6개월 전까지
    	// model.addAttribute("monthFrData0[0~6]", monthFrData0[0~6]) 형식으로 모델에 추가
	    for (Map.Entry<String, Firedata> entry : monthFrDataMap.entrySet()) {
	        model.addAttribute(entry.getKey(), entry.getValue());
	    }
		
		return viewName;		
	}
}
