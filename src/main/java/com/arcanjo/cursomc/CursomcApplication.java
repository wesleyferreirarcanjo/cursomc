package com.arcanjo.cursomc;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arcanjo.cursomc.domain.Categoria;
import com.arcanjo.cursomc.domain.Cidade;
import com.arcanjo.cursomc.domain.Estado;
import com.arcanjo.cursomc.domain.Produto;
import com.arcanjo.cursomc.repositories.CategoriaRepository;
import com.arcanjo.cursomc.repositories.CidadeRepository;
import com.arcanjo.cursomc.repositories.EstadoRepository;
import com.arcanjo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado estado = new Estado(null, "Mina Gerais");
		Estado estado2 = new Estado(null, "Sao Paulo");
		
		Cidade cidade = new Cidade(null, "campinas", estado2);
		Cidade cidade2 = new Cidade(null, "sao paulo", estado2);
		Cidade cidade3 = new Cidade(null, "uberlandia", estado);
		
		estado.getCidades().addAll(Arrays.asList(cidade3));
		estado2.getCidades().addAll(Arrays.asList(cidade, cidade2));
		
		estadoRepository.saveAll(Arrays.asList(estado, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade,cidade2,cidade3));
		
	}

}
