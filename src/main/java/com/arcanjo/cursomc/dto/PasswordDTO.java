package com.arcanjo.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PasswordDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preechimento obrigatorio")
	@Email(message = "Email invalido")
	private String email;

	@NotEmpty(message = "Preechimento obrigatorio")
	private String password;

	@NotEmpty(message = "Preechimento obrigatorio")
	private String newPassword;

	public PasswordDTO(String email, String password, String newPassword) {
		this.email = email;
		this.password = password;
		this.newPassword = newPassword;
	}

	public PasswordDTO() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
