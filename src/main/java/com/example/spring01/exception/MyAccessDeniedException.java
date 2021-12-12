package com.example.spring01.exception;

public class MyAccessDeniedException extends RuntimeException{
	public MyAccessDeniedException(String message) {
		super(message);
	}
}
