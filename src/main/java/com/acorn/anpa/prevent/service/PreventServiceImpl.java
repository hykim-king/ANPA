package com.acorn.anpa.prevent.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.anpa.prevent.domain.prevent;
import com.acorn.anpa.prevent.service.PreventService;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.mapper.PreventMapper;
import com.acorn.anpa.member.domain.Member;

@Service
public class PreventServiceImpl implements PreventService,PLog{ 

    @Autowired
    private PreventMapper preventMapper;
    
    public PreventServiceImpl() {
		log.debug("┌─────────────────────────");
		log.debug("│PreventServiceImpl()     ");
		log.debug("└─────────────────────────");
	}

    @Override
    public prevent doSelectOne(int preventSeq) throws SQLException {
        return preventMapper.doSelectOne(preventSeq);
    }

    @Override
    public List<prevent> doRetrieve(Search search) throws SQLException {
        return preventMapper.doRetrieve(search);
        
        
    }
    public int doRetrieve(Firedata inVO)throws SQLException {
		log.debug("1. param : " + inVO);
		int flag = 0;
		flag = preventMapper.doRetrieve(inVO);
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
}

