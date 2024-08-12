package com.acorn.anpa.prevent.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.cmn.StringUtil;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.firedata.domain.Firedata;
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
	
	
	//http://localhost:8080/ehr/prevent//preventData.do
    @GetMapping("/preventData.do")
    public String preventData() throws SQLException {
	String viewName = "prevent";
	
	return "prevent/prevent";
	}
	
	
	Search search = new Search();	
	
	//pageSize=10 (기본값)
	//String pageSize = StringUtil.nvl(req.getParameter("pageSize"), "10");		
	//pageNo=1 (기본값)
	//String pageNo = StringUtil.nvl(req.getParameter("pageNo"), "1");
	
	//	search.setPageSize(Integer.parseInt(pageSize));
	//search.setPageNo(Integer.parseInt(pageNo));
	


}

