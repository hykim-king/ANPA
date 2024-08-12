package com.acorn.anpa.firedata.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.acorn.anpa.cmn.DTO;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.mapper.CodeMapper;
import com.acorn.anpa.mapper.FireDataMapper;
import com.acorn.anpa.mapper.MemberMapper;
import com.acorn.anpa.member.domain.Member;

@Service
public class FireDataServiceImpl implements PLog, FireDataService {

	@Autowired
	FireDataMapper fireDataMapper;

	@Autowired
	CodeMapper codeMapper;
	
    @Autowired
    private MailSender mailSender;
    
    @Autowired
    MemberMapper memberMapper;

    Code code = new Code();
	List<Code> codeList;
	
    public FireDataServiceImpl() {}
    
    @Override
    public int doSaveData(Firedata inVO)throws SQLException {
		log.debug("1. param : " + inVO);
		int flag = 0;
		flag = fireDataMapper.doSave(inVO);
		log.debug("2. flag : " + flag);
		
		String title = "화재가 발생하였습니다";
		String contents = "";
		/* String userEmail = "anpa1995@naver.com"; */
		String userEmail = "";
		String userName = "";
		 
		String masterCode = "city";
		int subCode = 0;
		
		String bigList = "";
		String midList = "";
		
		int emailCount = 0;
		
		if(flag == 1) {
			
			code.setMasterCode(masterCode);
			code.setSubCode(inVO.getSubCity());
			List<Code> outVO = codeMapper.doSelectCode(code);	
			
			for(Code vo : outVO) {
				log.debug("┌──────────────────────────────────────────┐");
				log.debug("│" + vo);
				log.debug("└──────────────────────────────────────────┘");
				bigList = vo.getBigList();
				midList = vo.getMidList();
			}
			
			List<Member> list = memberMapper.doRetrieveLocEmail(inVO.getSubCity() + "");
			
			for (Member member : list) {
		        log.debug("┌─────────────────────────────────────────────────────────");
		        log.debug("│ Member : " + member.getUserName() + " Email : " + member.getEmail());
		        log.debug("└─────────────────────────────────────────────────────────");
		        userEmail = member.getEmail();
		        userName = member.getUserName();
		        contents = userName + "님이 거주하시는 " + bigList + midList + " 해당 지역에 화재가 발생하였습니다";
		        sendEmail(title, contents, userEmail);
		        emailCount++;
		    }			
			
            log.debug("┌──────────────────────────────────────────┐");
            log.debug("│ title : " + title);
            log.debug("│ contents : " + contents);
            log.debug("│ userEmail : " + userEmail);
            log.debug("└──────────────────────────────────────────┘");    			
		}
		
		return emailCount;
	}
    
    public void sendEmail(String title, String contents, String userEmail) {
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
            log.debug("│ sendEmail() " + e.getMessage());
            log.debug("└──────────────────────────────────────────┘");    
            e.printStackTrace();
        }
        
        log.debug("┌──────────────────────────────────────────┐");
        log.debug("│ mail 전송 완료 │");
        log.debug("└──────────────────────────────────────────┘");            
    }

	@Override
	public int doSave(Firedata inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Firedata doSelectOne(Firedata inVO) throws SQLException {

		return fireDataMapper.doSelectOne(inVO);
	}

	@Override
	public List<Firedata> doRetrieve(DTO search) throws SQLException {
		log.debug("1. param : " + search);
		return fireDataMapper.doRetrieve(search);
	}

	@Override
	public int doUpdate(Firedata inVO) throws SQLException {
		log.debug("1. param : " + inVO);
		return this.fireDataMapper.doUpdate(inVO);
	}

	@Override
	public int doDelete(Firedata inVO) throws SQLException {
		log.debug("1. param : " + inVO);
		return this.fireDataMapper.doDelete(inVO);
	}

	@Override
	public Firedata totalData(Search search) throws SQLException {
		log.debug("1. param : " + search);
		return fireDataMapper.totalData(search);
	}

	@Override
	public Firedata doMainData() throws SQLException {
		// TODO Auto-generated method stub
		return fireDataMapper.doMainData();
	}    
}