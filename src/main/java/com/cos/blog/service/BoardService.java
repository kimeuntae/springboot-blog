package com.cos.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //@Autowired 대신 사용가능 
public class BoardService {
	
	//@Autowired
	private final BoardRepository boardRepository;
	
	//@Autowired
	private final ReplyRepository replyRepository;	
	
	/* 글저장 */
	@Transactional
	public void boardSave(Board board,User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	/* 글수정 */
	@Transactional
	public void boardUpdate(int id , Board requestBoard) {
		
		Board board = boardRepository.findById(id)
		.orElseThrow(()->{
				return new IllegalArgumentException("글 상세보기 실패");
		});
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		//트랜잭션 끝나면 영속성때문에 저장됌
	}	
	/* 글조회 */
	@Transactional(readOnly = true)
	public Page<Board> boardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	/* 글상세조회 */
	@Transactional(readOnly = true)
	public Board boardDetail(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
						return new IllegalArgumentException("글 상세보기 실패");
				});
	}
	
	/* 글 삭제 */
	@Transactional
	public void boardDelete(int id) {
		boardRepository.deleteById(id);
	}
	
	/* 댓글 저장 */
	@Transactional
	public void replySave(ReplySaveRequestDto replyDto) {
		/*Board board = boardRepository.findById(replyDto.getBoardId()).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패");
		});
		
		User user = userRepository.findById(replyDto.getUserId()).orElseThrow(()->{
			return new IllegalArgumentException("유저 찾기 실패");
		});
		
		Reply reply = Reply.builder()
				.user(user)
				.board(board)
				.content(replyDto.getContent()).build();*/
		
		
		replyRepository.mSave(replyDto.getUserId(),replyDto.getBoardId(),replyDto.getContent());
	}

	/* 댓글 삭제 */
	@Transactional
	public void replyDelete(int replyId) {
		replyRepository.deleteById(replyId);
	}
}
