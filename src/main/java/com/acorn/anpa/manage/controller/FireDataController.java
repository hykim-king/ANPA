package com.acorn.anpa.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.firedata.service.FireDataService;

@Controller
@RequestMapping("manage")
public class FireDataController implements PLog{

	@Autowired
	FireDataService fireDataService;
	
	public FireDataController() {
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ FireDataController()");
		log.debug("└──────────────────────────────────────────────");
	}
	

	
}
