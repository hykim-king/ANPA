package com.pcwk.ehr.login.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.login.domain.Login;
import com.pcwk.ehr.mapper.LoginMapper;
import com.pcwk.ehr.member.domain.Member;

@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class LoginMapperTest implements PLog{

	@Autowired
	ApplicationContext context;
	
	@Autowired
	LoginMapper loginMapper;
	
	Login login01;
	
	@Before
	public void setUp() throws Exception {
		log.debug("┌────────────────────────────");
		log.debug("│ setUp ");
		log.debug("└────────────────────────────");	
		
		login01 = new Login("admin", "1234");
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌────────────────────────────");
		log.debug("│ tearDown ");
		log.debug("└────────────────────────────");	
	}

	@Test
	public void doLogin() throws Exception{
		int flag = loginMapper.idCheck(login01);
		assertEquals(flag, 1);
		
		flag = loginMapper.passwordCheck(login01);
		assertEquals(flag, 1);
		
		Member member = loginMapper.loginInfo(login01);
		assertNotNull(member);
		log.debug("member: "+ member);
	}

	@Ignore
	@Test
	public void beans() {
		log.debug("┌────────────────────────────");
		log.debug("│ beans() ");
		log.debug("└────────────────────────────");	
		log.debug("context: "+context);	
		log.debug("loginMapper: "+loginMapper);	
		assertNotNull(context);
		assertNotNull(loginMapper);
	}

}
