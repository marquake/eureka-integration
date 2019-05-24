package com.remoteservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_DEFAULT = "Not found request resource";
	
	public NotFoundException(){
		super(MESSAGE_DEFAULT);
	}

	public NotFoundException(String mesg){
		super(mesg);
	}
}