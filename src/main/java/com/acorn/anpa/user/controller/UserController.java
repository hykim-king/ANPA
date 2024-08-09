package com.acorn.anpa.user.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class UserController {

    @Autowired
    private UserService userService;

    // 로그인 페이지 이동
    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String loginPage() {
        return "user/user_login"; // user_login.jsp로 이동
    }

    // 로그인 처리
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        
        try {
            Member member = userService.login(userId, password);
            if (member != null) {
                // 로그인 성공 시 세션에 사용자 정보 저장
                request.getSession().setAttribute("user", member);
                return "redirect:/home.do"; // 로그인 성공 시 홈 페이지로 리다이렉트
            } else {
                // 로그인 실패 시 에러 메시지 전달
                model.addAttribute("errorMsg", "Invalid user ID or password.");
                return "user/user_login"; // 다시 로그인 페이지로
            }
        } catch (SQLException e) {
            model.addAttribute("errorMsg", "Error during login. Please try again.");
            return "user/user_login"; // 에러 시 다시 로그인 페이지로
        }
    }

    // 회원가입 페이지 이동
    @RequestMapping(value = "/signup.do", method = RequestMethod.GET)
    public String signupPage() {
        return "user/user_signup"; // user_signup.jsp로 이동
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
            int result = userService.signUp(newMember);
            if (result == 1) {
                // 회원가입 성공 시 로그인 페이지로 리다이렉트
                return "redirect:/user/login.do";
            } else {
                model.addAttribute("errorMsg", "Sign up failed. Please try again.");
                return "user/user_signup"; // 실패 시 다시 회원가입 페이지로
            }
        } catch (SQLException e) {
            model.addAttribute("errorMsg", "Error during sign up. Please try again.");
            return "user/user_signup"; // 에러 시 다시 회원가입 페이지로
        }
    }

    // ID 찾기 페이지 이동
    @RequestMapping(value = "/findID.do", method = RequestMethod.GET)
    public String findIDPage() {
        return "user/user_findID"; // user_findID.jsp로 이동
    }

    // ID 찾기 처리
    @RequestMapping(value = "/findID.do", method = RequestMethod.POST)
    public String findID(HttpServletRequest request, Model model) {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        
        try {
            String userId = userService.findUserId(userName, email);
            if (userId != null) {
                model.addAttribute("userId", userId);
                return "user/user_findID_result"; // ID 찾기 결과 페이지로 이동
            } else {
                model.addAttribute("errorMsg", "No matching user found.");
                return "user/user_findID"; // 실패 시 다시 ID 찾기 페이지로
            }
        } catch (SQLException e) {
            model.addAttribute("errorMsg", "Error during ID search. Please try again.");
            return "user/user_findID"; // 에러 시 다시 ID 찾기 페이지로
        }
    }

    // 비밀번호 찾기 페이지 이동
    @RequestMapping(value = "/findPW.do", method = RequestMethod.GET)
    public String findPWPage() {
        return "user/user_findPW"; // user_findPW.jsp로 이동
    }

    // 비밀번호 찾기 처리
    @RequestMapping(value = "/findPW.do", method = RequestMethod.POST)
    public String findPW(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        
        try {
            String password = userService.findPassword(userId, userName, email);
            if (password != null) {
                model.addAttribute("password", password);
                return "user/user_findPW_result"; // 비밀번호 찾기 결과 페이지로 이동
            } else {
                model.addAttribute("errorMsg", "No matching user found.");
                return "user/user_findPW"; // 실패 시 다시 비밀번호 찾기 페이지로
            }
        } catch (SQLException e) {
            model.addAttribute("errorMsg", "Error during password search. Please try again.");
            return "user/user_findPW"; // 에러 시 다시 비밀번호 찾기 페이지로
        }
    }
    
    // 로그아웃 처리
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        // 세션에서 사용자 정보 제거
        request.getSession().invalidate();
        return "redirect:/user/login.do"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }
}