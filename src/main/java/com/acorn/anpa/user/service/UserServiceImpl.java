package com.acorn.anpa.user.service;

import java.security.SecureRandom;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.acorn.anpa.cmn.PLog; 
import com.acorn.anpa.member.domain.Member;
import com.acorn.anpa.mapper.UserMapper; 

@Service("userServiceImpl")
public class UserServiceImpl implements UserService, PLog {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private JavaMailSender mailSender;

    public UserServiceImpl() {

    }

    @Override
    public Member login(Member inVO) throws SQLException {
        log.debug("1. param :" + inVO);
        Member member = userMapper.login(inVO);
        log.debug("2. login info :" + member);
        return member;
    }

    @Override
	public int idPasswordCheck(Member inVO) throws SQLException {
		log.debug("1. param :"+inVO);
		int status = 0;
		
		// idCheck : 1 성공/0 실패 -> 10
		
		int loginCnt = userMapper.idCheck(inVO);
		if(0 == loginCnt) {
			status = 10;
			return status;
		}
		
		// passwordCheck : 1 성공/0 실패 -> 20
		int passwordCnt = userMapper.passwordCheck(inVO);
		if(0 == passwordCnt) {
			status = 20;
			return status;
		}
		
		status = 30;
		log.debug("2. status :"+status);
		return status;
	}

    @Override
    public Member loginInfo(Member inVO) throws SQLException {
        log.debug("1. param :" + inVO);
        Member loginInfo = userMapper.loginInfo(inVO);
        log.debug("2. loginInfo :" + loginInfo);
        return loginInfo;
    }	
    
    @Override
    public int signUp(Member member) throws SQLException {
        int flag = 0;
        log.debug(member);
        flag = userMapper.signUp(member);
        log.debug("flag:" + flag);
        return flag;
    }
	
	@Override
	public int idDuplicateCheck(Member inVO) throws SQLException {
		log.debug("1. param :"+inVO);
		
		int count = userMapper.idDuplicateCheck(inVO);
		
		log.debug("2. count :"+count);
		return count;
	}
	
	@Override
	public int emailDuplicateCheck(Member inVO) throws SQLException {
		log.debug("1. param :"+inVO);
		
		int count = userMapper.emailDuplicateCheck(inVO);
		
		log.debug("2. count :"+count);
		return count;
	}

	 @Override
	    public String findUserId(Member member) {
	        try {
	            return userMapper.findUserId(member);
	        } catch (SQLException e) {
	            throw new RuntimeException("아이디 찾기 중 오류 발생", e);
	        }
	    }

	 @Override
		public int findPassword(Member member) throws SQLException {
			int flag = 0;
			String userId = member.getUserId();
			String userEmail = member.getEmail();
			String name = member.getUserName();
			log.debug("userId : "+userId);
			log.debug("userEmail : "+userEmail);
			log.debug("name : "+name);
			
			flag = userMapper.findPassword(member);
			StringBuilder sb = new StringBuilder(100); //메일 내용
			String password = ""; //초기화 비밀번호
			String title = ""; //메일 제목
			if(flag == 1) {
				title = "ANPA 비밀번호 초기화 안내";
				password = generateRandomPassword();
				log.debug("password : "+password);
				
				sb.append("안녕하세요, ["+name+"]님,\n");
				sb.append("\n");
				
				sb.append("비밀번호 초기화 요청을 확인하였습니다. 아래의 새 비밀번호를 사용하여 로그인해 주세요.\n");
				sb.append("새 비밀번호:\n");
				sb.append(password+"\n");
				
				sb.append("\n");
				sb.append("주의 사항:\n");
				sb.append("- 새 비밀번호를 로그인 후 즉시 변경하는 것을 권장합니다.\n");
				sb.append("- 비밀번호는 안전하게 보관해 주세요.\n");
				sb.append("\n");
				sb.append("감사합니다,\n");
				sb.append("ANPA 팀\n");
			
			
				String contents = sb.toString();
				log.debug("contents : "+contents);
				
				member.setPassword(password);
				int flagPw = userMapper.passwordUpdate(member);
				
				if(flagPw == 1) {
					sendEmail(title, contents, userEmail);
				}
			
			}
			
			return flag;
		}
		
		//비밀번호 찾기 메일 전송
		private void sendEmail(String title, String contents, String userEmail) {
	        try {
	            SimpleMailMessage message = new SimpleMailMessage();

	            // 보내는 사람
	            message.setFrom("anpa1995@naver.com");

	            // 받는 사람
	            message.setTo(userEmail);

	            // 제목
	            message.setSubject(title);

	            // 메일 내용
	            message.setText(contents);

	            // 메일 전송
	            mailSender.send(message);
	        } catch (Exception e) {
	            log.debug("┌──────────────────────────────────────────┐");
	            log.debug("│ sendEmail(error) " + e.getMessage());
	            log.debug("└──────────────────────────────────────────┘");    
	            e.printStackTrace();
	        }
	        
	        log.debug("┌──────────────────────────────────────────┐");
	        log.debug("│ mail 전송 완료 │");
	        log.debug("└──────────────────────────────────────────┘");            
	    }
		
		//비밀번호 초기화
		 private static String generateRandomPassword() {
			 final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+[]{}|;:,.<>?";
		     final int MIN_LENGTH = 8;
		     final int MAX_LENGTH = 10;
		     final SecureRandom RANDOM = new SecureRandom();
		     
		     int length = RANDOM.nextInt(MAX_LENGTH - MIN_LENGTH + 1) + MIN_LENGTH;
		     StringBuilder password = new StringBuilder(length);
		        
		     for (int i = 0; i < length; i++) {
		         int randomIndex = RANDOM.nextInt(CHARACTERS.length());
		         password.append(CHARACTERS.charAt(randomIndex));
		     }
		        
		     return password.toString();
		 }
	
	 @Override
		public int passwordUpdate(Member inVO) throws SQLException {
			return 0;
		}
	 
	@Override
	public void deleteUser(String userId) {
		
	}
	
	@Override
    public void deleteAll() throws SQLException {
        log.debug("Deleting all members.");
        userMapper.deleteAll();
    }
	
	@Override
	public int doUpdate(Member inVO) throws SQLException {
		log.debug("1. param : " + inVO);	
		int flag = this.userMapper.doUpdate(inVO);
		log.debug("2. flag : " + flag);
		
		return flag;
	}

	@Override
	public Member doSelectOne(Member inVO) throws SQLException {
		log.debug("1. param : " + inVO);	
		Member outVO = this.userMapper.doSelectOne(inVO);
		log.debug("2. outVO : " + outVO);
		
		return outVO;
	}

	@Override
	public int doDelete(Member inVO) throws SQLException {
		log.debug("1. param : " + inVO);	
		int flag = this.userMapper.doUpdate(inVO);
		log.debug("2. flag : " + flag);
		
		return flag;
	}

	@Override
	public int doSave(Member inVO) throws SQLException {
		log.debug("1. param : " + inVO);	
		int flag = this.userMapper.doUpdate(inVO);
		log.debug("2. flag : " + flag);
		
		return flag;
	}

}
