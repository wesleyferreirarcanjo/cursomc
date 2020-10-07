package com.arcanjo.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preechimento obrigatorio")
	@Email(message = "Email invalido")
	private String email;
	
	public EmailDTO() {
	}

	public EmailDTO(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
