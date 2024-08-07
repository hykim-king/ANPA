package com.acorn.anpa.firedata.dao;

import static org.junit.Assert.*;

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
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.mapper.MonthFireDataMapper;
@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 알파벳 순서대로 메서드 실행
public class MonthFireDataMapperTest implements PLog{

	@Autowired
	ApplicationContext context;
	
	@Autowired
	MonthFireDataMapper monthFireDataMapper;
	
	Firedata fire01;
	
	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUp()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		fire01 = new Firedata(1, 0, 0, 0, 0, 1000, 100, 11010);
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown()");
		log.debug("└─────────────────────────────────────────────────────────");
	
	}
	//@Ignore
	@Test
	public void factorMidData()throws Exception{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ todayMonthData()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		
		List<Firedata> result = monthFireDataMapper.factorMidData("202308");
		log.debug("result:"+result);
	}
	@Ignore
	@Test
	public void locMidData()throws Exception{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ todayMonthData()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		
		List<Firedata> result = monthFireDataMapper.locMidData("202308");
		log.debug("result:"+result);
	}
	@Ignore
	@Test
	public void locBigData()throws Exception{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ todayMonthData()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		
		List<Firedata> result = monthFireDataMapper.locBigData("202308");
		log.debug("result:"+result);
	}
	@Ignore
	@Test
	public void todayMonthData()throws Exception{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ todayMonthData()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		
		Firedata result = monthFireDataMapper.todayMonthData("202308");
		log.debug("result:"+result);
	}
	
	@Ignore
	@Test
	public void beans() {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ beans()");
		log.debug("│ context : " + context);
		log.debug("│ MonthFireDataMapper : " + monthFireDataMapper);
		log.debug("└─────────────────────────────────────────────────────────");
		assertNotNull(context);
		assertNotNull(monthFireDataMapper);
	}

}
