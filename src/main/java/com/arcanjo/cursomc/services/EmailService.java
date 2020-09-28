package com.arcanjo.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.arcanjo.cursomc.domain.Pedido;

public interface EmailService {
	
	public void sendOrderConfirmationEmail(Pedido obj);
	
	public void sendEmail(SimpleMailMessage msg);
}
