package com.arcanjo.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arcanjo.cursomc.domain.Cidade;




@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
	
	  @Transactional(readOnly = true)
	  public List<Cidade> findCidadesByEstadoId(Integer id);
}
