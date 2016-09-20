package com.MThibProj.exceptions;

import com.MThibProj.entity.Employee;

public class NoSuchEmployeeFoundException extends Exception {

	public NoSuchEmployeeFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoSuchEmployeeFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoSuchEmployeeFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoSuchEmployeeFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoSuchEmployeeFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
