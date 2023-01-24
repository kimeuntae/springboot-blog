package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.service.BoardService;
 
@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	
	/* 글 저장 */
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {
		boardService.boardSave(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변황하는데
	}
	
	/* 글 수정 */
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Board board) {
		boardService.boardUpdate(id , board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변황하는데
	}
	
	/* 글 삭제 */
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> save(@PathVariable int id) {
		boardService.boardDelete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변황하는데
	}
	
	//데이터 받을 때 컨트롤러에서 dto 를 만들어서 받는게 좋다.
	/* 댓글 저장 */
	@PostMapping("/api/board/reply")
	public ResponseDto<Integer> save(@RequestBody ReplySaveRequestDto replyDto,@AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println("replyDto1" + replyDto.getBoardId() );
		System.out.println("replyDto2" + replyDto.getUserId());
		System.out.println("replyDto3" + replyDto.getContent() );
		boardService.replySave(replyDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변황하는데
	}
	
	/* 글 삭제 */
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int boardId,@PathVariable int replyId) {
		boardService.replyDelete(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바오브젝트를 JSON으로 변황하는데
	}
	
}
