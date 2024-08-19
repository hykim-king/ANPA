package com.acorn.anpa.answer.dao;

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

import com.acorn.anpa.answer.domain.Answer;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.mapper.AnswerMapper;


@RunWith(SpringRunner.class) // 스프링 컨텍스트 프레임워크의 JUnit확장기능 지정
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
public class AnswerMapperTest implements PLog{
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	AnswerMapper answerMapper; 

	List<Answer> answers;
	Search search;

	@Before
	public void setUp() throws Exception {
		log.debug("┌───────────────────────────");
		log.debug("│ @Before() ");
		log.debug("└───────────────────────────");	
		
		answers = Arrays.asList(
				new Answer(1, 121, "댓글001"),
				new Answer(1, 121, "댓글002"),
				new Answer(1, 121, "댓글003"),
				new Answer(1, 121, "댓글004"),
				new Answer(1, 121, "댓글005")
		);		
		search = new Search();
	}

	@After
	public void tearDown() throws Exception {
		log.debug("┌───────────────────────────");
		log.debug("│ @After() ");
		log.debug("└───────────────────────────");	
	}

	@Test
	public void doSave() throws SQLException{
		log.debug("┌───────────────────────────");
		log.debug("│ doSaveAnswer() ");
		log.debug("└───────────────────────────");	

		search.setPageNo(1);
		search.setPageSize(10);
		
		Answer inVO = answers.get(0);
		inVO.setRegId("admin");
		answerMapper.doSave(inVO);
		List<Answer> list = answerMapper.doRetrieve(search);
		assertEquals(3, list.size());
		int seq = answerMapper.getSequence();
		inVO.setAnswerSeq(seq);
		int flag = answerMapper.doDelete(inVO);
		list = answerMapper.doRetrieve(search);
		assertEquals(2, list.size());
	}

	@Ignore
	@Test
	public void doRetrieve() throws SQLException{
		log.debug("┌───────────────────────────");
		log.debug("│ doRetrieveAnswer() ");
		log.debug("└───────────────────────────");		
		
		Answer inVO = answers.get(0);
		
		search.setPageNo(1);
		search.setPageSize(10);
		
		log.debug("┌───────────────────────────");
		log.debug(inVO.getBoardSeq());
		log.debug("└───────────────────────────");	
		log.debug("┌───────────────────────────");
		log.debug(search.getSearchWord());
		log.debug("└───────────────────────────");	
		
		List<Answer> list = answerMapper.doRetrieve(search);
		assertEquals(2, list.size());
	}
	
	@Ignore
	@Test
	public void beans() {
		log.debug("┌───────────────────────────");
		log.debug("│ beans() ");
		log.debug("└───────────────────────────");	
		
		log.debug("context: "+context);
		log.debug("answerMapper: "+answerMapper);
		assertNotNull(context);
		assertNotNull(answerMapper);
	}
	
	public void isSameBoard(Answer inVO, Answer outVo) {
		assertEquals(inVO.getBoardSeq(), outVo.getBoardSeq());
		assertEquals(inVO.getContents(), outVo.getContents());
		assertEquals(inVO.getModId(), outVo.getModId());
		assertEquals(inVO.getRegId(), outVo.getRegId());
	}

}
