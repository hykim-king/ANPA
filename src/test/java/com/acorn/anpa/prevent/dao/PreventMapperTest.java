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
import com.acorn.anpa.prevent.domain.Prevent;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 알파벳 순서대로 메서드 실행
public class PreventMapperTest implements PLog {
    
    @Autowired
    ApplicationContext context;
    @Autowired
    PreventMapper preventMapper;
    Prevent prevent01;
    Prevent prevent02;
    Prevent prevent03;
    
    Search  search;
    
    @Before
    public void setUp() throws Exception {
        log.debug("┌──────────────────────────────┐");
        log.debug("│ setUp()                      │");
        log.debug("└──────────────────────────────┘");
        preventMapper = context.getBean(PreventMapper.class); // context에서 PreventMapper 빈을 가져옴
        prevent01 = new Prevent(10, "제목01", 0, "내용01", "이미지01");
        prevent02 = new Prevent(20, "제목02", 0, "내용02", "이미지02");
        prevent03 = new Prevent(30, "제목03", 0, "내용03", "이미지03");
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
        
        
        search=new Search();
        
    }
        
        
   
    @After
    public void tearDown() throws Exception {
        log.debug("┌─────────────────────────────────────────────────────────");
        log.debug("│ tearDown()                                              ");
        log.debug("└─────────────────────────────────────────────────────────");
    }
    
    @Ignore
    @Test
    public void doSave() {
        try {
            log.debug("┌────────────────────────────────────────────────────────");
            log.debug("│ doSave()                                              ");
            log.debug("└────────────────────────────────────────────────────────");
            
            int flag = preventMapper.doSave(prevent01);
            log.debug("flag : " + flag);
            assertEquals(1, flag);
            
            flag = preventMapper.doSave(prevent02);
            log.debug("flag : " + flag);
            assertEquals(1, flag);
            
            flag = preventMapper.doSave(prevent03);
            log.debug("flag : " + flag);
            assertEquals(1, flag);
        } catch (Exception e) {
            log.error("Error in doSave: ", e);
        }
    }

    @Ignore
    @Test
    public void doSelectOne() throws SQLException {
        // Given: 특정 prevent_seq 값으로 데이터를 미리 생성했다고 가정
        // 데이터베이스에 미리 데이터를 삽입했다고 가정합니다. (보통 테스트 데이터 설정은 테스트 메서드 전에 수행됩니다)

        // 데이터 삽입 (여기서는 데이터를 미리 삽입한 것으로 가정하고, 실제로는 삽입 코드를 작성할 수도 있음)
        int flag = preventMapper.doSave(prevent01); 
        assertEquals(1, flag);  // 데이터 삽입이 성공했는지 검증
        
        // prevent01 객체의 preventSeq를 설정
        int seq = preventMapper.getSequence();
        prevent01.setPreventSeq(seq);

        // When: 단건 조회 쿼리 실행
        Prevent inVO = preventMapper.doSelectOne(prevent01);

        // Then: 결과 검증
        log.debug("inVO: " + inVO);
        
        // inVO가 null이 아닌지 확인
        assertNotNull(inVO);
        
        // prevent01과 조회된 inVO가 일치하는지 확인
        assertEquals(prevent01.getPreventSeq(), inVO.getPreventSeq());
        assertEquals(prevent01.getTitle(), inVO.getTitle());
        assertEquals(prevent01.getContents(), inVO.getContents());
        // 추가적으로 검증할 필요가 있는 필드가 있다면 여기에서 검증합니다.
    } 
    
    
    @Test
    public void doRetrieve() throws SQLException { //여러 개의 Prevent 객체를 조회하는 메서드
        log.debug("┌─────────────────────────────────────────────────────────");
        log.debug("│doRetrieve()                                             ");
        log.debug("└─────────────────────────────────────────────────────────");
       
      //2. 데이터 1건 입력
        int flag = preventMapper.doSave(prevent01);
        log.debug("flag : " + flag);
        assertEquals(1, flag);
        
        flag = preventMapper.doSave(prevent02);
        log.debug("flag : " + flag);
        assertEquals(1, flag);
        
        flag = preventMapper.doSave(prevent03);
        log.debug("flag : " + flag);
        assertEquals(1, flag);
        
        search.setSearchDiv("20"); //string 이니까 ""
                   search.setSearchWord("내용");                //  10>>제목 검색    
        List<Prevent> list = preventMapper.doRetrieve(search);
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