package com.acorn.anpa.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Message;
import com.acorn.anpa.member.domain.Member;
import com.acorn.anpa.user.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { 
    "file:src/main/webapp/WEB-INF/spring/root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" 
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest implements PLog{

    @Autowired
    WebApplicationContext webApplicationContext;
	
    MockMvc mockMvc;

    @Autowired
    UserService userService;
    
    @Autowired
    UserService userMapper;

    MockHttpSession session;
    
    Member testUser;

    @Before
    public void setUp() throws Exception {
    	log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		// 전체 삭제
		userMapper.deleteAll();

		testUser = new Member("dao", "54321", "김다오", "dao@test.com", 1, 0, 11010, "010-1234-6789");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        session = new MockHttpSession();    
    }
  
    @Test
	public void loginInfoPasswordFail() throws Exception {
		//사용자 등록
		Member flag = userMapper.login(testUser);
		assertEquals(1, flag);
		//등록 정보로 로그인
		
		//호출방식, URL, param 저장
		MockHttpServletRequestBuilder  requestBuilder = 
				  MockMvcRequestBuilders.post("/login/loginInfo.do")
				  .param("userId", testUser.getUserId())
				  .param("password", "비번오류")
				  .session(session)
				  ;
		
		//호출
		ResultActions resultActions =mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
				.andExpect(status().isOk());
		   
		String jsonResult = resultActions.andDo(print())
							.andReturn()
							.getResponse().getContentAsString();
		log.debug("┌──────────────────────────────────────────┐");
		log.debug("│jsonResult │"+jsonResult);
		log.debug("└──────────────────────────────────────────┘");		
		
		Message resultMessage = new Gson().fromJson(jsonResult, Message.class);
		assertEquals(20, resultMessage.getModId());
		assertEquals("비번을 확인 하세요.", resultMessage.getMessageContents());
		
		
		//session
		Member member=(Member) session.getAttribute("user");
		assertNull(member);
		
	}	
    
    @After
    public void tearDown() throws Exception {
    	log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
        userService.deleteUser(testUser.getUserId());
    }
    
    @Ignore
	@Test
	public void beans() {
		log.debug("┌──────────────────────────────────────────┐");
		log.debug("│ beans()                                  │");
		log.debug("└──────────────────────────────────────────┘");

		log.debug("webApplicationContext:" + webApplicationContext);
		log.debug("mockMvc:" + mockMvc);
		log.debug("userMapper:" + userMapper);

		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(userMapper);
	}
}
