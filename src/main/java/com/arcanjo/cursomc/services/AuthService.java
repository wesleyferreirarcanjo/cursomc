package com.arcanjo.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arcanjo.cursomc.domain.Cliente;
import com.arcanjo.cursomc.repositories.ClienteRepository;
import com.arcanjo.cursomc.security.UserSS;
import com.arcanjo.cursomc.services.exceptions.AuthorizationException;
import com.arcanjo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCPE;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {

		Cliente cliente = clienteRepository.findByEmail(email);

		if (cliente == null) {
			throw new ObjectNotFoundException("Email nao encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(bCPE.encode(newPass));

		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}
	
	public void changePassword(String email, String password, String newPassword) {
		
		Cliente cliente = clienteRepository.findByEmail(email);
		
		UserSS user = UserService.authenticated();
		
		if(user == null || !cliente.getId().equals(user.getId()) ) {
			throw new AuthorizationException("Acesso negado");
		}
		
		if( !bCPE.matches(password, cliente.getSenha())) {
			throw new AuthorizationException("Senha nao confere");
		}
		
		
		cliente.setSenha(bCPE.encode(newPassword));
		clienteRepository.save(cliente);
		
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);

		switch (opt) {
		case 1:
			return (char) (rand.nextInt(10) + 48);
		case 2:
			return (char) (rand.nextInt(26) + 65);
		default:
			return (char) (rand.nextInt(26) + 97);
		}

	}



}
