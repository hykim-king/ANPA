package com.acorn.anpa.prevent.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
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

       
        prevent01 = new prevent();
 
    }

    @After
    public void tearDown() throws Exception {
        log.debug("┌─────────────────────────────────────────────────────────");
        log.debug("│ tearDown()                                                  ");
        log.debug("└─────────────────────────────────────────────────────────");
    }

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

    @Test
    public void testDoSelectOne() throws SQLException {
        log.debug("┌─────────────────────────────────────────────────────────");
        log.debug("│ testDoSelectOne()                                       ");
        log.debug("└─────────────────────────────────────────────────────────");

        prevent result = preventMapper.doSelectOne(1);
        assertNotNull(result);
        log.debug("result: " + result);
    }

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
