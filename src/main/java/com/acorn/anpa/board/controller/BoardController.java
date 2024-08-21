package com.acorn.anpa.board.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorn.anpa.answer.domain.Answer;
import com.acorn.anpa.answer.service.AnswerService;
import com.acorn.anpa.board.domain.Board;
import com.acorn.anpa.board.service.BoardService;
import com.acorn.anpa.cmn.Message;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.cmn.StringUtil;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.code.service.CodeService;
import com.acorn.anpa.member.domain.Member;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("board")
public class BoardController implements PLog{
	@Autowired
	BoardService boardService;

	@Autowired
	AnswerService answerService;
	
	@Autowired
	CodeService codeService;
	
	public BoardController() {
		log.debug("┌─────────────────────────");
		log.debug("│ BoardController()");
		log.debug("└─────────────────────────");	
	}
	
	@RequestMapping(value = "/doSave.do"
			, method = RequestMethod.POST
			, produces = "text/plain;charset=UTF-8") // produces:																					// encoding
	@ResponseBody
	public String doSave(Board inVO) throws SQLException{
		String jsonString = "";
		
		int flag = boardService.doSave(inVO);
		log.debug("2.flag:" + flag);
		
		String message = "";

		if (1 == flag) {
			message = inVO.getTitle() + " 게시글이 등록되었습니다.";
		} else {
			message = inVO.getTitle() + " 등록 실패!";
		}
		
		Message messageObj = new Message(flag, message);
		
		jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(messageObj);
		log.debug("3.jsonString:" + jsonString);
		
		return jsonString;
	}
	
	@RequestMapping(value = "/doUpdate.do"
			, method = RequestMethod.POST
			, produces = "text/plain;charset=UTF-8") // produces:																					// encoding
	@ResponseBody
	public String doUpdate(Board inVO) throws SQLException{
		String jsonString = "";
		
		int flag = boardService.doUpdate(inVO);
		log.debug("2.flag:" + flag);
		
		String message = "";

		if (1 == flag) {
			message = inVO.getTitle() + " 게시글이 수정 되었습니다.";
		} else {
			message = inVO.getTitle() + " 수정 실패!";
		}
		
		Message messageObj = new Message(flag, message);
		
		jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(messageObj);
		log.debug("3.jsonString:" + jsonString);
		
		return jsonString;
	}
	
	@RequestMapping(value = "/doDelete.do"
			, method = RequestMethod.GET
			, produces = "text/plain;charset=UTF-8") // produces:																					// encoding
	@ResponseBody
	public String doDelete(Board inVO) throws SQLException{
		String jsonString = "";
		
		int flag = boardService.doDelete(inVO);
		log.debug("2.flag:" + flag);
		
		String message = "";

		if (1 == flag) {
			message = inVO.getTitle() + " 게시글이 삭제 되었습니다.";
		} else {
			message = inVO.getTitle() + " 삭제 실패!";
		}
		
		Message messageObj = new Message(flag, message);
		
		jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(messageObj);
		log.debug("3.jsonString:" + jsonString);
		
		return jsonString;
	}
	
	@GetMapping(value = "/doSelectOne.do")
	public String doSelectOne(Model model, HttpServletRequest req) throws SQLException {
	    String viewName = "board/board_info";
	    Board inVO = new Board();
	    
	    // 요청 파라미터에서 boardSeq 값을 가져와서 int로 변환
	    int boardSeq = Integer.parseInt(StringUtil.nvl(req.getParameter("seq"), "0"));
	    inVO.setBoardSeq(boardSeq);

	    // HttpSession 객체를 얻어오기
	    HttpSession session = req.getSession(false); // false: 세션이 존재하지 않으면 새로 생성하지 않음
	    
	    // 로그인 사용자 정보 가져오기
	    String regId = "guest";
	    if (session != null) {
	    	Member loginUser = (Member) session.getAttribute("user");
	        if (loginUser != null) {
	            regId = loginUser.getUserId(); // 로그인된 사용자 ID를 regId에 저장
	        }
	    }
	    inVO.setRegId(regId);


		Search search = new Search();

		search.setSearchWord(boardSeq + "");
		List<Answer> list = answerService.doRetrieve(search);
		model.addAttribute("list", list);
		
	    // 게시글 조회
	    Board outVO = boardService.doSelectOne(inVO);
	    log.debug("outVO : " + outVO);

	    String message = "";
	    int flag = 0;

	    if (outVO != null) {
	        message = outVO.getTitle() + " 게시글이 조회되었습니다.";
	        flag = 1;
	    } else {
	        message = "조회 실패!";
	    }

	    // Message 객체 생성
	    Message messageObj = new Message(flag, message);

	    // 모델에 데이터 추가
	    model.addAttribute("board", outVO);
	    model.addAttribute("message", messageObj);

	    return viewName;
	}
	
	
	@RequestMapping(value = "/doRetrieveAjax.do"
			, method = RequestMethod.GET
			, produces = "text/plain;charset=UTF-8") // produces:																					// encoding
	@ResponseBody
	public String doRetrieveAjax(Search search) throws SQLException {
		log.debug("search : " + search);
		String jsonString = "";
		
		List<Board> list = boardService.doRetrieve(search);
		log.debug("list : " + list);
			
		jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(list);
		log.debug("3.jsonString:" + jsonString);
		
		return jsonString;
	}
	
	@GetMapping("/boardReg.do")
	public String boardReg(Model model, HttpServletRequest req) throws SQLException {
		String viewName = "board/board_reg";
		
		String divYn = StringUtil.nvl(req.getParameter("divYn"),"");
		
		model.addAttribute("divYn",divYn);
		
		return viewName;
	}
	
	@GetMapping("/{div}")
	public String doRetrieve(@PathVariable("div")String div, Model model, HttpServletRequest req) throws SQLException {
		String viewName = "board/board";
		
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
		String NmDiv = div.replace(".do", "");
		NmDiv = StringUtil.nvl(NmDiv, "");
		log.debug("Received div: " + NmDiv); 
		search.setDiv(NmDiv);
		
		String blTitle;
		if ("20".equals(NmDiv)) {
			blTitle = "공지사항";
		}else {
			blTitle = "소통마당"; // 기본 메시지
		} 
		model.addAttribute("blTitle", blTitle);
		
		List<Board> list = boardService.doRetrieve(search);
		log.debug("list : " + list);
		
		model.addAttribute("list", list);
		model.addAttribute("search", search);		
		
		//============================================
		Code code = new Code();
		code.setMasterCode("BOARD_SEARCH");
		List<Code> boardSearch = codeService.doRetrieve(code);
		model.addAttribute("boardSearch", boardSearch);	
		
		code.setMasterCode("COM_PAGE_SIZE");
		List<Code> pageSearch = codeService.doRetrieve(code);
		model.addAttribute("pageSearch", pageSearch);		
		//============================================
		return viewName;
	}
	
}
