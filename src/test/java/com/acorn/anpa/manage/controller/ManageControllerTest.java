package com.acorn.anpa.manage.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.acorn.anpa.cmn.Message;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.firedata.service.FireDataService;
import com.acorn.anpa.mapper.FireDataMapper;
import com.google.gson.Gson;

@WebAppConfiguration
@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class ManageControllerTest implements PLog{

	@Autowired
	WebApplicationContext webApplicationContext;
	
    @Autowired
    FireDataMapper fireDataMapper;
	
	@Autowired
	FireDataService firedataService;
	
	//브라우저 대신
	MockMvc mockMvc;
	
	Firedata firedata01;
	Firedata firedata02;
	Firedata firedata03;
	
	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUp");
		log.debug("└─────────────────────────────────────────────────────────");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		firedata01 = new Firedata(1, 0, 0, 0, 10, 1000, 100, 11010, "sjm", "SYSDATE", "sjm", "SYSDATE");
        fireDataMapper.doDeleteTest(firedata01);     
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown");
		log.debug("└─────────────────────────────────────────────────────────");
	}

	@Test
	public void doSaveData() throws Exception{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doSaveData()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		//요청 매핑
		MockHttpServletRequestBuilder requestBuilder =
			MockMvcRequestBuilders.post("/manage/doSaveData.do")
			.param("injuredSum", firedata01.getInjuredSum() + "")
			.param("dead", firedata01.getDead() + "")
			.param("injured", firedata01.getInjured() + "")
			.param("amount", firedata01.getAmount() + "")
			.param("subFactor", firedata01.getSubFactor() + "")
			.param("subLoc", firedata01.getSubLoc() + "")
			.param("subCity", firedata01.getSubCity() + "")
			.param("regId", firedata01.getRegId());	
		
		//호출 및 결과
		ResultActions resultActions = 
				mockMvc.perform(requestBuilder)
				// Controller produces = "text/plain;charset=UTF-8"
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
				// Web상태
				.andExpect(status().is2xxSuccessful());
		
		String jsonResult = resultActions.andDo(print())
		.andReturn().getResponse().getContentAsString();
		
		log.debug("┌────────────────────────────────────────────────────────");
		log.debug("│ jsonResult() : " + jsonResult);
		log.debug("└────────────────────────────────────────────────────────");
		// json 문자열을 Message로 변환
		Message resultMessage = new Gson().fromJson(jsonResult, Message.class);
		
		// 3. 비교
		assertEquals("등록이 완료되었습니다 2건의 메일이 전송되었습니다", resultMessage.getMessageContents());
	}
	
	@Ignore
	@Test
	public void beans() {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ beans()");
		log.debug("│ webApplicationContext : " + webApplicationContext);
		log.debug("│ firedataService 	   : " + firedataService);
		log.debug("│ mockMvc 			   : " + mockMvc);
		log.debug("└─────────────────────────────────────────────────────────");
		
		assertNotNull(webApplicationContext);
		assertNotNull(firedataService);
		assertNotNull(mockMvc);
	}

}
