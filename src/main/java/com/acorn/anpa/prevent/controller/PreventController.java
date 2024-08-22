package com.acorn.anpa.prevent.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.acorn.anpa.prevent.domain.Prevent;
import com.acorn.anpa.prevent.service.PreventService;
import com.acorn.anpa.cmn.Message;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.cmn.StringUtil;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.code.service.CodeService;
import com.acorn.anpa.mapper.PreventMapper;
import com.google.gson.GsonBuilder;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("prevent")
public class PreventController  {
    
    private static final Logger log = LoggerFactory.getLogger(PreventController.class);

    @Autowired
    PreventService preventService;
    
    @Autowired
	PreventMapper preventMapper; 
	
    @Autowired
	CodeService  codeService;
    
    
    //@Autowired
	//MarkdownService markdownService;
    
    
    // 생성자
    public PreventController() {
        log.debug("┌──────────────────────────────────────────┐");
        log.debug("│ PreventController()                      │");
        log.debug("└──────────────────────────────────────────┘");
    }

    // 데이터 저장
    @RequestMapping(value = "/doSave.do", 
    		method = RequestMethod.POST, 
    		produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String doSave(Prevent inVO) throws SQLException {
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
  //http://localhost:8080/ehr/prevent/doSelectOne.do?preventSeq=413
  //http://localhost:8080/ehr/prevent/doSelectOne.do
    @RequestMapping(value = "/doSelectOne.do")
	public String doSelectOne(Prevent inVO, Model model, HttpSession httpSession) throws SQLException, EmptyResultDataAccessException{
		String view = "prevent/prevent_page";///WEB-INF/views/+board/board_mng+.jsp ->/WEB-INF/views/board/board_mng.jsp
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ doSelectOne                       │");
		log.debug("│PreventVO                           │"+inVO);
		log.debug("└───────────────────────────────────┘");			
		if(0 == inVO.getPreventSeq() ) {
			log.debug("============================");
			log.debug("==nullPointerException===");
			log.debug("============================");
			
			throw new NullPointerException("순번을 입력 하세요");
		}
		
		if( null==inVO.getRegId()) {
			inVO.setRegId(StringUtil.nvl(inVO.getRegId(),"Guest"));
		} 
		
		////session이 있는 경우
		//if(null != httpSession.getAttribute("user")) {
		//	UserVO user = (UserVO) httpSession.getAttribute("user");
		//	inVO.setRegId(user.getUserId());
		//}
		//
		Prevent outVO = preventService.doSelectOne(inVO);
		model.addAttribute("vo", outVO);
		
		return view;
		}
 // (value = "/doSelectOne.do", 
 // 		method = RequestMethod.GET, 
 // 		produces = "text/plain;charset=UTF-8")
 // @ResponseBody
 // public String doSelectOne(prevent inVO) throws SQLException {
 // 	   String viewName = "prevent/prevent_page";
 //     log.debug("1. param inVO: " + inVO);
 //
 //     prevent outVO = preventService.doSelectOne(inVO);
 //     log.debug("2. outVO: " + outVO);
 //
 //     String message = "";
 //     int flag = 0;
 //     if (null != outVO) {
 //         message = outVO.getTitle() + " 이 조회되었습니다.";
 //         flag = 1;
 //     } else {
 //         message = inVO.getTitle() + " 조회 실패!";
 //     }
 //
 //     Message messageObj = new Message(flag, message);
 //     String jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(messageObj);
 //
 //     String jsonPrevent = new GsonBuilder().setPrettyPrinting().create().toJson(outVO);
 //     String jsonAll = "{\"prevent\":" + jsonPrevent + ",\"message\":" + jsonString + "}";
 //
 //     log.debug("3. jsonAll: " + jsonAll);
 //     return jsonAll;
 // }
    
    
    
    // 수정
    @RequestMapping(value = "/doUpdate.do"
			   , method = RequestMethod.POST            //textarea post로
			   , produces = "text/plain;charset=UTF-8") //json encoding 
	@ResponseBody //json으로 리턴하기 위한
	public String doUpdate(Prevent inVO) throws SQLException {
		
		String jsonString = "";
		log.debug("1.param:" + inVO);
		
		//TODO : SESSION처리 
		int flag = preventService.doUpdate(inVO);
		log.debug("2.flag:" + flag);
		String message = "";
		if(1 == flag) {
			message = inVO.getTitle() + "가 수정 되었습니다.";
		}else {
			message = inVO.getTitle() + "가 수정 실패 했습니다.";
		}
		
		Message messageObj = new Message(flag, message);
		jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(messageObj);
		log.debug("3.jsonString:" + jsonString);
		
		return jsonString;
		
	}
    
  //삭제
    @RequestMapping(value = "/doDelete.do"
	    	, method = RequestMethod.GET
	        , produces = "text/plain;charset=UTF-8"
	        ) //produces : 화면으로 전송 encoding)
	@ResponseBody
	public String doDelete(Prevent inVO) throws SQLException {
		String jsonString = "";
		log.debug("1.param:" + inVO);
		
		int flag = preventService.doDelete(inVO);
		
		log.debug("1.flag:" + flag);
		String message = "";
		
		if(1 == flag) {
			message = inVO.getNo() + "가 삭제 되었습니다.";
		}else {
			message = inVO.getNo() + "삭제 실패 되었습니다.";
		}
		
		Message messageObj = new Message(flag, message);
		jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(messageObj);
		log.debug("3.jsonString:" + jsonString);
		
		return jsonString;
		
	} 
  
    
//http://localhost:8080/ehr/prevent/doRetrieve.do
    // 조회 (검색 및 페이징)
    @RequestMapping( value ="/doRetrieve.do"
			, method = RequestMethod.GET)	
	public String  doRetrieve(Model model, HttpServletRequest req) throws SQLException{
		String viewName = "prevent/prevent_list";
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
		
		List<Prevent> list = this.preventService.doRetrieve(search);
		
		//2. 화면 전송 데이터
		model.addAttribute("list", list);//조회 데이터
		model.addAttribute("search", search); //검색조건
		
		int totalCnt = 0;
		//페이징:totalcnt
		if(null != list && list.size() > 0) {
			Prevent firstVO = list.get(0);
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
        
        
     
        
     