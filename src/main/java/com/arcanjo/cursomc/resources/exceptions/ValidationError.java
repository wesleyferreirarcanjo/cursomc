package com.arcanjo.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> list = new ArrayList<FieldMessage>();

	public ValidationError(Integer status, String msg, long timestap) {
		super(status, msg, timestap);
	}

	public List<FieldMessage> getErrors() {
		return list;
	}

	public void addError(String fieldName, String message) {
		this.list.add(new FieldMessage(fieldName, message));
	}
	

}
