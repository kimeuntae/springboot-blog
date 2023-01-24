package com.cos.blog.handler;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	/*해당 에러가 나오면 해당 함수 실행*/
	@ExceptionHandler(value = Exception.class)
	public ResponseDto<String> handlerArgumentException(Exception e) {
		return  new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
	
	/*해당 에러가 나오면 해당 함수 실행*/
	@ExceptionHandler(value = EmptyResultDataAccessException.class)
	public String handlerArgumentException2(EmptyResultDataAccessException e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
	
}
