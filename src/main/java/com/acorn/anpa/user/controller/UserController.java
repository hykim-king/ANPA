package com.acorn.anpa.user.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		log.debug("1.jsonString:" + jsonString);

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
		log.debug("1.jsonString:" + jsonString);		
		
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
			message = member.getUserId() + " 님 회원가입에 실패하였습니다";
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
		log.debug("2.jsonString:" + jsonString);
		
		
		return jsonString;
	}	
    
    // ID 중복 체크 
    @RequestMapping(value = "/emailDuplicateCheck.do", method = RequestMethod.GET)
    @ResponseBody
    public String emailDuplicateCheck(Member inVO) throws SQLException {
    	String jsonString = "";
		log.debug("1. param: " + inVO);
		
		int flag = userService.emailDuplicateCheck(inVO);
		String message = "";
		if(1==flag) {
			message =  inVO.getEmail()+" 사용 불가 이메일입니다";
		}else {
			message =  inVO.getEmail()+" 이메일 중복체크가 완료되었습니다!";
		}
		jsonString = new Gson().toJson(new Message(flag, message));
		log.debug("2.jsonString:" + jsonString);
		
		
		return jsonString;
	}	
    
    
    // ID 찾기 페이지 이동
    @RequestMapping(value = "/findUserId.do", method = RequestMethod.GET)
    public String findIDPage() {
        return "user/findUserId"; 
    }

    // ID 찾기 처리
    @RequestMapping(value ="/findUserId.do", method = RequestMethod.POST)
    @ResponseBody
    public String findUserId(Member inVO) throws SQLException {

    	 log.debug("1. param: " + inVO);

    	    // 사용자 아이디 찾기
    	    String foundUserId = userService.findUserId(inVO);
    	    String message;
    	    int flag;

    	    if (foundUserId != null && !foundUserId.isEmpty()) {
    	        message = "회원님의 아이디는 " + foundUserId + "입니다.";
    	        flag = 1;  // 성공
    	    } else {
    	        message = "해당 정보와 일치하는 아이디가 없습니다.";
    	        flag = 0;  // 실패
    	    }

    	    Message messageObj = new Message(flag, message);
    	    String jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(messageObj);

    	    log.debug("2. jsonString: " + jsonString);

    	    return jsonString;
    }
    
    // 비밀번호 찾기 페이지 이동
    @RequestMapping(value = "/findUserPw.do", method = RequestMethod.GET)
    public String findPwPage() {
        return "user/findUserPw"; // findPw.jsp로 이동
    }
    
    // 비밀번호 찾기 처리
    @PostMapping("/findPassword.do")
    @ResponseBody
    public String findPassword(Member inVO) throws SQLException {
    	log.debug("1. param: " + inVO);

	    // 사용자 비밀번호 초기화
	 	int flag = userService.findPassword(inVO);
	    String message;

	    if (flag == 1) {
	        message = "비밀번호 초기화 완료! 이메일을 확인하시고, 새로운 비밀번호를 사용하여 로그인해 주세요.";
	    } else {
	        message = "해당 정보와 일치하는 아이디가 없습니다.";
	    }

	    Message messageObj = new Message(flag, message);
	    String jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(messageObj);

	    log.debug("3. jsonString: " + jsonString);

	    return jsonString;
    }

    // 비밀번호 찾기 처리
    @RequestMapping(value = "/findUserPw.do", method = RequestMethod.POST)
    @ResponseBody
    public String findUserPassword(Member inVO) throws SQLException {
    	
        return null;
    }
    

}