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
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.code.service.CodeService;
import com.acorn.anpa.member.domain.Member;
import com.acorn.anpa.user.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController implements PLog{

	@Autowired
    private UserService userService;
	
	@Autowired
	CodeService codeService;
	
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
		if (10 == checkCount) {
			loginMessage = "잘못된 아이디 입니다!";
		} else if (20 == checkCount) {
			loginMessage = "잘못된 비밀번호 입니다!";
		} else if (30 == checkCount) {
			loginMessage = "아이디 및 비번일치!";

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
        return "redirect:/user/login.do"; 
    }
    
    // 회원가입 페이지 이동
    @RequestMapping(value = "/signup.do", method = RequestMethod.GET)
    public String signupPage(Model model) throws SQLException {
    	
    	Code code = new Code();
    	// 모델에 튜닝 시군구 코드
		code.setMasterCode("city");
		List<Code> cityCode = codeService.doRetrieve(code);
		model.addAttribute("cityCode", cityCode);
		
		return "user/signup"; 
    }

    // 회원가입 처리
    @RequestMapping(value = "/signup.do", method = RequestMethod.POST)
    public String signup(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        int emailYn = Integer.parseInt(request.getParameter("emailYn"));
        int adminYn = Integer.parseInt(request.getParameter("adminYn"));
        int subCity = Integer.parseInt(request.getParameter("subCity"));
        String tel = request.getParameter("tel");
        
        Member newMember = new Member(userId, password, userName, email, 
                                       emailYn, adminYn, subCity, tel);
        
        try {
            if (userService.idCheck(userId)) {
                model.addAttribute("errorMsg", "User ID already exists.");
                return "user/signup.do"; // ID 중복 시 회원가입 페이지로
            }
            int result = userService.signUp(newMember);
            if (result == 1) {
                return "redirect:/user/login.do"; // 회원가입 성공 시 로그인 페이지로 리다이렉트
            } else {
                model.addAttribute("errorMsg", "Sign up failed. Please try again.");
                return "user/signup.do"; // 실패 시 다시 회원가입 페이지로
            }
        } catch (SQLException e) {
            model.addAttribute("errorMsg", "Error during sign up. Please try again.");
            return "user/signup.do"; // 에러 시 다시 회원가입 페이지로
        }
    }

    // ID 중복 체크 처리
    @RequestMapping(value = "/checkUserId.do", method = RequestMethod.POST)
    public String checkUserId(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        
        try {
            boolean exists = userService.idCheck(userId);
            model.addAttribute("exists", exists);
            return "user/checkUserId"; // ID 중복 체크 결과 페이지로 이동
        } catch (SQLException e) {
            model.addAttribute("errorMsg", "Error during ID check. Please try again.");
            return "user/signup.do"; // 에러 시 다시 회원가입 페이지로
        }
    }

    

}