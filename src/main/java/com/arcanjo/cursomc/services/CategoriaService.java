package com.arcanjo.cursomc.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.arcanjo.cursomc.domain.Categoria;
import com.arcanjo.cursomc.dto.CategoriaDTO;
import com.arcanjo.cursomc.repositories.CategoriaRepository;
import com.arcanjo.cursomc.services.exceptions.DataIntegrityException;
import com.arcanjo.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id)  {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto nao encontrado! Id: %s, Tipo: %s", id, Categoria.class.getName())));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public void update(Categoria obj) {
		this.find(obj.getId());
		repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao e possivel excluir uma categoria com produtos");
		}
		
		
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
}
