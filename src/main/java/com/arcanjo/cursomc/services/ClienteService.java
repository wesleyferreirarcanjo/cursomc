package com.arcanjo.cursomc.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcanjo.cursomc.domain.Cidade;
import com.arcanjo.cursomc.domain.Cliente;
import com.arcanjo.cursomc.domain.Endereco;
import com.arcanjo.cursomc.domain.enums.TipoCliente;
import com.arcanjo.cursomc.dto.ClienteDTO;
import com.arcanjo.cursomc.dto.ClienteNewDTO;
import com.arcanjo.cursomc.repositories.CidadeRepository;
import com.arcanjo.cursomc.repositories.ClienteRepository;
import com.arcanjo.cursomc.repositories.EnderecoRepository;
import com.arcanjo.cursomc.services.exceptions.DataIntegrityException;
import com.arcanjo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto nao encontrado! Id: %s, Tipo: %s", id, Cliente.class.getName())));
	}
	

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepo.saveAll(obj.getEndereco());
		return obj;
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
			throw new DataIntegrityException("nao e possivel excluir cliente pois ha pedidos relacionados");
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
		return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		
		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipoCliente()), bCryptPasswordEncoder.encode(objDto.getSenha()));
		
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		cidadeRepository.save(cid);
		
		Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cliente, cid);
		
		cliente.getEndereco().add(endereco);
		cliente.getTelefones().add(objDto.getTelefone());
		
		if(objDto.getTelefone2() != null) {
			cliente.getTelefones().add(objDto.getTelefone2());			
		}
		
		if(objDto.getTelefone3() != null) {
			cliente.getTelefones().add(objDto.getTelefone3());			
		}	
		
		return cliente;
	}

	
	private void updataData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}





}
