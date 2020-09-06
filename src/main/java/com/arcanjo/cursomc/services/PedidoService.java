package com.arcanjo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcanjo.cursomc.domain.Pedido;
import com.arcanjo.cursomc.repositories.PedidoRepository;
import com.arcanjo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	
	public Pedido buscar(Integer id)  {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto nao encontrado! Id: %s, Tipo: %s", id, Pedido.class.getName())));
	}
}
