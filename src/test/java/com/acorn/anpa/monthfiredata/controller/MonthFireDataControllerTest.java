package com.acorn.anpa.monthfiredata.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.mapper.MonthFireDataMapper;
import com.acorn.anpa.monthfiredata.service.MonthFireDataService;
@WebAppConfiguration
@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class MonthFireDataControllerTest implements PLog{
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	MonthFireDataMapper monthFireDataMapper;
	
	@Autowired
	MonthFireDataService monthFireDataService;
	
	MockMvc mockMvc;
	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUp");
		log.debug("└─────────────────────────────────────────────────────────");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown");
		log.debug("└─────────────────────────────────────────────────────────");
		
	}

	@Test
	public void beans() {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ beans()");
		log.debug("│ webApplicationContext : " + webApplicationContext);
		log.debug("│ mockMvc : " + mockMvc);
		log.debug("│ MonthFireDataMapper : " + monthFireDataMapper);
		log.debug("└─────────────────────────────────────────────────────────");
		
		assertNotNull(webApplicationContext);
		assertNotNull(monthFireDataService);
		assertNotNull(mockMvc);
	}

}
