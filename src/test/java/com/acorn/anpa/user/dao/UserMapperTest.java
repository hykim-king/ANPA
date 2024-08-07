package com.acorn.anpa.user.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserMapperTest implements PLog {
    
    @Autowired
    ApplicationContext context;
    
    @Autowired
    UserMapper userMapper;
    
    Member user01;

    @Before
    public void setUp() throws Exception {
        log.debug("┌──────────────────────────────────────────┐");
        log.debug("│ setUp()                                  │");
        log.debug("└──────────────────────────────────────────┘");

        // 기본 필드를 모두 포함하여 Member 객체 생성
        user01 = new Member("testUser", "password123", "Test Name", "test@example.com", 1, 0, 0, "010-1234-5678");
        
        // 회원가입
        userMapper.signUp(user01);
    }

    @Test
    public void login() throws SQLException {
        log.debug("┌─────────────────────────────────────┐");
        log.debug("│ login()                             │");
        log.debug("└─────────────────────────────────────┘");

        Member result = userMapper.login("testUser", "password123");
        assertNotNull(result);
        assertEquals("testUser", result.getUserId());
    }
    
    @Test
    public void signUp() throws SQLException {
        log.debug("┌─────────────────────────────────────┐");
        log.debug("│ signUp()                            │");
        log.debug("└─────────────────────────────────────┘");

        Member newUser = new Member("newUser", "newPassword123", "New User", "new@example.com", 0, 0, 0, "010-0000-0000");
        int result = userMapper.signUp(newUser);
        assertEquals(1, result);
        
        Member fetchedUser = userMapper.login("newUser", "newPassword123");
        assertNotNull(fetchedUser);
        assertEquals("newUser", fetchedUser.getUserId());
    }
    
    @Test
    public void idDuplicateCheck() throws SQLException {
        log.debug("┌──────────────────────────────────────┐");
        log.debug("│ idDuplicateCheck()                   │");
        log.debug("└──────────────────────────────────────┘");

        int result = userMapper.idDuplicateCheck(user01);
        assertEquals(0, result);

        Member duplicateMember = new Member("testUser", "anotherPassword", "Another Name", "another@example.com", 1, 0, 0, "010-8765-4321");
        userMapper.signUp(duplicateMember);
        result = userMapper.idDuplicateCheck(duplicateMember);
        assertEquals(1, result);
    }

    @Test
    public void findUserId() throws SQLException {
        log.debug("┌──────────────────────────────────────┐");
        log.debug("│ findUserId()                         │");
        log.debug("└──────────────────────────────────────┘");

        String result = userMapper.findUserId("Test Name", "test@example.com");
        assertEquals("testUser", result);
    }

    @Test
    public void findPassword() throws SQLException {
        log.debug("┌─────────────────────────────────────┐");
        log.debug("│ findPassword()                      │");
        log.debug("└─────────────────────────────────────┘");

        String result = userMapper.findPassword("testUser", "Test Name", "test@example.com");
        assertEquals("password123", result);
    }

    @After
    public void tearDown() throws Exception {
        log.debug("┌────────────────────────────────────────┐");
        log.debug("│ tearDown()                             │");
        log.debug("└────────────────────────────────────────┘");

       
    }
}
