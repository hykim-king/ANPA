package com.pcwk.ehr.firedata.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.mail.MailSender;
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
import com.pcwk.ehr.mapper.FireDataMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class FireDataServiceImplTest implements PLog {

    @Autowired
    private ApplicationContext context;
    
    @Autowired
    FireDataService fireDataService;
    
    @Autowired
    FireDataMapper fireDataMapper;
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private PlatformTransactionManager transactionManager;
    
    @Autowired
    private MailSender mailSender; // Autowire MailSender for test
    
    Firedata fireData01;
    Firedata fireData02;
    Firedata fireData03;
    Firedata fireData04;
    
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
        
        // 0. 전체 삭제
        fireDataMapper.deleteAll();
        
        fireData01 = new Firedata(1, 0, 0, 0, 10, 1000, 100, 11010, "sjm", "SYSDATE", "sjm", "SYSDATE");
        
    }
    
    @After
    public void tearDown() throws Exception {
        log.debug("┌─────────────────────────────────────────────────────────");
        log.debug("│ tearDown()	                                             ");
        log.debug("└─────────────────────────────────────────────────────────");    
    }
    
    @Test
    public void doSaveData() throws Exception{
    	log.debug("┌─────────────────────────────────────────────────────────");
    	log.debug("│ doSaveData()");
    	log.debug("└─────────────────────────────────────────────────────────");    
    	
    	fireDataService.doSaveData(fireData01);
    }
    
    @Ignore
    @Test
    public void sendMail() {
		String title = "화재가 발생하였습니다";
		String contents = "";
		String userEmail = "anpa1995@naver.com";
    	
        assertNotNull(fireDataService);
        assertNotNull(mailSender);         
        fireDataService.sendEmail(title, contents, userEmail); 
        log.debug("┌─────────────────────────────────────────────────────────");
        log.debug("│ sendMail 작동()	                                             ");
        log.debug("└─────────────────────────────────────────────────────────");    
    }
    
    @Ignore
    @Test
    public void beans() {
        log.debug("┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰┰");
        log.debug("context : " + context);
        log.debug("┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻┻");
        assertNotNull(context);
    }
}
