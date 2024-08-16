package com.acorn.anpa.user.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

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
    
    Member member01;

    @Before
    public void setUp() throws Exception {
        log.debug("┌─────────────────────────────────────────────────────────┐");
        log.debug("│ setUp()                                                 │");
        log.debug("└─────────────────────────────────────────────────────────┘");
        
        userMapper.deleteAll();

        member01 = new Member("dao", "54321", "김다오", "dao@test.com", 1, 0, 11010, "010-1234-6789");
        member01.setRegId("Admin");
        member01.setModId("Admin");
    }
    
    @Test
    public void login() throws SQLException {
        log.debug("┌─────────────────────────────────────────────────────┐");
        log.debug("│ login()                                             │");
        log.debug("└─────────────────────────────────────────────────────┘");
        
        // 회원 등록
        int flag = userMapper.signUp(member01);
        assertEquals(1, flag);
        
        // 로그인 성공 테스트
        Member loginMember = userMapper.login(member01);
        log.debug("로그인 성공!: {}", loginMember);
        assertNotNull(loginMember);
        assertEquals(member01.getUserId(), loginMember.getUserId());
        
        // 로그인 실패 테스트
        member01.setPassword("로그인 실패");
        loginMember = userMapper.login(member01);
        log.debug("Login Failure: {}", loginMember);
        assertNull(loginMember);
    }
    
    @Ignore
    @Test
    public void idCheck() throws SQLException {
        log.debug("┌─────────────────────────────────────────────────────┐");
        log.debug("│ idCheck()                                           │");
        log.debug("└─────────────────────────────────────────────────────┘");

        // ID 체크 테스트
        userMapper.signUp(member01);
        int result = userMapper.idCheck(member01);
        log.debug("ID Check (existing): {}", result);
        assertEquals(1, result);

        Member nonExistingMember = new Member("nonExistingUser", "", "", "", 0, 0, 0, "");
        result = userMapper.idCheck(nonExistingMember);
        log.debug("ID Check (non-existing): {}", result);
        assertEquals(0, result);
    }
    
    @Ignore
    @Test
    public void passwordCheck() throws SQLException {
        log.debug("┌─────────────────────────────────────────────────────┐");
        log.debug("│ passwordCheck()                                     │");
        log.debug("└─────────────────────────────────────────────────────┘");

        // 비밀번호 체크 테스트
        userMapper.signUp(member01);
        int result = userMapper.passwordCheck(member01);
        log.debug("Password Check (correct): {}", result);
        assertEquals(1, result);

        Member wrongPasswordMember = new Member("testUser01", "wrongPassword", "", "", 0, 0, 0, "");
        result = userMapper.passwordCheck(wrongPasswordMember);
        log.debug("Password Check (incorrect): {}", result);
        assertEquals(0, result);
    }
    
    @Ignore
    @Test
    public void testLoginInfo() throws SQLException {
        log.debug("┌─────────────────────────────────────────────────────────┐");
        log.debug("│ testLoginInfo()                                         │");
        log.debug("└─────────────────────────────────────────────────────────┘");

        // 로그인 정보 조회 테스트
        userMapper.signUp(member01);
        Member retrievedMember = userMapper.loginInfo(member01);
        log.debug("Login Info: {}", retrievedMember);
        assertNotNull(retrievedMember);
        assertEquals(member01.getUserId(), retrievedMember.getUserId());
    }
    
    @After
    public void tearDown() throws Exception {
        log.debug("┌─────────────────────────────────────────────────────────┐");
        log.debug("│ tearDown()                                              │");
        log.debug("└─────────────────────────────────────────────────────────┘");
        
        // 테스트 후 정리 작업
        //userMapper.deleteAll();
    }
}