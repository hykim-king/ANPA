package com.acorn.anpa.user.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    // 로그인 페이지 이동
    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String loginPage() {
        return "user/login"; // login.jsp로 이동
    }

    // 로그인 처리
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        
        try {
            Member member = userService.login(userId, password);
            if (member != null) {
                request.getSession().setAttribute("user", member);
                return "redirect:/home.do"; // 로그인 성공 시 홈 페이지로 리다이렉트
            } else {
                model.addAttribute("errorMsg", "Invalid user ID or password.");
                return "user/login"; // 다시 로그인 페이지로
            }
        } catch (SQLException e) {
            model.addAttribute("errorMsg", "Error during login. Please try again.");
            return "user/login"; // 에러 시 다시 로그인 페이지로
        }
    }

    // 회원가입 페이지 이동
    @RequestMapping(value = "/signup.do", method = RequestMethod.GET)
    public String signupPage() {
        return "user/signup"; // signup.jsp로 이동
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
                return "user/signup"; // ID 중복 시 회원가입 페이지로
            }
            int result = userService.signUp(newMember);
            if (result == 1) {
                return "redirect:/user/login.do"; // 회원가입 성공 시 로그인 페이지로 리다이렉트
            } else {
                model.addAttribute("errorMsg", "Sign up failed. Please try again.");
                return "user/signup"; // 실패 시 다시 회원가입 페이지로
            }
        } catch (SQLException e) {
            model.addAttribute("errorMsg", "Error during sign up. Please try again.");
            return "user/signup"; // 에러 시 다시 회원가입 페이지로
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
            return "user/signup"; // 에러 시 다시 회원가입 페이지로
        }
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
    
    // 비밀번호 재설정 페이지 이동
    @RequestMapping(value = "/resetPw.do", method = RequestMethod.GET)
    public String resetPWPage() {
        return "user/resetPw"; // resetPW.jsp로 이동
    }

    // 비밀번호 재설정 처리
    @RequestMapping(value = "/resetPw.do", method = RequestMethod.POST)
    public String resetPW(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userId");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("errorMsg", "Passwords do not match.");
            return "user/resetPW"; // 비밀번호 불일치 시 재설정 페이지로
        }

        try {
            boolean success = userService.resetPassword(userId, newPassword);
            if (success) {
                return "redirect:/user/login.do"; // 비밀번호 재설정 성공 시 로그인 페이지로 리다이렉트
            } else {
                model.addAttribute("errorMsg", "Password reset failed. Please try again.");
                return "user/resetPW"; // 실패 시 다시 비밀번호 재설정 페이지로
            }
        } catch (SQLException e) {
            model.addAttribute("errorMsg", "Error during password reset. Please try again.");
            return "user/resetPW"; // 에러 시 다시 비밀번호 재설정 페이지로
        }
    }

    // 로그아웃 처리
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/user/login.do"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }
}