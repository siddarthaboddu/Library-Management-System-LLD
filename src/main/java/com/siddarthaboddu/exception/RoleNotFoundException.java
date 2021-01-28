package com.siddarthaboddu.exception;

public class RoleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4195911400097532457L;
	
	public RoleNotFoundException(String msg){
		super(msg);
	}
}
