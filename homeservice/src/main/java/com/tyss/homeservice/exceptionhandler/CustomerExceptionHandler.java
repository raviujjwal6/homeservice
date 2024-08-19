package com.tyss.homeservice.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tyss.homeservice.exception.IdNotFound;
import com.tyss.homeservice.exception.Login_Exception;
import com.tyss.homeservice.util.ResponseStructure;

@RestControllerAdvice
public class CustomerExceptionHandler {
	
	@ExceptionHandler(IdNotFound.class)
	public ResponseEntity<ResponseStructure<String>> idNotFound(IdNotFound idNotFound) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setData(idNotFound.getMessage());
		responseStructure.setMsg("id not found");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
	
	}
	@ExceptionHandler(Login_Exception.class)
	public ResponseEntity<ResponseStructure<String>> loginException(Login_Exception exception) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setData(exception.getMessage());
		responseStructure.setMsg("wrong details");
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
	}

}
