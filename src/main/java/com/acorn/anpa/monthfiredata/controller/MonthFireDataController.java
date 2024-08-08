package com.acorn.anpa.monthfiredata.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.monthfiredata.service.MonthFireDataService;
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
	

}
