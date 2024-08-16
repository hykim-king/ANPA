package com.acorn.anpa.monthfiredata.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.anpa.cmn.Message;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.monthfiredata.service.MonthFireDataService;
import com.google.gson.Gson;
@Controller
@RequestMapping("monthfiredata")
public class MonthFireDataController implements PLog {
	@Autowired
	MonthFireDataService monthFireDataService;

	public MonthFireDataController() {
		log.debug("┌──────────────────────────────────────────┐");
		log.debug("│ MonthFireDataController()                │");
		log.debug("└──────────────────────────────────────────┘");
	}
	
	@GetMapping("/monthFireData.do")
	public String monthFireData() throws SQLException{
		
		return "monthResult/monthResult";
	}
	
	@RequestMapping(
			value = "/selectYear.do",
			method = RequestMethod.GET,
			produces = "text/plain;charset=UTF-8"
			)
	@ResponseBody//데이터만 보여줌
	public String selectYear() throws SQLException {
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ selectYear()");
		log.debug("└──────────────────────────────────────────────");	
		
		String jsonString = "";
		List<String> outVO = monthFireDataService.selectYear();
		log.debug("┌──────────────────────────────────────────────");
		log.debug("1. outVO : " + outVO);
		log.debug("└──────────────────────────────────────────────");
		
		String message = "";
		int flag = 0;
		if(null != outVO) {
			message = "연도가 조회되었습니다.";
			flag = 1;
		}else{
			message = "연도 조회에 실패했습니다.";			
		}
		
		jsonString = new Gson().toJson(new Message(flag, message));
		log.debug("2. jsonString : " + jsonString);
		
		String jsonData = new Gson().toJson(outVO);
		
		String allMessage = "{\"yearData\" : "+jsonData + ",\"message\": "+jsonString+"}";
		
		
		return allMessage;
	}
	
	@RequestMapping(
			value = "/todayMonthData.do",
			method = RequestMethod.GET,
			produces = "text/plain;charset=UTF-8"
			)
	@ResponseBody//데이터만 보여줌
	public String todayMonthData(Firedata inVO) throws SQLException{
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ todayMonthData()");
		log.debug("└──────────────────────────────────────────────");	
		
		String jsonString = "";
		
		log.debug("1. param : " + inVO);
		Firedata outVO = monthFireDataService.todayMonthData(inVO);
		log.debug("┌──────────────────────────────────────────────");
		log.debug("1. outVO : " + outVO);
		log.debug("└──────────────────────────────────────────────");
		
		String message = "";
		int flag = 0;
		if(null != outVO) {
			message = inVO.getRegDt() +"날짜의 화재데이터가 조회되었습니다.";
			flag = 1;
		}else{
			message = inVO.getRegDt() +"날짜의 화재데이터 조회에 실패했습니다.";			
		}
		
		jsonString = new Gson().toJson(new Message(flag, message));
		log.debug("2. jsonString : " + jsonString);
		
		String jsonData = new Gson().toJson(outVO);
		
		String allMessage = "{\"tmData\" : "+jsonData + ",\"message\": "+jsonString+"}";
		
		
		return allMessage;
	}
	
	@RequestMapping(
			value = "/locBigData.do",
			method = RequestMethod.GET,
			produces = "text/plain;charset=UTF-8"
			)
	@ResponseBody
	public String locBigData(Firedata inVO) throws SQLException {
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ locBigData()");
		log.debug("└──────────────────────────────────────────────");	
		
		String jsonString = "";
		
		log.debug("1. param : " + inVO);
		List<Firedata> outVO = monthFireDataService.locBigData(inVO);
		
		String message = "";
		int flag = 0;
		if(null != outVO) {
			message = inVO.getRegDt() +"날짜의 화재데이터가 조회되었습니다.";
			flag = 1;
		}else{
			message = inVO.getRegDt() +"날짜의 화재데이터 조회에 실패했습니다.";			
		}
		
		jsonString = new Gson().toJson(new Message(flag, message));
		log.debug("2. jsonString : " + jsonString);
		
		String jsonlbData = new Gson().toJson(outVO);
		String allMessage = "{\"lbData\" : "+jsonlbData + ",\"message\": "+jsonString+"}";
		
		return allMessage;
		
	}
	
	@RequestMapping(
			value = "/locMidData.do",
			method = RequestMethod.GET,
			produces = "text/plain;charset=UTF-8"
			)
	@ResponseBody
	public String locMidData(Firedata inVO) throws SQLException {
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ locMidData()");
		log.debug("└──────────────────────────────────────────────");	
		
		String jsonString = "";
		
		log.debug("1. param : " + inVO);
		List<Firedata> outVO = monthFireDataService.locMidData(inVO);
		
		String message = "";
		int flag = 0;
		if(null != outVO) {
			message = inVO.getRegDt() +"날짜의 화재데이터가 조회되었습니다.";
			flag = 1;
		}else{
			message = inVO.getRegDt() +"날짜의 화재데이터 조회에 실패했습니다.";			
		}
		
		jsonString = new Gson().toJson(new Message(flag, message));
		log.debug("2. jsonString : " + jsonString);
		
		String jsonlmData = new Gson().toJson(outVO); 
		String allMessage = "{\"lmData\" : "+jsonlmData + ",\"message\": "+jsonString+"}";
		return allMessage;
		
	}
	
	@RequestMapping(
			value = "/factorMidData.do",
			method = RequestMethod.GET,
			produces = "text/plain;charset=UTF-8"
			)
	@ResponseBody
	public String factorMidData(Firedata inVO) throws SQLException {
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ factorMidData()");
		log.debug("└──────────────────────────────────────────────");	
		
		String jsonString = "";
		
		log.debug("1. param : " + inVO);
		List<Firedata> outVO = monthFireDataService.factorMidData(inVO);
		
		String message = "";
		int flag = 0;
		if(null != outVO) {
			message = inVO.getRegDt() +"날짜의 화재데이터가 조회되었습니다.";
			flag = 1;
		}else{
			message = inVO.getRegDt() +"날짜의 화재데이터 조회에 실패했습니다.";			
		}
		
		jsonString = new Gson().toJson(new Message(flag, message));
		log.debug("2. jsonString : " + jsonString);
		
		String jsonfmData = new Gson().toJson(outVO); 
		String allMessage = "{\"fmData\" : "+jsonfmData + ",\"message\": "+jsonString+"}";
		return allMessage;
		
	}
	

}
