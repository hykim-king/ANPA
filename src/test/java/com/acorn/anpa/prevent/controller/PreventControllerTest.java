package com.acorn.anpa.prevent.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.mapper.PreventMapper;
import com.acorn.anpa.prevent.domain.Prevent;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class PreventControllerTest implements PLog{
	     
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	PreventMapper boardMapper;
	
    Prevent prevent01;
    Prevent prevent02;
    Prevent prevent03;
    
	com.acorn.anpa.cmn.Search search;
	
	
	//브라우저 대신 Mock
		MockMvc mockMvc;
	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUp");
		log.debug("└─────────────────────────────────────────────────────────");
		
	}
	

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown");
		log.debug("└─────────────────────────────────────────────────────────");
	
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

