package com.acorn.anpa.board.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import com.acorn.anpa.board.domain.Board;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.mapper.BoardMapper;


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
	Search search;

	@Before
	public void setUp() throws Exception {
		log.debug("┌───────────────────────────");
		log.debug("│ @Before() ");
		log.debug("└───────────────────────────");	
		
		boards = Arrays.asList(
				new Board(999, "제목01", 0, "내용이지1", "20", "admin", "안씀", "admin", "안씀"),
				new Board(1000, "제목02", 0, "내용이지2", "20", "admin", "안씀", "admin", "안씀"),
				new Board(1001, "제목03", 0, "내용이지3", "20", "admin", "안씀", "admin", "안씀"),
				new Board(1002, "제목04", 0, "내용이지4", "20", "admin", "안씀", "admin", "안씀")
		);
		
		boardMapper.deleteAll();
		search = new Search();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌───────────────────────────");
		log.debug("│ @After() ");
		log.debug("└───────────────────────────");	
	}
	
	@Test
	public void readCntUpdate() throws SQLException{
		log.debug("┌───────────────────────────");
		log.debug("│ readCntUpdate() ");
		log.debug("└───────────────────────────");
		
		Board boardInVO1 =  boards.get(0);
		Board boardInVO2 =  boards.get(1);
		
		int flag = boardMapper.doSave(boardInVO1);
		assertEquals(1, flag);
		
		int seq = boardMapper.getSequence();
		boardInVO1.setBoardSeq(seq);
		
		boardMapper.readCntUpdate(boardInVO1);
		assertEquals(0, boardInVO1.getReadCnt());
		
		Board outVO1 = boardMapper.doSelectOne(boardInVO1);
		
		isSameBoard(boardInVO1, outVO1);
		
		boardInVO1.setRegId(boardInVO1.getRegId()+"_U");
		
		boardMapper.readCntUpdate(boardInVO1);
		Board outVO2 = boardMapper.doSelectOne(boardInVO1);
		
		boardInVO1.setReadCnt(boardInVO1.getReadCnt()+1);
		assertEquals(1, boardInVO1.getReadCnt());
		
		isSameBoard(boardInVO1, outVO2);
		
	}
	
	@Ignore
	@Test
	public void doRetrieve() throws SQLException{
		log.debug("┌───────────────────────────");
		log.debug("│ doRetrieve() ");
		log.debug("└───────────────────────────");
		
		int flag = boardMapper.multipleSave();
		assertEquals(101, flag);
		
		search.setPageNo(1);
		search.setPageSize(10);
		
		// 상무
		search.setSearchDiv("30");
		search.setSearchWord("내용000002");
		
		List<Board> list = boardMapper.doRetrieve(search);
		assertEquals(10, list.size());
	}
	
	@Ignore
	@Test
	public void addAndGet() throws SQLException {
		log.debug("┌───────────────────────────");
		log.debug("│ addAndGet() ");
		log.debug("└───────────────────────────");	
		
		Board boardInVO =  boards.get(0);
		
		int flag = boardMapper.doSave(boardInVO);
		assertEquals(1, flag);
		
		int seq = boardMapper.getSequence();
		boardInVO.setBoardSeq(seq);
		
		Board boardOutVO = boardMapper.doSelectOne(boardInVO);
		isSameBoard(boardInVO, boardOutVO);
		
		flag = boardMapper.doDelete(boardOutVO);
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
