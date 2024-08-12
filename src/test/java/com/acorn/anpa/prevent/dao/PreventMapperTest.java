package com.acorn.anpa.prevent.dao;
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
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.mapper.PreventMapper;
import com.acorn.anpa.prevent.domain.prevent;
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 알파벳 순서대로 메서드 실행
public class PreventMapperTest implements PLog {
    
    @Autowired
    ApplicationContext context;
    @Autowired
    PreventMapper preventMapper;
    prevent prevent01;
    prevent prevent02;
    prevent prevent03;
    
    Search  search;
    
    @Before
    public void setUp() throws Exception {
        log.debug("┌──────────────────────────────┐");
        log.debug("│ setUp()                      │");
        log.debug("└──────────────────────────────┘");
        preventMapper = context.getBean(PreventMapper.class); // context에서 PreventMapper 빈을 가져옴
        prevent01 = new prevent(1, "제목01", 0, "내용01", "이미지01");
        prevent02 = new prevent(2, "제목02", 0, "내용02", "이미지02");
        prevent03 = new prevent(3, "제목03", 0, "내용03", "이미지03");
        prevent01.setRegId("ADMIN01");
        prevent02.setRegId("ADMIN02");
        prevent03.setRegId("ADMIN03");
        // 테스트 데이터를 미리 삽입
        int flag = preventMapper.doSave(prevent01);
        assertEquals("prevent01 데이터 삽입 실패", 1, flag);
        flag = preventMapper.doSave(prevent02);
        assertEquals("prevent02 데이터 삽입 실패", 1, flag);
        flag = preventMapper.doSave(prevent03);
        assertEquals("prevent03 데이터 삽입 실패", 1, flag);
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
        log.debug("┌────────────────────────────────────────────────────────");
        log.debug("│ doSave()                                              ");
        log.debug("└────────────────────────────────────────────────────────");
        
        // 2. 데이터 1건 입력
        int flag = preventMapper.doSave(prevent01);
        log.debug("flag : " + flag);
        assertEquals(1, flag);
        
        flag = preventMapper.doSave(prevent02);
        log.debug("flag : " + flag);
        assertEquals(1, flag);
        
        flag = preventMapper.doSave(prevent03);
        log.debug("flag : " + flag);
        assertEquals(1, flag);
        
    }
    
    
    
    @Ignore
    @Test
    public void doSelectOne() throws SQLException {
        // Given: 특정 prevent_seq 값으로 데이터를 미리 생성했다고 가정
        int preventSeq = 1; // 테스트하려는 데이터의 시퀀스 값
        // When: 단건 조회 쿼리 실행
        prevent inVO = preventMapper.doSelectOne(preventSeq);
        // Then: 결과값 검증
        assertNotNull("Prevent 객체는 null이 아니어야 합니다.", inVO);
        assertEquals("PreventSeq 값이 일치해야 합니다.", preventSeq, inVO.getPreventSeq());
        
        // 기타 필드 값 검증 (예시)
        assertEquals("Title 값이 일치해야 합니다.", "Expected Title", inVO.getTitle());
        assertEquals("Contents 값이 일치해야 합니다.", "Expected Contents", inVO.getContents());
        assertEquals("ImgSrc 값이 일치해야 합니다.", "Expected ImgSrc", inVO.getImgSrc());
        assertEquals("RegId 값이 일치해야 합니다.", "Expected RegId", inVO.getRegId());
        // 등록일과 수정일이 null이 아닌지 확인 (필요에 따라 추가 검증 가능)
        assertNotNull("RegDt는 null이 아니어야 합니다.", inVO.getRegDt());
        assertNotNull("ModDt는 null이 아니어야 합니다.", inVO.getModDt());
    }
  
    
    
    @Ignore
    @Test
    public void doRetrieve() throws SQLException { //여러 개의 Prevent 객체를 조회하는 메서드
        log.debug("┌─────────────────────────────────────────────────────────");
        log.debug("│doRetrieve()                                             ");
        log.debug("└─────────────────────────────────────────────────────────");
       
     // 2. 데이터 1건 입력
        int flag = preventMapper.doSave(prevent01);
        log.debug("flag : " + flag);
        assertEquals(1, flag);
        
        flag = preventMapper.doSave(prevent02);
        log.debug("flag : " + flag);
        assertEquals(1, flag);
        
        flag = preventMapper.doSave(prevent03);
        log.debug("flag : " + flag);
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
}