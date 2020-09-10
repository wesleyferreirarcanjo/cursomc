package com.arcanjo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import com.arcanjo.cursomc.domain.Cliente;
import com.arcanjo.cursomc.dto.ClienteDTO;
import com.arcanjo.cursomc.repositories.ClienteRepository;
import com.arcanjo.cursomc.services.exceptions.DataIntegrityException;
import com.arcanjo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto nao encontrado! Id: %s, Tipo: %s", id, Cliente.class.getName())));
	}
	
	
	public void update(Cliente obj) {
		Cliente newObj = this.find(obj.getId());
		this.updataData(newObj, obj);
		repo.save(newObj);
		
	}


	public void delete (Integer id) {
		this.find(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("nao e possivel excluir cliente pois ha entidades relacionadas");
		}
			
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO obj) {
		return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null);
	}
	
	private void updataData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
}
