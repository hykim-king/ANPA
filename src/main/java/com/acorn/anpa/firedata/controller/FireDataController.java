package com.acorn.anpa.firedata.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.code.service.CodeService;
import com.acorn.anpa.firedata.service.FireDataService;

@Controller
@RequestMapping("firedata")
public class FireDataController implements PLog {
	
	@Autowired
	FireDataService fireDataService;
	
	@Autowired
	CodeService codeService;
	
	public FireDataController() {
		log.debug("┌──────────────────────────────────────────┐");
		log.debug("│ FireDataController()                        │");
		log.debug("└──────────────────────────────────────────┘");
	}
	
	//http://localhost:8080/ehr/firedata/fireData.do
	@GetMapping("/fireData.do")
	public String fireData() throws SQLException{
		
		return "firedata/fire_data";
	}
	
}
