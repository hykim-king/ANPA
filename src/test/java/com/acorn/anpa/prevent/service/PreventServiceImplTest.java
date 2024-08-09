package com.acorn.anpa.prevent.service;

import static org.junit.Assert.*;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.acorn.anpa.cmn.PLog;

@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class PreventServiceImplTest implements PLog{
	
	@Autowired
	ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUp()"                                               );
		log.debug("└─────────────────────────────────────────────────────────");
	
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown()");
		log.debug("└─────────────────────────────────────────────────────────");
	
	}

	@Test
	public void beans() {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ beans()");
		log.debug("│ context : " + context);
		log.debug("│                                                         ");
		log.debug("└─────────────────────────────────────────────────────────");
		assertNotNull(context);
		//assertNotNull(PreventService);
		//assertNotNull(PreventMapper);
	}

}
