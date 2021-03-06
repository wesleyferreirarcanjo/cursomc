package com.arcanjo.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.arcanjo.cursomc.services.DBService;
import com.arcanjo.cursomc.services.EmailService;
import com.arcanjo.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() {
		try {
			dbService.instantiateTestDatabase();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
