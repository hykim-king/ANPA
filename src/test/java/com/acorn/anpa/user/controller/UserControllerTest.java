package com.acorn.anpa.user.controller;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.mapper.UserMapper;
import com.acorn.anpa.member.domain.Member;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest implements PLog {
    
    @Autowired
    WebApplicationContext webApplicationContext;
	
	//브라우저 대신 Mock
	MockMvc mockMvc;
	
	List<Member> users;
    
    @Autowired
    UserMapper userMapper;
    
    Member user01;

    @Before
    public void setUp() throws Exception {
        log.debug("┌──────────────────────────────────────────┐");
        log.debug("│ setUp()                                  │");
        log.debug("└──────────────────────────────────────────┘");

    }
    
    @After
    public void tearDown() throws Exception {
        log.debug("┌────────────────────────────────────────┐");
        log.debug("│ tearDown()                             │");
        log.debug("└────────────────────────────────────────┘");

        // 테스트 데이터 삭제
        //userMapper.deleteAll();
    }

    
}
