package com.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
		
		String message=ex.getMessage();
//		ApiResponse build=ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND);
		ApiResponse response=new ApiResponse();
		response.setMessage(message);
		response.setSuccess(true);
        response.setStatus(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
	}

}
