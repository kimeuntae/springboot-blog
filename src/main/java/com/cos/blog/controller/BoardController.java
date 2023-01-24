package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	private PrincipalDetail principal;

	//@AuthenticationPrincipal PrincipalDetail principal
	/* 메인 페이지 호출*/
	@GetMapping({"","/"})
	public String index(Model model,@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards",boardService.boardList(pageable));
		return "index"; //viewResolver 작동!!
	}
	
	/* 글 상세보기 */
	@GetMapping("/board/{id}")
	public String joinForm(Model model, @PathVariable int id) {
		model.addAttribute("board",boardService.boardDetail(id));
		return "board/detailForm";
	}
	
	/* 글 저장 */
	@GetMapping("/board/saveForm")
	public String joinForm() {
		return "board/saveForm";
	}
	
	/* 글 수정 */
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(Model model, @PathVariable int id) {
		model.addAttribute("board",boardService.boardDetail(id));
		return "board/updateForm";
	}	
}
