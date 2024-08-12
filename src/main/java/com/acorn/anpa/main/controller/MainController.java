package com.acorn.anpa.main.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.firedata.service.FireDataService;

@Controller
@RequestMapping("main")
public class MainController implements PLog {
	
	@Autowired
	FireDataService fireDataService;
	
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
		
		return viewName;		
	}
}
