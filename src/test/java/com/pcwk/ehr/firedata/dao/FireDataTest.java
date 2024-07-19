package com.pcwk.ehr.firedata.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

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

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.firedata.domain.Firedata;
import com.pcwk.ehr.mapper.FireDataMapper;

@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 알파벳 순서대로 메서드 실행
public class FireDataTest implements PLog {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	FireDataMapper fireMapper;
	
	Firedata fire01;
	
	public void isSameUser(Firedata fireIn, Firedata fireOut) {
		assertEquals(fireIn.getFireSeq(), fireOut.getFireSeq());
		assertEquals(fireIn.getInjuredSum(), fireOut.getInjuredSum());
		assertEquals(fireIn.getDead(), fireOut.getDead());
		assertEquals(fireIn.getInjured(), fireOut.getInjured());
		assertEquals(fireIn.getAmount(), fireOut.getAmount());
		assertEquals(fireIn.getSubFactor(), fireOut.getSubFactor());
		assertEquals(fireIn.getSubLoc(), fireOut.getSubLoc());
		assertEquals(fireIn.getSubCity(), fireOut.getSubCity());
	}
	
	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUp()");
		log.debug("└─────────────────────────────────────────────────────────");
	
		fire01 = new Firedata(1, 0, 0, 0, 0, 1000, 100, 11010, "admin1", "admin1", "SYSDATE", "SYSDATE");
	
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown()");
		log.debug("└─────────────────────────────────────────────────────────");
	}

	@Test
	public void doSave() throws SQLException{
		// 1. 데이터 1건 입력
		int flag = fireMapper.doSave(fire01);	
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doSave(flag) : " + flag);
		log.debug("└─────────────────────────────────────────────────────────");
		assertEquals(1, flag);
		
	}
	
	@Ignore
	@Test
	public void beans() {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ beans()");
		log.debug("│ context : " + context);
		log.debug("│ fireMapper : " + fireMapper);
		log.debug("└─────────────────────────────────────────────────────────");
		assertNotNull(context);
		assertNotNull(fireMapper);
	}

}
