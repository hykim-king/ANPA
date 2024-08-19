package com.acorn.anpa.firedata.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.firedata.domain.Firedata;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class FireDataControllerTest implements PLog{
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대신 Mock
	MockMvc mockMvc;
	
	Search search;

	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────┐");
		log.debug("│ setUp() ");
		log.debug("└─────────────────────────┘");
		
		search = new Search();
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────┐");
		log.debug("│ tearDown() ");
		log.debug("└─────────────────────────┘");
	}

	@Test
	public void totalDataList() throws Exception {
		log.debug("┌─────────────────────────┐");
		log.debug("│ totalDataList() ");
		log.debug("└─────────────────────────┘");
		
		MockHttpServletRequestBuilder requestBuilder
		= MockMvcRequestBuilders.get("/firedata/totalDataList.do")
		.param("searchDateStart", "2023-03-01")
		.param("searchDateEnd", "2023-08-01")
		.param("searchDiv", "10")
		.param("div", "factor")
		.param("BigNm", "부주의")
//		.param("MidNm", "불장난")
//		.param("subCityBigNm", "서울특별시")
//		.param("subCityMidNm", "중구")
		;
		
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				//Controller produces =  "text/plain;charset=UTF-8"
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
				//Web상태
				.andExpect(status().is2xxSuccessful());
		
		//Mock 로그: print()
		//json문자열 
		String jsonResult=resultActions.andDo(print())
							.andReturn()
							.getResponse().getContentAsString();
							;
							
		log.debug("┌──────────────────────────────────────────┐");
		log.debug("│ jsonResult:"+jsonResult);
		log.debug("└──────────────────────────────────────────┘");
		
		//json 문자열을 Message로 변환
		Gson gson = new Gson();
        Type firedataListType = new TypeToken<List<Firedata>>() {}.getType();
        List<Firedata> outVO = gson.fromJson(jsonResult, firedataListType);
		//비교
		assertNotNull(outVO);
		
		log.debug("┌──────────────────────────────────────────┐");
		for(Firedata vo : outVO) {
			log.debug("vo: "+vo);
		}
		log.debug("└──────────────────────────────────────────┘");
	}
	
	@Ignore
	@Test
	public void beans() {
		log.debug("┌─────────────────────────┐");
		log.debug("│ beans() ");
		log.debug("└─────────────────────────┘");	
		
		log.debug("webApplicationContext:"+webApplicationContext);
		log.debug("mockMvc:"+mockMvc);
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
	}

}
