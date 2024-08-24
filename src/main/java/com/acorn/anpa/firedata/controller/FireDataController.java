package com.acorn.anpa.firedata.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.code.service.CodeService;
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.firedata.service.FireDataService;
import com.google.gson.Gson;

@Controller
@RequestMapping("firedata")
public class FireDataController implements PLog {
	
	@Autowired
	FireDataService fireDataService;
	
	@Autowired
	CodeService codeService;
	
	public FireDataController() {
		log.debug("┌──────────────────────────────────────────┐");
		log.debug("│ FireDataController()                     │");
		log.debug("└──────────────────────────────────────────┘");
	}
	
	//http://localhost:8080/ehr/firedata/firedata.do
	@GetMapping("/firedata.do")
	public String fireData(Model model) throws SQLException{
		Search search = new Search();
		
		search.setSearchDiv("10");
		search.setDiv("city");
		
		List<Code> cityList = codeService.codeList(search);
		
		model.addAttribute("cityList",cityList);
		
		//최소 최대 날짜만 받는 용도
		Search minMaxDate = fireDataService.minMaxDate();
		model.addAttribute("minMaxDate",minMaxDate);
		//최소 최대 날짜만 받는 용도 end
		
		return "firedata/fire_data";
	}
	
	@RequestMapping(value = "/totalData.do"
			, method = RequestMethod.GET
			, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String totalData(Search search) throws SQLException{
		String jsonString = "";
		log.debug("param: " + search);
		
		//조건 데이터
		Firedata outVO = fireDataService.totalData(search);
		
		//전국 데이터
		search.setSubCityBigNm(null);
		search.setSubCityMidNm(null);
		Firedata total = fireDataService.totalData(search);
		
		List<Firedata> allData = new ArrayList<Firedata>();
		
		allData.add(total);
		allData.add(outVO);
		
		jsonString = new Gson().toJson(allData);
		log.debug("jsonString: "+jsonString);
		return jsonString;
	}
	
	@RequestMapping(value = "/totalDataList.do"
			, method = RequestMethod.GET
			, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String totalDataList(Search search) throws SQLException{
		String jsonString = "";
		log.debug("param: " + search);
		List<Firedata> totalDataList = new ArrayList<Firedata>();;
		
		totalDataList = fireDataService.totalDataList(search);
		
		jsonString = new Gson().toJson(totalDataList);
		log.debug("jsonString: "+jsonString);

		return jsonString;
	}
	
	
	@RequestMapping(value = "/cityList.do"
			, method = RequestMethod.GET
			, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String cityList(Search search) throws SQLException{
		String jsonString = "";
		log.debug("param: " + search);
		
		List<Code> cityList = codeService.codeList(search);
		
		jsonString = new Gson().toJson(cityList);
		log.debug("jsonString: "+jsonString);

		return jsonString;
	}
	
		
		
}
