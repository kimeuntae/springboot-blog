package com.cos.blog.handler;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	/*해당 에러가 나오면 해당 함수 실행*/
	@ExceptionHandler(value = IllegalArgumentException.class)
	public String handlerArgumentException(IllegalArgumentException e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
	
	/*해당 에러가 나오면 해당 함수 실행*/
	@ExceptionHandler(value = EmptyResultDataAccessException.class)
	public String handlerArgumentException2(EmptyResultDataAccessException e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
	
}
