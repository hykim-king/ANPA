package com.acorn.anpa.firedata.dao;

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
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.mapper.FireDataMapper;

@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 알파벳 순서대로 메서드 실행
public class FireDataMapperTest implements PLog {

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
		
		// 0. 전체삭제 + fire_seq초기화
		//fireMapper.deleteAll();
		
		fire01 = new Firedata(1, 0, 0, 0, 0, 1000, 100, 11010);
		fire01.setRegId("admin1");
		search = new Search();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown()");
		log.debug("└─────────────────────────────────────────────────────────");
	}
	
	@Ignore
	@Test
	public void mainData() throws SQLException{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ mainData()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		Firedata inVO = fireMapper.doMainData();
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ inVO() : " + inVO);
		log.debug("└─────────────────────────────────────────────────────────");
	}
	
	@Test
	public void totalDataList() throws SQLException{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ totalData()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		search.setSearchDateStart("2023-03-01");
		search.setSearchDateEnd("2023-08-01");
		search.setSearchDiv("10");
		search.setBigNm("부주의");
		
		List<Firedata> outVO = fireMapper.totalDataList(search);
		for(Firedata vo : outVO) {
			log.debug("vo: "+vo);
			//테스트 해야되`~~ 꼬였어
			//테스트 해야되`~~ 꼬였어
			//테스트 해야되`~~ 꼬였어
			//테스트 해야되`~~ 꼬였어
			//테스트 해야되`~~ 꼬였어
		}
	}
	
	@Ignore
	@Test
	public void doRetrieve() throws SQLException {
		search.setPageNo(1);
		search.setPageSize(10);
		
		List<Firedata> list = fireMapper.doRetrieve(search);
		assertEquals(10, list.size());
	}
	
	@Ignore
	@Test
	public void totalData() throws Exception{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ totalData()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		search.setSearchDateStart("20230301");
		search.setSearchDateEnd("20230801");
		search.setSubCityBigNm("경기도");
		search.setSubCityMidNm("부천시");
		
		search.setSearchDiv("10");
		search.setBigNm("부주의");
		
		Firedata outVO = fireMapper.totalData(search);
		assertNotNull(outVO);
		
		search.setSearchDiv("20");
		search.setMidNm("불장난");
		
		outVO = fireMapper.totalData(search);
		assertNotNull(outVO);
		
		search.setSearchDiv("30");
		search.setBigNm("숙박시설");
		
		outVO = fireMapper.totalData(search);
		assertNotNull(outVO);
		
		search.setSearchDiv("40");
		search.setMidNm("모텔");
		
		outVO = fireMapper.totalData(search);
		assertNotNull(outVO);
	}
	
	@Ignore
	@Test
	public void doDelete() throws SQLException{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doDeleteMapper()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		int flag = fireMapper.doSave(fire01);	
		assertEquals(1, flag);
		
		int seq = fireMapper.getSequence();		
		fire01.setFireSeq(seq);
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ fire01()" + fire01);
		log.debug("└─────────────────────────────────────────────────────────");
		
		flag = fireMapper.doDelete(fire01);
		assertEquals(1, flag);
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ deleteComplete()");
		log.debug("└─────────────────────────────────────────────────────────");
	}
	
	@Ignore
	@Test
	public void doSelectOne() throws SQLException{
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doSelectOne()");
		log.debug("└─────────────────────────────────────────────────────────");
		
		int flag = fireMapper.doSave(fire01);	
		assertEquals(1, flag);
		
		int seq = fireMapper.getSequence();
		
		fire01.setFireSeq(seq);
		
		Firedata inVO = fireMapper.doSelectOne(fire01);
		log.debug(inVO);
	}
	
	@Ignore
	@Test
	public void doUpdate() throws SQLException{
		
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

	@Ignore
	@Test
	public void doSave() throws SQLException{
		
		// 2. 데이터 1건 입력
		fire01.setRegId("admin1");
		
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
