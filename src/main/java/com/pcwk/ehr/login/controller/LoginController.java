package com.pcwk.ehr.login.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pcwk.ehr.cmn.Message;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.login.domain.Login;
import com.pcwk.ehr.login.service.LoginService;
import com.pcwk.ehr.member.domain.Member;

@Controller
@RequestMapping("login")
public class LoginController implements PLog {

	@Autowired
	LoginService loginService;

	public LoginController() {
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ LoginController()");
		log.debug("└──────────────────────────────────────────────");	
	}
	
	//화면
	@GetMapping("/login.do") // 주소 이름
	public String loginView() {
		String viewName = "login/login"; // jsp 실제 경로
		log.debug("viewName = "+viewName);	
		
		return viewName;
	}

	//logout.do
	@RequestMapping(
			value = "/logout.do", // 주소 이름
			method = RequestMethod.GET,
			produces = "text/plain;charset=UTF-8"
	)
	@ResponseBody
	public String logout(HttpSession httpSession){
		String jsonString = "";
		log.debug("logout()");
		
		String logOutMessage = "";
		int flag = 0;
		// 세션에 user값이 존재하는지 확인	  session에 저장된 회원 정보
		if(null != httpSession.getAttribute("user")) {
			logOutMessage = "로그아웃 되었습니다.";
			// 세션 초기화 실행
			httpSession.invalidate(); // 로그아웃 실행		
			flag = 1;
		}		
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		jsonString = gson.toJson(new Message(flag, logOutMessage));
		
		return jsonString;
	}
		
	//로그인 처리
	@RequestMapping(
			value = "/loginInfo.do",
			method = RequestMethod.POST,
			produces = "text/plain;charset=UTF-8"
	)
	@ResponseBody
	public String loginInfo(Login inVO, HttpSession httpSession) throws SQLException {
		String jsonString = "";		
		log.debug("1. pram = "+inVO);	
		
		int checkCount = loginService.idPasswordCheck(inVO);
		
		String message = "";
		if(10 == checkCount) {
			message = "아이디를 확인 하세요.";
		}else if(20 == checkCount) {
			message = "비번을 확인 하세요.";
		}else if(30 == checkCount) {
			message = "로그인 성공!";
			Member outVO = loginService.loginInfo(inVO);
			
			if(outVO != null) { // 세션에 저장할 회원 정보 이름
				httpSession.setAttribute("user", outVO);
			}
			
		}else {
			message = "규격외 에러 발생 : 관리자에게 문의 요망";
		}
		
		Message messageVO = new Message(checkCount, message);
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		jsonString = gson.toJson(messageVO);
		log.debug("2. jsonString = "+jsonString);	
		
		
		return jsonString;
	}
}
