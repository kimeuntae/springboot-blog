package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	private PrincipalDetail principal;
	
	//@AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"","/"})
	public String index(Model model) {
		model.addAttribute("boards",boardService.boardList());
		return "index"; //viewResolver 작동!!
	}
	
	@GetMapping("/board/saveForm")
	public String joinForm() {
		return "board/saveForm";
	}	
}
