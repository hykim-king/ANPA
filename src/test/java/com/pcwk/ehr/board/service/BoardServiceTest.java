package com.pcwk.ehr.board.service;

import static org.junit.Assert.*;

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
import com.pcwk.ehr.mapper.BoardMapper;

@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class BoardServiceTest implements PLog {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	BoardMapper boardMapper;
	
	Board board01;
	Board board02;
	
	@Before
	public void setUp() throws Exception {
		log.debug("┌───────────────────────────");
		log.debug("│ @Before() ");
		log.debug("└───────────────────────────");
		
		boardMapper.deleteAll();
		
		board01 = new Board(999, "제목01", 0, "내용이지1", "20", "admin", "안씀", "admin", "안씀");
		board02 = new Board(1000, "제목02", 0, "내용이지2", "20", "admin1", "안씀", "admin1", "안씀");
		
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌───────────────────────────");
		log.debug("│ @After() ");
		log.debug("└───────────────────────────");
	}
	
	@Test
	public void doSelectOne() throws Exception{
		log.debug("┌───────────────────────────");
		log.debug("│ doSelectOne() ");
		log.debug("└───────────────────────────");
		
		int flag = boardMapper.doSave(board01);
		assertEquals(1, flag);
		
		int seq = boardMapper.getSequence();
		board01.setBoardSeq(seq);
		
		Board outVO1 = boardService.doSelectOne(board01);
		assertEquals(0, outVO1.getReadCnt());
		isSameBoard(board01, outVO1);
		
		board01.setRegId("수정자");
		Board outVO2 = boardService.doSelectOne(board01);
		assertEquals(1, outVO2.getReadCnt());
		
		board01.setReadCnt(board01.getReadCnt()+1);
		isSameBoard(board01, outVO2);
		
		
	}
	
	
	@Ignore
	@Test
	public void beans() {
		log.debug("┌───────────────────────────");
		log.debug("│ beans() ");
		log.debug("└───────────────────────────");
		
		log.debug("context: "+context);
		log.debug("boardService: "+boardService);
		assertNotNull(context);
		assertNotNull(boardService);
	}
	
	public void isSameBoard(Board boardIn, Board boardOut) {
		assertEquals(boardIn.getBoardSeq(), boardOut.getBoardSeq());
		assertEquals(boardIn.getTitle(), boardOut.getTitle());
		assertEquals(boardIn.getReadCnt(), boardOut.getReadCnt());
		assertEquals(boardIn.getContents(), boardOut.getContents());
		assertEquals(boardIn.getDiv(), boardOut.getDiv());
//		assertEquals(boardIn.getRegId(), boardOut.getRegId());
		assertEquals(boardIn.getModId(), boardOut.getModId());
	}

}
