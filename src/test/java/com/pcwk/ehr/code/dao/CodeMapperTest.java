package com.pcwk.ehr.code.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.code.domain.Code;


@RunWith(SpringRunner.class) //스프링 컨텍스트 프레임워크의 JUnit 확장기능 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class CodeMapperTest implements PLog{

	@Autowired
	ApplicationContext context;
	
	@Autowired
	CodeMapper codeMapper;
	
	Code code;
	List<Code> codeList;
	
	@Before
	public void setUp() throws Exception {
		log.debug("┌──────────────────────────────┐");
		log.debug("│ @Before                      │");
		log.debug("└──────────────────────────────┘");	
		
		codeList = new ArrayList<Code>();
		code = new Code();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌──────────────────────────────┐");
		log.debug("│ @After                       │");
		log.debug("└──────────────────────────────┘");	
	}

	
	@Test
	public void doRetrieve() throws Exception {
		log.debug("┌──────────────────────────────┐");
		log.debug("│ doRetrieve()                 │");
		log.debug("└──────────────────────────────┘");	
		
		code.setMasterCode("location");
		
		codeList = codeMapper.doRetrieve(code);
		assertNotNull(codeList);
		
		for(Code vo : codeList) {
			log.debug(vo);
		}
		
	}
	
	@Ignore
	@Test
	public void beans() {
		log.debug("┌──────────────────────────────┐");
		log.debug("│ beans()                      │");
		log.debug("└──────────────────────────────┘");	
		
		log.debug("context: "+context);
		log.debug("codeMapper: "+codeMapper);
		assertNotNull(context);
		assertNotNull(codeMapper);
	}

}
