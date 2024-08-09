package com.acorn.anpa.prevent.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

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

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.mapper.PreventMapper;
import com.acorn.anpa.prevent.domain.prevent;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class PreventMapperTest implements PLog {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private PreventMapper preventMapper;

    private prevent prevent01;
    private prevent prevent02;
    private prevent prevent03;
    
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        log.debug("┌─────────────────────────────────────────────────────────");
        log.debug("│ setUpBeforeClass()                                      ");
        log.debug("└─────────────────────────────────────────────────────────");
    }

    @Before
    public void setUp() throws Exception {
        log.debug("┌──────────────────────────────┐");
        log.debug("│ setUp()                      │");
        log.debug("└──────────────────────────────┘");

       
        prevent01 = new prevent("1","제목01", 0, "내용01", "", "sysdate", "ADMIN01", "sysdate","ADMIN01");
        prevent02 = new prevent("2","제목02", 0, "내용02", "", "sysdate", "ADMIN02", "sysdate","ADMIN02");
        prevent03 = new prevent("3","제목03", 0, "내용03", "", "sysdate", "ADMIN03", "sysdate","ADMIN03");
        
    }

    @After
    public void tearDown() throws Exception {
        log.debug("┌─────────────────────────────────────────────────────────");
        log.debug("│ tearDown()                                              ");
        log.debug("└─────────────────────────────────────────────────────────");
    }

    
    @Ignore
	@Test
	public void doSave() throws SQLException{
		
		// 2. 데이터 1건 입력
    	prevent01.setRegId("admin1");
		
		int flag = preventMapper.doSave(prevent01);	
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ doSave(flag) : " + flag);                                
		log.debug("└─────────────────────────────────────────────────────────");
		assertEquals(1, flag);
		
	}
    
    
    
    @Ignore
    @Test
    public void beans() {
        log.debug("┌─────────────────────────────────────────────────────────");
        log.debug("│ beans()                                                 ");
        log.debug("└─────────────────────────────────────────────────────────");
        log.debug("context:" + context);
        log.debug("preventMapper:" + preventMapper);
        
        assertNotNull(context);
        assertNotNull(preventMapper);
    }
  //  @Ignore
    @Test
    public void doSelectOne() throws SQLException {
        log.debug("┌─────────────────────────────────────────────────────────");
        log.debug("│ doSelectOne()                                           ");
        log.debug("└─────────────────────────────────────────────────────────");

        // 1. 데이터 저장
        prevent testPrevent = new prevent("1", "Test Title", 0, "Test Content", "", "sysdate", "ADMIN01", "sysdate", "ADMIN01");
        int saveFlag = preventMapper.doSave(testPrevent);
        log.debug("Save flag: " + saveFlag);
        assertEquals(1, saveFlag); // Ensure that the data is saved successfully

        // 2. 데이터 조회
        prevent result = preventMapper.doSelectOne(Integer.parseInt(testPrevent.getPreventSeq()));
        log.debug("Result: " + result);

        // 3. 데이터 검증
        assertNotNull(result);
        assertEquals(testPrevent.getPreventSeq(), result.getPreventSeq());
        assertEquals(testPrevent.getTitle(), result.getTitle());
        assertEquals(testPrevent.getContents(), result.getContents());
        assertEquals(testPrevent.getRegId(), result.getRegId());
        // Add more assertions as needed to fully verify the result
    }                    


    @Ignore
    @Test
    public void testDoRetrieve() throws SQLException {
        log.debug("┌─────────────────────────────────────────────────────────");
        log.debug("│ testDoRetrieve()                                       ");
        log.debug("└─────────────────────────────────────────────────────────");

        Search search = new Search();
        search.setPageNo(1);
        search.setPageSize(10);
        search.setSearchWord("example"); // Set appropriate search parameters

        List<prevent> results = preventMapper.doRetrieve(search);
        assertNotNull(results);
        assertTrue(!results.isEmpty());
        log.debug("results: " + results);
    }
}
