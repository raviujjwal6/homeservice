package com.tyss.homeservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login_Exception extends RuntimeException{
	
	String msg;
	
	@Override
	public String getMessage() {
		return msg;
	}

}
