package com.arcanjo.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import com.arcanjo.cursomc.domain.Cliente;


public class MockEmailService extends AbstractEmailService{

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("simulando envio de email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
		
		
	}
	
	@Override
	public void sendHtmlEmail (MimeMessage msg) {
		LOG.info("simulando envio de email html...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
	}

	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		LOG.info("simulando envio de email html...");
		LOG.info(newPass.toString());
		LOG.info("Email enviado");
		
	}

}
