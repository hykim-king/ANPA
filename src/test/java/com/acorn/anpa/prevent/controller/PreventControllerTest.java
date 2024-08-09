package com.acorn.anpa.prevent.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.acorn.anpa.cmn.PLog;

public class PreventControllerTest implements PLog{

	@Before
	public void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ setUp");
		log.debug("└─────────────────────────────────────────────────────────");
		
	}
	

	@After
	public void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────");
		log.debug("│ tearDown");
		log.debug("└─────────────────────────────────────────────────────────");
	
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

