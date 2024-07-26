package com.pcwk.ehr.firedata.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.firedata.domain.Firedata;

@Service
public class FireDataServiceImpl implements PLog, FireDataService{

	private MailSender mailSender;
	
	public FireDataServiceImpl() {}
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}	
	
	void sendEmail() {
		try {
			SimpleMailMessage message = new SimpleMailMessage();

			// 보내는 사람
			message.setFrom("anpa1995@naver.com");

			// 받는 사람
			message.setTo("anpa1995@naver.com");

			// 제목
			message.setSubject("메일 확인 제목");

			// 메일확인
			message.setText("메일 확인 내용");

			mailSender.send(message);
		} catch (Exception e) {
			log.debug("┌──────────────────────────────────────────┐");
			log.debug("│ sendEmail()"+e.getMessage());
			log.debug("└──────────────────────────────────────────┘");	
			e.printStackTrace();
		}
		
		log.debug("┌──────────────────────────────────────────┐");
		log.debug("│ mail 전송│");
		log.debug("└──────────────────────────────────────────┘");			
	}	
}
