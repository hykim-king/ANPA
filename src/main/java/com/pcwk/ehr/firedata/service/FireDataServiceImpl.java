package com.pcwk.ehr.firedata.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.code.domain.Code;
import com.pcwk.ehr.firedata.domain.Firedata;
import com.pcwk.ehr.mapper.CodeMapper;
import com.pcwk.ehr.mapper.FireDataMapper;

@Service
public class FireDataServiceImpl implements PLog, FireDataService {

	@Autowired
	FireDataMapper fireDataMapper;

	@Autowired
	CodeMapper codeMapper;
	
    @Autowired
    private MailSender mailSender;

    Code code = new Code();
	List<Code> codeList;
    
    public FireDataServiceImpl() {}
    
    @Override
    public Firedata doSaveData(Firedata inVO)throws SQLException {
		log.debug("1. param : " + inVO);
		int flag = 0;
		flag = fireDataMapper.doSave(inVO);
		log.debug("2. flag : " + flag);
		
		String title = "화재가 발생하였습니다";
		String contents = "";
		String userEmail = "anpa1995@naver.com";
		
		String masterCode = "city";
		int subCode = 0;
		
		String bigList = "";
		String midList = "";
		
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
			
			contents = bigList + midList + " 해당 지역에 화재가 발생하였습니다";
			sendEmail(title, contents, userEmail);
			
            log.debug("┌──────────────────────────────────────────┐");
            log.debug("│ title : " + title);
            log.debug("│ contents : " + contents);
            log.debug("│ userEmail : " + userEmail);
            log.debug("└──────────────────────────────────────────┘");    			
		}
		
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Firedata> doRetrieve(DTO search) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doUpdate(Firedata inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(Firedata inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}    
}