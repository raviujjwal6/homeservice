package com.tyss.homeservice.util;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	
	private int statusCode;
	private String msg;
	private T data;
	private LocalDateTime  dateTime = LocalDateTime.now();
	

}
