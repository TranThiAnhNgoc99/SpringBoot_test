package com.example.spring01.exception;

public class InternalServerException extends RuntimeException {
	public InternalServerException(String message) {
		super(message);
	}
}
