
package com.pcwk.ehr.mapper;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcwk.ehr.board.domain.Board;
import com.pcwk.ehr.cmn.PLog;

@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class BoardMapperTest implements PLog{
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	BoardMapper boardMapper; 

	List<Board> boards;
	

	@Before
	public void setUp() throws Exception {
		log.debug("┌───────────────────────────");
		log.debug("│ @Before() ");
		log.debug("└───────────────────────────");	
		
		boards = Arrays.asList(
				new Board(999, "제목01", 999, "내용이지1", 0, "admin", "안씀", "admin", "안씀"),
				new Board(1000, "제목02", 999, "내용이지2", 0, "admin", "안씀", "admin", "안씀"),
				new Board(1001, "제목03", 999, "내용이지3", 0, "admin", "안씀", "admin", "안씀"),
				new Board(1002, "제목04", 999, "내용이지4", 0, "admin", "안씀", "admin", "안씀")
		);
		
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌───────────────────────────");
		log.debug("│ @After() ");
		log.debug("└───────────────────────────");	
	}

	@Test
	public void addAndGet() throws SQLException {
		log.debug("┌───────────────────────────");
		log.debug("│ addAndGet() ");
		log.debug("└───────────────────────────");	
		
		Board board =  boards.get(0);
		
		int flag = boardMapper.doSave(board);
		assertEquals(1, flag);
		
	}
	
	@Ignore
	@Test
	public void beans() {
		log.debug("┌───────────────────────────");
		log.debug("│ beans() ");
		log.debug("└───────────────────────────");	
		
		log.debug("context: "+context);
		log.debug("boardMapper: "+boardMapper);
		assertNotNull(context);
		assertNotNull(boardMapper);
	}

}
