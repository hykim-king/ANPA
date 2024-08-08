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
    
    Member user01;

    @Before
    public void setUp() throws Exception {
        log.debug("┌──────────────────────────────────────────┐");
        log.debug("│ setUp()                                  │");
        log.debug("└──────────────────────────────────────────┘");

        // 기본 필드를 모두 포함하여 Member 객체 생성
        user01 = new Member("dao", "54321", "김다오", "dao@test.com", 1, 0, 11010, "010-1234-6789");
        
        // 회원가입
        //userMapper.signUp(user01);
        
        // 회원가입 전에 ID 중복 체크
        if (userMapper.idCheck(user01.getUserId()) == 0) {
            userMapper.signUp(user01);
        } else {
            log.debug("User ID already exists: " + user01.getUserId());
        }
    }
    
    @After
    public void tearDown() throws Exception {
        log.debug("┌────────────────────────────────────────┐");
        log.debug("│ tearDown()                             │");
        log.debug("└────────────────────────────────────────┘");

        // 테스트 데이터 삭제
        //userMapper.deleteAll();
    }

    @Test
    public void login() throws SQLException {
        log.debug("┌─────────────────────────────────────┐");
        log.debug("│ login()                             │");
        log.debug("└─────────────────────────────────────┘");

        Member result = userMapper.login("dao", "54321");
        log.debug("Login result: " + result);
        assertNotNull(result);
        assertEquals("dao", result.getUserId());
    }
    
    @Test
    public void signUp() throws SQLException {
        log.debug("┌─────────────────────────────────────┐");
        log.debug("│ signUp()                            │");
        log.debug("└─────────────────────────────────────┘");

        Member newUser = new Member("newUser", "newPassword123", "New User", "new@example.com", 0, 0, 0, "010-0000-0000");
        
        // 회원가입 전에 ID 중복 체크
        if (userMapper.idCheck(newUser.getUserId()) == 0) {
            int result = userMapper.signUp(newUser);
            assertEquals(1, result);
        } else {
            log.debug("User ID already exists: " + newUser.getUserId());
        }
        
        Member fetchedUser = userMapper.login("newUser", "newPassword123");
        assertNotNull(fetchedUser);
        assertEquals("newUser", fetchedUser.getUserId());
    }
    
    @Test
    public void idCheck() throws SQLException {
        log.debug("┌──────────────────────────────────────┐");
        log.debug("│ idCheck()                            │");
        log.debug("└──────────────────────────────────────┘");

        int result = userMapper.idCheck(user01.getUserId());
        assertEquals(1, result);

        Member nonExistingUser = new Member("nonExist", "54321", "No One", "none@test.com", 1, 0, 11010, "010-0000-0000");
        result = userMapper.idCheck(nonExistingUser.getUserId());
        assertEquals(0, result);
    }


    @Test
    public void findUserId() throws SQLException {
        log.debug("┌──────────────────────────────────────┐");
        log.debug("│ findUserId()                         │");
        log.debug("└──────────────────────────────────────┘");

        String result = userMapper.findUserId("김다오", "dao@test.com");
        assertEquals("dao", result);
    }

    @Test
    public void findPassword() throws SQLException {
        log.debug("┌─────────────────────────────────────┐");
        log.debug("│ findPassword()                      │");
        log.debug("└─────────────────────────────────────┘");

        String result = userMapper.findPassword("dao", "김다오", "dao@test.com");
        assertEquals("54321", result);
    }
    
    @Ignore
	@Test
	public void beans() {
		log.debug("┌──────────────────────────────────────────┐");
		log.debug("│ beans()                                  │");
		log.debug("└──────────────────────────────────────────┘");
		log.debug("context:" + context);
		log.debug("userMapper:" + userMapper);
		
		assertNotNull(context);
		assertNotNull(userMapper);
	}
}
