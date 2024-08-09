package com.acorn.anpa.prevent.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.prevent.service.PreventService;


@Controller
@RequestMapping("prevent")

public class PreventController implements PLog{

	@Autowired
	PreventService preventService;

	public  PreventController() {
		log.debug("┌──────────────────────────────────────────┐");
		log.debug("│ PreventController()                      │");
		log.debug("└──────────────────────────────────────────┘");
	}
	
	@GetMapping("/preventData.do")
    public String preventData() throws SQLException {
        return "prevent/prevent"; 
    }
	

}

