package com.acorn.anpa.monthfiredata.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.sql.SQLException;

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
import com.acorn.anpa.mapper.MonthFireDataMapper;
import com.acorn.anpa.monthfiredata.service.MonthFireDataService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
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
	
	Firedata fire01;
	
	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUp");
		log.debug("└─────────────────────────────────────────────────────────");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	
		fire01 = new Firedata(1, 0, 0, 0, 0, 1000, 100, 11010);
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown");
		log.debug("└─────────────────────────────────────────────────────────");
		
	}
	
	@Ignore
	@Test
	public void todayMonthData() throws Exception{
		log.debug("┌────────────────────────────────────────────────────────");
		log.debug("│ todayMonthData() : ");
		log.debug("└────────────────────────────────────────────────────────");
		
		// 1. url, param 설정
		MockHttpServletRequestBuilder requestBuilder =
				MockMvcRequestBuilders.get("/monthfiredata/todayMonthData.do")
				.param("regDt", "202308");
				
		// 2. 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
		// return encoding
		.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
		// Web 상태
		.andExpect(status().is2xxSuccessful());		
		
		String jsonResult = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		log.debug("┌────────────────────────────────────────");
		log.debug("│ jsonResult : " + jsonResult);
		log.debug("└────────────────────────────────────────");
		
		
		// JSON 문자열을 JsonObject로 파싱
        JsonObject jsonObject = JsonParser.parseString(jsonResult).getAsJsonObject();

        // "firedata" "message" 노드를 추출
        String firedataJson = jsonObject.get("tmData").toString();
		
        String messageJson = jsonObject.get("message").toString();
        
        // "message" 노드를 Message로 변환
        Type messageType = new TypeToken<Message>() {}.getType();
        Message resultMessage = new Gson().fromJson(messageJson, messageType);
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("202308"+"날짜의 화재데이터가 조회되었습니다.", resultMessage.getMessageContents());
			
	}
	
	@Ignore
	@Test
	public void locBigData() throws Exception {
		log.debug("┌────────────────────────────────────────────────────────");
		log.debug("│ locBigData() : ");
		log.debug("└────────────────────────────────────────────────────────");
		
		// 1. url, param 설정
		MockHttpServletRequestBuilder requestBuilder =
				MockMvcRequestBuilders.get("/monthfiredata/locBigData.do")
				.param("regDt", "202308");
				
		// 2. 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
		// return encoding
		.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
		// Web 상태
		.andExpect(status().is2xxSuccessful());		
		
		String jsonResult = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		log.debug("┌────────────────────────────────────────");
		log.debug("│ jsonResult : " + jsonResult);
		log.debug("└────────────────────────────────────────");
		
		
		// JSON 문자열을 JsonObject로 파싱
        JsonObject jsonObject = JsonParser.parseString(jsonResult).getAsJsonObject();

        // "firedata" "message" 노드를 추출
        String firedataJson = jsonObject.get("lbData").toString();
		
        String messageJson = jsonObject.get("message").toString();
        
        // "message" 노드를 Message로 변환
        Type messageType = new TypeToken<Message>() {}.getType();
        Message resultMessage = new Gson().fromJson(messageJson, messageType);
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("202308"+"날짜의 화재데이터가 조회되었습니다.", resultMessage.getMessageContents());
			
	}

	@Ignore
	@Test
	public void locMidData() throws Exception {
		log.debug("┌────────────────────────────────────────────────────────");
		log.debug("│ locMidData() : ");
		log.debug("└────────────────────────────────────────────────────────");
		
		// 1. url, param 설정
		MockHttpServletRequestBuilder requestBuilder =
				MockMvcRequestBuilders.get("/monthfiredata/locMidData.do")
				.param("regDt", "202308");
				
		// 2. 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
		// return encoding
		.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
		// Web 상태
		.andExpect(status().is2xxSuccessful());		
		
		String jsonResult = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		log.debug("┌────────────────────────────────────────");
		log.debug("│ jsonResult : " + jsonResult);
		log.debug("└────────────────────────────────────────");
		
		
		// JSON 문자열을 JsonObject로 파싱
        JsonObject jsonObject = JsonParser.parseString(jsonResult).getAsJsonObject();

        // "firedata" "message" 노드를 추출
        String firedataJson = jsonObject.get("lmData").toString();
		
        String messageJson = jsonObject.get("message").toString();
        
        // "message" 노드를 Message로 변환
        Type messageType = new TypeToken<Message>() {}.getType();
        Message resultMessage = new Gson().fromJson(messageJson, messageType);
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("202308"+"날짜의 화재데이터가 조회되었습니다.", resultMessage.getMessageContents());
			
	}
	
	@Test
	public void factorMidData() throws Exception {
		log.debug("┌────────────────────────────────────────────────────────");
		log.debug("│ factorMidData() : ");
		log.debug("└────────────────────────────────────────────────────────");
		
		// 1. url, param 설정
		MockHttpServletRequestBuilder requestBuilder =
				MockMvcRequestBuilders.get("/monthfiredata/factorMidData.do")
				.param("regDt", "202308");
				
		// 2. 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
		// return encoding
		.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
		// Web 상태
		.andExpect(status().is2xxSuccessful());		
		
		String jsonResult = resultActions.andDo(print())
				.andReturn()
				.getResponse().getContentAsString();
		log.debug("┌────────────────────────────────────────");
		log.debug("│ jsonResult : " + jsonResult);
		log.debug("└────────────────────────────────────────");
		
		
		// JSON 문자열을 JsonObject로 파싱
        JsonObject jsonObject = JsonParser.parseString(jsonResult).getAsJsonObject();

        // "firedata" "message" 노드를 추출
        String firedataJson = jsonObject.get("fmData").toString();
		
        String messageJson = jsonObject.get("message").toString();
        
        // "message" 노드를 Message로 변환
        Type messageType = new TypeToken<Message>() {}.getType();
        Message resultMessage = new Gson().fromJson(messageJson, messageType);
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("202308"+"날짜의 화재데이터가 조회되었습니다.", resultMessage.getMessageContents());
			
	}
	
	
	@Ignore
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
