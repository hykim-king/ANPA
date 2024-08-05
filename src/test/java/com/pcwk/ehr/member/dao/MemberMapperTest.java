package com.pcwk.ehr.member.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.mapper.MemberMapper;
import com.pcwk.ehr.member.domain.Member;

@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 알파벳 순서대로 메서드 실행
public class MemberMapperTest implements PLog {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	MemberMapper memberMapper;
	
	String subCity01;
	
	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUp()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		subCity01 = "11010";
	}
	
	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown()");
		log.debug("└─────────────────────────────────────────────────────────");
	}
	
	@Test
	public void doRetrieveLocEmail() throws SQLException {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doRetrieveLocEmail()");
		log.debug("└─────────────────────────────────────────────────────────");
		List<Member> list = memberMapper.doRetrieveLocEmail(subCity01);
		
	    for (Member member : list) {
	        log.debug("┌─────────────────────────────────────────────────────────");
	        log.debug("│ Member : " + member.getUserName() + " Email : " + member.getEmail());
	        log.debug("└─────────────────────────────────────────────────────────");
	    }
	}
	
	@Ignore
	@Test
	public void beans() {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ beans()");
		log.debug("│ context : " + context);
		log.debug("│ memberMapper : " + memberMapper);
		log.debug("└─────────────────────────────────────────────────────────");
		assertNotNull(context);
		assertNotNull(memberMapper);
	}

}
