package com.acorn.anpa.member.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.mapper.MemberMapper;
import com.acorn.anpa.member.domain.Member;

@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 알파벳 순서대로 메서드 실행
public class MemberMapperTest implements PLog {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	MemberMapper memberMapper;
	
	String subCity01;
	
	Member memberVO01;
	
	Search search;
	
	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUp()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		subCity01 = "11010";
		
		memberVO01 = new Member("james01", "4321", "이상무01", "bagsa1515@naver.com", 0, 0, 11010, "01012345678");
		
		search = new Search();
	}
	
	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown()");
		log.debug("└─────────────────────────────────────────────────────────");
	}
	
	@Test
	public void addAndDel() throws SQLException{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ addAndDel()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		// 1건 등록
		int flag = memberMapper.doSave(memberVO01);
		log.debug("flag : " + flag);
		assertEquals(1, flag);
		if(flag == 1) {			
			log.debug("┌─────────────────────────────────────────────────────────");
			log.debug("│ memberVO01 : " + memberVO01);
			log.debug("└─────────────────────────────────────────────────────────");
			
			flag = memberMapper.doDelete(memberVO01);
			assertEquals(1, flag);
		}else {
			log.debug("┌─────────────────────────────────────────────────────────");
			log.debug("│ memberVO01 doSave Failed");
			log.debug("└─────────────────────────────────────────────────────────");
		}
	}
	
	@Ignore
	@Test
	public void doRetrieve() throws SQLException {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doRetrieveTest()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		search.setPageNo(1);
		search.setPageSize(10);
		
		// 조건	user_id(10), user_name(20), email(30), sub_city(40), tel(50)
//		search.setSearchDiv("10");
//		search.setSearchWord("newUser");
		List<Member>list = memberMapper.doRetrieve(search);
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ list : " + list);
		log.debug("└─────────────────────────────────────────────────────────");
	}
	
	@Ignore
	@Test
	public void doRetrieveLocEmail() throws SQLException {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doRetrieveLocEmail()");
		log.debug("└─────────────────────────────────────────────────────────");
		List<Member> list = memberMapper.doRetrieveLocEmail(subCity01);
		
	    for (Member member : list) {
	        log.debug("┌─────────────────────────────────────────────────────────");
	        log.debug("│ Member : " + member.getUserName() + " Email : " + member.getEmail());
	        log.debug("└─────────────────────────────────────────────────────────");
	    }
	}
	
	@Ignore
	@Test
	public void beans() {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ beans()");
		log.debug("│ context : " + context);
		log.debug("│ memberMapper : " + memberMapper);
		log.debug("└─────────────────────────────────────────────────────────");
		assertNotNull(context);
		assertNotNull(memberMapper);
	}

}
