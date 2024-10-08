package com.acorn.anpa.manage.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.acorn.anpa.cmn.Message;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.code.service.CodeService;
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.firedata.service.FireDataService;
import com.acorn.anpa.mapper.FireDataMapper;
import com.acorn.anpa.member.domain.Member;
import com.acorn.anpa.member.service.MemberService;
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
public class ManageControllerTest implements PLog{

	@Autowired
	WebApplicationContext webApplicationContext;
	
    @Autowired
    FireDataMapper fireDataMapper;
	
	@Autowired
	FireDataService firedataService;

	@Autowired
	MemberService memberService;

	@Autowired
	CodeService codeService;
	
	//브라우저 대신
	MockMvc mockMvc;
	
	Firedata firedata01;
	Firedata firedata02;
	Firedata firedata03;

	Member memberVO01;
	
	Code code01;
	
	Search search;
	
	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUp");
		log.debug("└─────────────────────────────────────────────────────────");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		firedata01 = new Firedata(1, 0, 0, 0, 10, 1000, 100, 11010);   
		firedata01.setRegId("admin1");
		
		memberVO01 = new Member("james01", "4321", "이상무01", "bagsa1515@naver.com", 0, 0, 11010, "01012345678");
		
		code01 = new Code("city", 0, "", "", 1);		
		
		search = new Search();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown");
		log.debug("└─────────────────────────────────────────────────────────");
	}

	@Ignore
	@Test
	public void doSelectCode() throws Exception{
		log.debug("┌──────────────────────────────────────────────");
		log.debug("│ doSelectCode()");
		log.debug("└──────────────────────────────────────────────");	
		
		code01.setMasterCode("city");
		code01.setMainCode(31000);
		
		List<Code> codeList = codeService.doSelectCode(code01);	
		for(Code vo : codeList) {
			log.debug(vo);
		}
	}
	
	@Ignore
	@Test
	public void doDeleteMember() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doDeleteMember Test()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		int flag = memberService.doSave(memberVO01);
		log.debug("flag : " + flag);
		assertEquals(1, flag);
		
		//요청 매핑
		MockHttpServletRequestBuilder requestBuilder =
			MockMvcRequestBuilders.get("/manage/doDeleteMember.do")
			.param("userId", memberVO01.getUserId());
		
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
		assertEquals(1, resultMessage.getMessageId());
		assertEquals(memberVO01.getUserId() + "회원정보가 삭제되었습니다.", resultMessage.getMessageContents());
		
	}	
	
	@Ignore
	@Test
	public void doRetrieveMember() throws Exception{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doRetrieveMember Test()");
		log.debug("└─────────────────────────────────────────────────────────");
	
		search.setPageNo(1);
		search.setPageSize(10);
		
		//요청 매핑
		MockHttpServletRequestBuilder requestBuilder =
			MockMvcRequestBuilders.get("/manage/doRetrieveMember.do")
			.param("pageSize", search.getPageSize()+"")
			.param("pageNo", search.getPageNo()+"")
			;
		
		//호출 및 결과
		ResultActions resultActions = 
				mockMvc.perform(requestBuilder)
				// Web상태
				.andExpect(status().is2xxSuccessful());
		
		//Model
		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		
		Map<String, Object> modelMap = mvcResult.getModelAndView().getModel();
		
		List<Member> list = (List<Member>) modelMap.get("list");
		assertNotNull(list);
		for (Member vo : list) log.debug(vo);		
		
		String viewName = mvcResult.getModelAndView().getViewName();
		
		int totalCnt = (int) modelMap.get("totalCnt");
		log.debug("totalCnt: "+totalCnt);	
	}
	
	@Test
	public void doRetrieveData() throws Exception{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doRetrieveData Test()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		search.setPageNo(1);
		search.setPageSize(10);
		search.setSearchDiv("4100");
//		search.setSearchWord("8010");
//		search.setBigNm("200");
//		search.setMidNm("503");
		search.setSubCityBigNm("11000");
//		search.setSubCityMidNm("31023");
//		search.setSearchDateStart("20230101");
//		search.setSearchDateEnd("20231231");
		
		//요청 매핑
		MockHttpServletRequestBuilder requestBuilder =
			MockMvcRequestBuilders.get("/manage/doRetrieveData.do")
			.param("pageNo", search.getPageNo()+"")
			.param("pageSize", search.getPageSize()+"")
			.param("fBselect", search.getSearchDiv())
			.param("fMselect", search.getSearchWord())
			.param("lBselect", search.getBigNm())
			.param("lMselect", search.getMidNm())
			.param("cBselect", search.getSubCityBigNm())
			.param("cMselect", search.getSubCityMidNm())
//			.param("searchDateStart", search.getSearchDateStart())
//			.param("searchDateEnd", search.getSearchDateEnd())
			;
		
		//호출 및 결과
		ResultActions resultActions = 
				mockMvc.perform(requestBuilder)
				// Web상태
				.andExpect(status().is2xxSuccessful());
		
		//Model
		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		
		Map<String, Object> modelMap = mvcResult.getModelAndView().getModel();
		
		List<Firedata> list = (List<Firedata>) modelMap.get("list");
		assertNotNull(list);
		for (Firedata vo : list) {
			log.debug("┌─────────────────────────────────────────────────────────");
			log.debug(vo);		
			log.debug("└─────────────────────────────────────────────────────────");
		}
		
		String viewName = mvcResult.getModelAndView().getViewName();
		
		int totalCnt = (int) modelMap.get("totalCnt");
		log.debug("totalCnt: "+totalCnt);	
	}
	
	@Ignore
	@Test
	public void doSelectOne() throws Exception{
		log.debug("┌────────────────────────────────────────────────────────");
		log.debug("│ testDoSelectOne() : ");
		log.debug("└────────────────────────────────────────────────────────");
		
		int flag = fireDataMapper.doSave(firedata01);
		assertEquals(1, flag);
		
		int seq = fireDataMapper.getSequence();
		firedata01.setFireSeq(seq);
		
		// 1. url, param 설정
		MockHttpServletRequestBuilder requestBuilder =
				MockMvcRequestBuilders.get("/manage/doSelectOne.do")
				.param("fireSeq", firedata01.getFireSeq() + "")
		;
		
		// 2. 호출
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				// return encoding
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
				// Web 상태
				.andExpect(status().is2xxSuccessful());
		
		String jsonResult = resultActions.andDo(print())
		.andReturn()
		.getResponse().getContentAsString()
		;
		log.debug("┌────────────────────────────────────────");
		log.debug("│ jsonResult : " + jsonResult);
		log.debug("└────────────────────────────────────────");

		// JSON 문자열을 JsonObject로 파싱
        JsonObject jsonObject = JsonParser.parseString(jsonResult).getAsJsonObject();

        // "firedata" "message" 노드를 추출
        String firedataJson = jsonObject.get("firedata").toString();
		
        String messageJson = jsonObject.get("message").toString();
        
        // "message" 노드를 Message로 변환
        Type messageType = new TypeToken<Message>() {}.getType();
        Message resultMessage = new Gson().fromJson(messageJson, messageType);
		assertEquals(1, resultMessage.getMessageId());
		assertEquals(firedata01.getFireSeq() + " 의 데이터가 조회되었습니다", resultMessage.getMessageContents());
		
		flag = fireDataMapper.doDelete(firedata01);
		assertEquals(1, flag);
	}

	@Ignore
	@Test
	public void doUpdateData() throws Exception{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doUpdateData()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		int flag = fireDataMapper.doSave(firedata01);
		assertEquals(1, flag);
		
		int seq = fireDataMapper.getSequence();
		firedata01.setFireSeq(seq);
		
		//요청 매핑
		MockHttpServletRequestBuilder requestBuilder =
			MockMvcRequestBuilders.post("/manage/doUpdateData.do")
			.param("fireSeq", firedata01.getFireSeq()+"")
			.param("injuredSum", "2")
			.param("dead", "1")
			.param("injured", "1")
			.param("amount", "50")
			.param("subFactor", "1000")
			.param("subLoc", "100")
			.param("subCity", "11010")
			.param("modId", "admin2");
	
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
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("화재정보가 수정되었습니다.", resultMessage.getMessageContents());
		
	}

	@Ignore
	@Test
	public void doDeleteData() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doDelete()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		int flag = fireDataMapper.doSave(firedata01);
		assertEquals(1, flag);
		
		int seq = fireDataMapper.getSequence();
		firedata01.setFireSeq(seq);
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ seq : " + seq);
		log.debug("│ data : " + firedata01);
		log.debug("└─────────────────────────────────────────────────────────");
		
		//요청 매핑
		MockHttpServletRequestBuilder requestBuilder =
			MockMvcRequestBuilders.get("/manage/doDeleteData.do")
			.param("fireSeq", firedata01.getFireSeq()+"");
		
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
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("화재 데이터가 삭제되었습니다.", resultMessage.getMessageContents());
		
	}	

	@Ignore
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
		assertEquals("등록이 완료되었습니다 1건의 메일이 전송되었습니다", resultMessage.getMessageContents());
		
		int seq = fireDataMapper.getSequence();
		firedata01.setFireSeq(seq);
		
		int flag = fireDataMapper.doDelete(firedata01);
		assertEquals(1, flag);
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
