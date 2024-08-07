package com.acorn.anpa.prevent.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.mapper.MemberMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class PreventMapperTest implements PLog {
	
	  @Autowired
	    private ApplicationContext context;
	  
	  @Autowired
	  PreventMapperTest preventMapper;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 log.debug("┌─────────────────────────────────────────────────────────");
	     log.debug("│ setUpBeforeClass()                                      ");
	     log.debug("└─────────────────────────────────────────────────────────");    
	    }
	

	@Before
	public void setUp() throws Exception {
		log.debug("┌──────────────────────────────┐");
		log.debug("│ @Before                      │");
		log.debug("└──────────────────────────────┘");	
		
	}
	

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ @After                                                  " );
		log.debug("└─────────────────────────────────────────────────────────");
	}

	@Test
	public void beans() {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ beans()                                                 x ");
		log.debug("└─────────────────────────────────────────────────────────");
		assertNotNull(context);
		assertNotNull(preventMapper);
	}
}
