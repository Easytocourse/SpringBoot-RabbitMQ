package com.easytocourse.exception;

public class Employeenotfound extends RuntimeException{

	private static final long serialVersionUID = 48308689895916626L;

	public Employeenotfound(String message)
	{
		super(message);
	}
}
