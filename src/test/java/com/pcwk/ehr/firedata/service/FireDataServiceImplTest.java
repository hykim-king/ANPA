package com.pcwk.ehr.firedata.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.firedata.domain.Firedata;

@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class FireDataServiceImplTest implements PLog {
	@Autowired
	ApplicationContext context;
	
	@Autowired
	FireDataService fireDataService;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	List<Firedata> fires;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUpBeforeClass()                                      ");
		log.debug("└─────────────────────────────────────────────────────────");	
	}
	
	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUp()	                                             ");
		log.debug("└─────────────────────────────────────────────────────────");	
	}
	
	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown()	                                             ");
		log.debug("└─────────────────────────────────────────────────────────");	
	}
	
	@Test
	public void sendMail() {
		FireDataServiceImpl firedataServiceImple = new FireDataServiceImpl();
	    firedataServiceImple.sendEmail();
	}

	@Ignore
	@Test
	public void beans() {
		log.debug("┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰");
		log.debug("context : " + context);
		log.debug("┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻");
		assertNotNull(context);
	}
}
