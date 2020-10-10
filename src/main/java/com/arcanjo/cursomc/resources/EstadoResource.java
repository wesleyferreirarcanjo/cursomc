package com.arcanjo.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arcanjo.cursomc.domain.Cidade;
import com.arcanjo.cursomc.domain.Estado;
import com.arcanjo.cursomc.dto.CidadeDTO;
import com.arcanjo.cursomc.dto.EstadoDTO;
import com.arcanjo.cursomc.services.CidadeService;
import com.arcanjo.cursomc.services.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = service.findAll();
		List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping("/{estado_id}/cidades")
	public ResponseEntity<List<CidadeDTO>> findAllCidades(@PathVariable Integer estado_id){
		List<Cidade> list = cidadeService.findByEstado(estado_id);
		List<CidadeDTO> listDTO = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
