package com.pcwk.ehr.login.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
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

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.Message;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.login.domain.Login;
import com.pcwk.ehr.mapper.MemberMapper;
import com.pcwk.ehr.member.domain.Member;

@WebAppConfiguration
@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class LoginControllerTest implements PLog{

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Autowired
	LoginController loginController;
	
	@Autowired
	MemberMapper memberMapper;
	
	MockHttpSession session;	
	
	Login login01;
	Member memberVO01;
	
	// 브라우저 대신 Mock
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────");
		log.debug("│ setUp()");
		log.debug("└─────────────────────────────────────────");		
		
		login01 = new Login("kim", "1234");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		session = new MockHttpSession();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────");
		log.debug("│ tearDown()");
		log.debug("└─────────────────────────────────────────");	
	}

	@Test
	public void loginInfo() throws Exception{
		log.debug("┌─────────────────────────────────────────");
		log.debug("│ loginInfo()");
		log.debug("└─────────────────────────────────────────");	
		
		// 호출방식, URL, param 저장
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login/loginInfo.do")
				.param("userId",login01.getUserId())
				.param("password",login01.getPassword())
				.session(session)
				;
		
		// 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
				.andExpect(status().is2xxSuccessful());
		
		String jsonResult = resultActions.andDo(print())
		.andReturn()
		.getResponse().getContentAsString();
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ jsonResult : " + jsonResult);
		log.debug("└─────────────────────────────────────────────────────────");
		
		Message resultMessage = new Gson().fromJson(jsonResult, Message.class);
		assertEquals(30, resultMessage.getMessageId());
		assertEquals("로그인 성공!", resultMessage.getMessageContents());
		
		// session
		Member member = (Member) session.getAttribute("user");
		assertNotNull(member);
		
		assertEquals("승희", member.getUserName());
	}
	
	@Ignore
	@Test
	public void beans() {
		log.debug("┌───────────────────────────────────────────────────────");
		log.debug("│ beans()");
		log.debug("│ webApplicationContext" + webApplicationContext);
		log.debug("│ mockMvc" + mockMvc);
		log.debug("│ loginController" + loginController);		
		log.debug("└───────────────────────────────────────────────────────");
		
		assertNotNull(loginController);	
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);		
	}

}
