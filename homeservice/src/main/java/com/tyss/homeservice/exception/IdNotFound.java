package com.tyss.homeservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdNotFound extends RuntimeException {
	
	String msg="id not present or not found";
	
	@Override
	public String getMessage() {
		return msg;
	}
}
