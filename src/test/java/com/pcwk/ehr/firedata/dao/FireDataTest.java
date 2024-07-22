package com.pcwk.ehr.firedata.dao;

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

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.Search;
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
	Search 	search;
	
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
	
	@Ignore
	@Test
	public void doRetrieve() throws SQLException {
		// 검색 페이징 설정
		search.setPageNo(1);
		search.setPageSize(10);
		
		// 검색 조건 설정
		search.setSearchDiv("10");
		search.setSearchWord("");
		
		List<Firedata> list = fireMapper.doRetrieve(search);
		assertEquals(10, list.size());		
	}
	
	@Ignore
	@Test
	public void doUpdate() throws SQLException{
		// 1. 전체삭제
		fireMapper.deleteAll();
		
		// 2. 데이터 1건 입력
		int flag = fireMapper.doSave(fire01);	
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doSave(flag) : " + flag);
		log.debug("└─────────────────────────────────────────────────────────");
		assertEquals(1, flag);
		
		// 2_1. 시퀀스 세팅
		int seq = fireMapper.getSequence();
		log.debug("seq" + seq);
		fire01.setFireSeq(seq);
		
		// 3. 단건 조회
		Firedata outVO01 = fireMapper.doSelectOne(fire01);
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doSelectOne(flag) : " + flag);
		log.debug("└─────────────────────────────────────────────────────────");
		assertNotNull(outVO01);
		isSameUser(fire01, outVO01);
		
		// 4. 조회 데이터로 데이터 수정 및 Update
		String updateStr = "_U";
		
		outVO01.setInjuredSum(10);
		outVO01.setDead(5);
		outVO01.setInjured(5);
		outVO01.setAmount(5000);
		outVO01.setSubFactor(5555);
		outVO01.setSubLoc(555);
		outVO01.setSubCity(55555);
		outVO01.setModId(outVO01.getModId() + updateStr);
		outVO01.setFireSeq(outVO01.getFireSeq());
		
		flag = fireMapper.doUpdate(outVO01);		
		assertEquals(1, flag);
		
		// 5. 수정된데이터 조회
		Firedata upOutVO = fireMapper.doSelectOne(outVO01);
		
		// 6. 비교	
		isSameUser(upOutVO, outVO01);
	}
	
	@Test
	public void doSave() throws SQLException{
		// 1. 전체삭제
		fireMapper.deleteAll();
		
		// 2. 데이터 1건 입력
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
