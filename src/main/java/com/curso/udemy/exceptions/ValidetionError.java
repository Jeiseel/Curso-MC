package com.curso.udemy.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidetionError extends StanderdError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidetionError(Integer status, String msg, Long timeStamp) {
		super(status,msg,timeStamp);
	}

	public List<FieldMessage> getErros() {
		return errors;
	}

	public void addError(String  fieldName, String message){
		errors.add(new FieldMessage(fieldName, message));
	}
	
	
	
}
