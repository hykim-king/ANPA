package com.acorn.anpa.user.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.acorn.anpa.cmn.DTO;
import com.acorn.anpa.cmn.Message;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.cmn.StringUtil;
import com.acorn.anpa.member.domain.Member;
import com.acorn.anpa.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController implements PLog{

	@Autowired
    private UserService userService;
	
	public UserController() {
		log.debug("┌────────────────────────────────────────┐");
		log.debug("│ UserController()                       │");
		log.debug("└────────────────────────────────────────┘");
	}

    // 로그인 페이지 이동
    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String loginPage() {
        return "user/login"; 
    }

    // 로그인 처리
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
	public String loginInfo(Member inVO, HttpSession httpSession) throws SQLException {
		String jsonString = "";

		log.debug("1 param:" + inVO);
		int checkCount = userService.idPasswordCheck(inVO);

		String loginMessage = "";
		if (10 == checkCount) {// 아이디를 확인 하세요.
			loginMessage = "아이디를 확인 하세요.";
		} else if (20 == checkCount) {// 비번을 확인 하세요.
			loginMessage = "비번을 확인 하세요.";
		} else if (30 == checkCount) {
			loginMessage = "아이디/비번일치!";

			// 회원정보
			Member member = userService.loginInfo(inVO);
			if (null != member) {
				httpSession.setAttribute("user", member);
			}
		}

		Message message = new Message(checkCount, loginMessage);

		jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(message);
		log.debug("3.jsonString:" + jsonString);

		return jsonString;
	}
    
    
    // 로그아웃 처리
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/user/login.do"; // 로그아웃 후 로그인 페이지로 이동
    }

    

}