package com.pcwk.ehr.board.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pcwk.ehr.board.domain.Board;
import com.pcwk.ehr.board.service.BoardService;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.Search;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.code.domain.Code;
import com.pcwk.ehr.code.service.CodeService;

@Controller
@RequestMapping("board")
public class BoardController implements PLog{
	@Autowired
	BoardService boardService;
	
	@Autowired
	CodeService codeService;
	
	public BoardController() {
		log.debug("┌─────────────────────────");
		log.debug("│ BoardController()");
		log.debug("└─────────────────────────");	
	}
	
	@GetMapping("/doRetrieve.do")
	public String doRetrieve(Model model, HttpServletRequest req) throws SQLException {
		String viewName = "board/board_list";
		
		Search search = new Search();
		
		String searchDiv = StringUtil.nvl(req.getParameter("searchDiv"), "");
		search.setSearchDiv(searchDiv);
		
		String searchWord = StringUtil.nvl(req.getParameter("searchWord"), "");
		search.setSearchWord(searchWord);
		
		//pageSize=10 (기본값)
		String pageSize = StringUtil.nvl(req.getParameter("pageSize"), "10");		
		//pageNo=1 (기본값)
		String pageNo = StringUtil.nvl(req.getParameter("pageNo"), "1");
				
		//div값이 없으면 전체 조회
		String div = StringUtil.nvl(req.getParameter("div"), "");
		search.setDiv(div);
		
		List<Board> list = boardService.doRetrieve(search);
		log.debug("list : " + list);
		
		model.addAttribute("list", list);
		model.addAttribute("search", search);
		
		
		//============================================
		Code code = new Code();
		
		
		//============================================
		return viewName;
	}
	
}
