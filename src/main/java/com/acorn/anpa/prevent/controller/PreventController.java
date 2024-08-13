package com.acorn.anpa.prevent.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.acorn.anpa.prevent.domain.prevent;
import com.acorn.anpa.prevent.service.PreventService;
import com.acorn.anpa.cmn.Message;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.cmn.StringUtil;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.code.service.CodeService;
import com.google.gson.GsonBuilder;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("prevent")
public class PreventController {
    
    private static final Logger log = LoggerFactory.getLogger(PreventController.class);

    @Autowired
    PreventService preventService;

    @Autowired
	CodeService  codeService;
    
    // 생성자
    public PreventController() {
        log.debug("┌──────────────────────────────────────────┐");
        log.debug("│ PreventController()                      │");
        log.debug("└──────────────────────────────────────────┘");
    }

    // 데이터 저장
    @RequestMapping(value = "/doSave.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String doSave(prevent inVO) throws SQLException {
        log.debug("1. param inVO: " + inVO);
        int flag = preventService.doSave(inVO);

        log.debug("2. flag: " + flag);
        String message = "";
        if (1 == flag) {
            message = inVO.getTitle() + " 이 등록되었습니다.";
        } else {
            message = inVO.getTitle() + " 등록 실패!";
        }

        Message messageObj = new Message(flag, message);
        String jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(messageObj);
        log.debug("3. jsonString: " + jsonString);

        return jsonString;
    }

    // 단건 조회
    @RequestMapping(value = "/doSelectOne.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String doSelectOne(prevent inVO) throws SQLException {
        log.debug("1. param inVO: " + inVO);

        prevent outVO = preventService.doSelectOne(inVO);
        log.debug("2. outVO: " + outVO);

        String message = "";
        int flag = 0;
        if (null != outVO) {
            message = outVO.getTitle() + " 이 조회되었습니다.";
            flag = 1;
        } else {
            message = inVO.getTitle() + " 조회 실패!";
        }

        Message messageObj = new Message(flag, message);
        String jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(messageObj);

        String jsonPrevent = new GsonBuilder().setPrettyPrinting().create().toJson(outVO);
        String jsonAll = "{\"prevent\":" + jsonPrevent + ",\"message\":" + jsonString + "}";

        log.debug("3. jsonAll: " + jsonAll);
        return jsonAll;
    }

    // 조회 (검색 및 페이징)
    @RequestMapping( value ="/doRetrieve.do"
			, method = RequestMethod.GET)	
	public String  doRetrieve(Model model, HttpServletRequest req) throws SQLException{
		String viewName = "board/board_list";
		log.debug("┌──────────────────────────────────────────┐");
		log.debug("│ doRetrieve()                             │");
		log.debug("└──────────────────────────────────────────┘");		
		

        Search search = new Search();
		
		//검색구분
		String  searchDiv  = StringUtil.nvl(req.getParameter("searchDiv"),"");
		String  searchWord = StringUtil.nvl(req.getParameter("searchWord"),"");
		
		search.setSearchDiv(searchDiv);
		search.setSearchWord(searchWord);
		
		//브라우저에서 숫자 : 문자로 들어 온다.	
		String pageSize = StringUtil.nvl(req.getParameter("pageSize"),"10");
		String pageNo = StringUtil.nvl(req.getParameter("pageNo"),"1");
		
		search.setPageSize(Integer.parseInt(pageSize));
		search.setPageNo(Integer.parseInt(pageNo));
		
		// 1.
		log.debug("1.param search:" + search);		
		
		List<prevent> list = this.preventService.doRetrieve(search);
		
		//2. 화면 전송 데이터
		model.addAttribute("list", list);//조회 데이터
		model.addAttribute("search", search); //검색조건
		
		int totalCnt = 0;
		//페이징:totalcnt
		if(null != list && list.size() > 0) {
			prevent firstVO = list.get(0);
			totalCnt = firstVO.getTotalCnt();
		}
		model.addAttribute("totalCnt", totalCnt); //검색조건
		
		//----------------------------------------------------------------------
		Code code =new Code();
		code.setMasterCode("BOARD_SEARCH");//회원검색 조건
		List<Code> memberSearch = this.codeService.doRetrieve(code);
		model.addAttribute("BOARD_SEARCH", memberSearch); //검색조건
		
        code.setMasterCode("COM_PAGE_SIZE");//회원검색 조건
		List<Code> pageSizeSearch = this.codeService.doRetrieve(code);
		model.addAttribute("COM_PAGE_SIZE", pageSizeSearch); //페이지 사이즈

		//----------------------------------------------------------------------
		
		//model
		return viewName;
	}
}
        
        
     
        
     