package com.acorn.anpa.firedata.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.code.service.CodeService;
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
	public String fireData() throws SQLException{
		
		return "firedata/fire_data";
	}
	
	@RequestMapping(value = "/cityList.do"
			, method = RequestMethod.GET
			, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String cityList(Search search) throws SQLException{
		String jsonString = "";
		log.debug("param: " + search);
		
		
		//----------------------------------------------------------------------
		List<Code> cityList = codeService.codeList(search);
		
		
		//----------------------------------------------------------------------
		
		jsonString = new Gson().toJson(cityList);
		log.debug("jsonString: "+jsonString);

		return jsonString;
	}
	
		
		
}
