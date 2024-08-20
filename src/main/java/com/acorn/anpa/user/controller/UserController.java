package com.acorn.anpa.user.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    // 로그인
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
    
    
    // 로그아웃
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpSession httpSession) {
    	String jsonString = "";
		log.debug("logout()");
		
		String loginOutMessage = "로그아웃";
		int flag = 0;
		if( null != httpSession.getAttribute("user")) {
			httpSession.invalidate();
					
			loginOutMessage = "로그아웃 되었습니다.";
			flag = 1;
		}
		
		Message message=new Message(flag, loginOutMessage);
		jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(message);
		log.debug("3.jsonString:" + jsonString);		
		
		return jsonString;
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

    // 회원가입 
    @RequestMapping(value = "/signup.do", method = RequestMethod.POST)
    @ResponseBody
    public String signUp(@RequestBody Member member) throws SQLException {
    	String jsonString = "";

		// 1.
		log.debug("1.param user:" + member);

		int flag = userService.signUp(member);

		// 2.
		log.debug("2.flag:" + flag);

		String message = "";

		if (1 == flag) {
			message = member.getUserId() + " 님 안전파수꾼 가입을 환영합니다!";
		} else {
			message = member.getUserId() + " 님 회원가입을 실패하였습니다";
		}

		Message messageObj = new Message(flag, message);

		Gson gson = new Gson();
		jsonString = gson.toJson(messageObj);
		// 3.
		log.debug("3.jsonString:" + jsonString);

		return jsonString;
    }

    // ID 중복 체크 
    @RequestMapping(value = "/idDuplicateCheck.do", method = RequestMethod.GET)
    @ResponseBody
    public String idDuplicateCheck(Member inVO) throws SQLException {
    	String jsonString = "";
		log.debug("1. param: " + inVO);
		
		int flag = userService.idDuplicateCheck(inVO);
		String message = "";
		if(1==flag) {
			message =  inVO.getUserId()+" 사용 불가 아이디입니다";
		}else {
			message =  inVO.getUserId()+" 아이디 중복체크가 완료되었습니다!";
		}
		jsonString = new Gson().toJson(new Message(flag, message));
		log.debug("3.jsonString:" + jsonString);
		
		
		return jsonString;
	}	
    
    // ID 찾기 페이지 이동
    @RequestMapping(value = "/findId.do", method = RequestMethod.GET)
    public String findIDPage() {
        return "user/findId"; // findId.jsp로 이동
    }

    // ID 찾기 처리
    @RequestMapping(value = "/findId.do", method = RequestMethod.POST)
    public String findID(HttpServletRequest request, Model model) {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        
        try {
            String userId = userService.findUserId(userName, email);
            if (userId != null) {
                model.addAttribute("userId", userId);
                return "user/findID_result"; // ID 찾기 결과 페이지로 이동
            } else {
                model.addAttribute("errorMsg", "No matching user found.");
                return "user/findID"; // 실패 시 다시 ID 찾기 페이지로
            }
        } catch (SQLException e) {
            model.addAttribute("errorMsg", "Error during ID search. Please try again.");
            return "user/findID"; // 에러 시 다시 ID 찾기 페이지로
        }
    }

    // 비밀번호 찾기 페이지 이동
    @RequestMapping(value = "/findPw.do", method = RequestMethod.GET)
    public String findPwPage() {
        return "user/findPw"; // findPw.jsp로 이동
    }

    // 비밀번호 찾기 처리
    @RequestMapping(value = "/findPw.do", method = RequestMethod.POST)
    public String findPw(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        
        try {
            String password = userService.findPassword(userId, userName, email);
            if (password != null) {
                model.addAttribute("password", password);
                return "user/findPw_result"; // 비밀번호 찾기 결과 페이지로 이동
            } else {
                model.addAttribute("errorMsg", "No matching user found.");
                return "user/findPw"; // 실패 시 다시 비밀번호 찾기 페이지로
            }
        } catch (SQLException e) {
            model.addAttribute("errorMsg", "Error during password search. Please try again.");
            return "user/findPw"; // 에러 시 다시 비밀번호 찾기 페이지로
        }
    }

}