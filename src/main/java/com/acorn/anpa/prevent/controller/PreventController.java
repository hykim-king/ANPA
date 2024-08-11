package com.acorn.anpa.prevent.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	
//	@GetMapping("/preventData.do")
//	public String doSelectpre(Model model, HttpServletRequest req) throws SQLException {
//		String viewName = "prevent";
//		
//		Search search = new Search();	
//		
//		//pageSize=10 (기본값)
//		String pageSize = StringUtil.nvl(req.getParameter("pageSize"), "10");		
//		//pageNo=1 (기본값)
//		String pageNo = StringUtil.nvl(req.getParameter("pageNo"), "1");
//		
//		search.setPageSize(Integer.parseInt(pageSize));
//		search.setPageNo(Integer.parseInt(pageNo));
//		
//		List<Firedata> list = preventService.doRetrieve(search);
//		
//		// 모델에 튜닝 리스트, 검색 조건
//		model.addAttribute("list", list);
//		model.addAttribute("search", search);
//		// 모델에 튜닝 리스트, 검색 조건
//		
//		Code code = new Code();
//
//		
//		// 모델에 튜닝 토탈 카운트
//		int totalCnt = 0;
//		if(null!=list && list.size()>0) {
//			Firedata firedata = list.get(0);
//			totalCnt = firedata.getTotalCnt();
//		}		
//		model.addAttribute("totalCnt", totalCnt);
//		// 모델에 튜닝 토탈 카운트
//		
//		// 모델에 튜닝 페이지 사이즈
//		code.setMasterCode("COM_PAGE_SIZE"); // 페이지 사이즈
//		List<Code> comPageSize = this.codeService.doRetrieve(code);
//		model.addAttribute("COM_PAGE_SIZE", comPageSize); // 검색조건
//		// 모델에 튜닝 페이지 사이즈
//		
//		return viewName;
//	}

}

