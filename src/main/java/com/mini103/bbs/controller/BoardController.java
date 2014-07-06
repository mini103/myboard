package com.mini103.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mini103.bbs.dao.BoardDao;
import com.mini103.bbs.model.Board;

@Controller
public class BoardController {
	@Autowired
	private BoardDao boardDao;
	private static final Logger logger = LoggerFactory
			.getLogger(BoardController.class);

	@ModelAttribute("board")
	public Board getBoardObject() {
		return new Board();
	}

	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public ModelAndView getList() {
		List<Board> list = boardDao.getList();

		ModelAndView view = new ModelAndView();
		view.setViewName("board/list");
		view.addObject("list", list);

		return view;
	}

	@RequestMapping(value = "/board/add", method = RequestMethod.GET)
	public String addItemGet() {
		return "board/add";
	}

	@RequestMapping(value = "/board/add", method = RequestMethod.POST)
	public String addItemPost(Board board) {
		boardDao.addItem(board);

		return "redirect:list";
	}

	@RequestMapping(value = "/board/detail/{id}", method = RequestMethod.GET)
	public ModelAndView getDetail(@PathVariable("id") int id) {
		boardDao.increaseCount(id);

		ModelAndView view = new ModelAndView();
		view.setViewName("board/detail");
		view.addObject("board", boardDao.getItem(id));

		return view;
	}

	@RequestMapping(value = "/board/detailajax/{id}", method = RequestMethod.GET)
	public String getDetailAjax(@PathVariable("id") int id) {
		return "Board/detailajax";
	}

	@RequestMapping(value = "/board/detail/ajax/{id}", method = RequestMethod.GET)
	public void getDetailAjaxCall(@PathVariable("id") int id,
			HttpServletResponse response) {
		boardDao.increaseCount(id);
		Board board = boardDao.getItem(id);

		JSONArray json = JSONArray.fromObject(board);

		PrintWriter writer;

		try {
			writer = response.getWriter();
			writer.print(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/board/update/{id}", method = RequestMethod.GET)
	public ModelAndView updateItemGet(@PathVariable("id") int id) {
		ModelAndView view = new ModelAndView();
		view.setViewName("board/update");
		view.addObject("board", boardDao.getItem(id));

		return view;
	}

	@RequestMapping(value = "/board/update/{id}", method = RequestMethod.POST)
	public String updateItemPost(@PathVariable("id") int id, Board board) {
		boardDao.updateItem(id, board.getSubject(), board.getDetail());

		return "redirect:../list";
	}

	@RequestMapping(value = "/board/delete/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable("id") int id) {
		boardDao.deleteItem(id);

		return "redirect:../list";
	}
}
