package com.arcanjo.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arcanjo.cursomc.domain.Produto;
import com.arcanjo.cursomc.dto.ProdutoDTO;
import com.arcanjo.cursomc.resources.utils.URL;
import com.arcanjo.cursomc.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue = "") String nome,
			@RequestParam(value="categorias", defaultValue = "") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> listaCategoriasIds = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded, listaCategoriasIds, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		
		return ResponseEntity.ok().body(listDto);	
	}
	
}
