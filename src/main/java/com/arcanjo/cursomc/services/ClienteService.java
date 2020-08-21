package com.arcanjo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.arcanjo.cursomc.domain.Cliente;
import com.arcanjo.cursomc.repositories.ClienteRepository;
import com.arcanjo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente busca(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto nao encontrado! Id: %s, Tipo: %s", id, Cliente.class.getName())));
	}
}