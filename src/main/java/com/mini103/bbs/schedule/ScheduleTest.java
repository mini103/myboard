package com.mini103.bbs.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mini103.bbs.controller.IndexController;
import com.mini103.bbs.dao.BoardDao;

public class ScheduleTest {
	private BoardDao boardDao;

	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	private static final Logger logger = LoggerFactory
			.getLogger(IndexController.class);

	public void start() {
		logger.debug("start method begin!!");
		logger.debug("start method End!!");
	}
}
