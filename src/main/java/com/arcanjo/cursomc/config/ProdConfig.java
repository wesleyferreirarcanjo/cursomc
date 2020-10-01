package com.arcanjo.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.arcanjo.cursomc.services.DBService;
import com.arcanjo.cursomc.services.EmailService;
import com.arcanjo.cursomc.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return true;
		}

		dbService.instantiateTestDatabase();

		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
