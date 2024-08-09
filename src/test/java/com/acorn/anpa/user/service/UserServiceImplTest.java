package com.acorn.anpa.user.service;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.mapper.UserMapper;
import com.acorn.anpa.member.domain.Member;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest implements PLog {
    
    @Autowired
    ApplicationContext context;
    
    @Autowired
    UserMapper userMapper;
    
    @Autowired
	@Qualifier("userServiceImpl")
	UserService userService;
    
    List<Member> users;

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
