package com.example.spring01.exception;

import java.util.Map;

public class ValidationExceptions extends RuntimeException{
	public ValidationExceptions(Map<String, String> message) {
		super();
	}
}
