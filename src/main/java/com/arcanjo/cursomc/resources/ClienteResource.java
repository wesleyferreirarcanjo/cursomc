package com.arcanjo.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arcanjo.cursomc.domain.Cliente;
import com.arcanjo.cursomc.dto.ClienteDTO;
import com.arcanjo.cursomc.dto.ClienteNewDTO;
import com.arcanjo.cursomc.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {

		Cliente obj = clienteService.find(id);
		return ResponseEntity.ok().body(obj);

	}
	
	
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
		Cliente obj = clienteService.fromDTO(objDto);
		obj = clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO){
		System.out.println(id);
		clienteDTO.setId(id);
		Cliente cliente = clienteService.fromDto(clienteDTO);
		clienteService.update(cliente);
		
		return ResponseEntity.noContent().build();
		
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {

		Page<Cliente> pageCliente = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> pageClienteDTO = pageCliente.map(obj -> new ClienteDTO(obj));

		return ResponseEntity.ok().body(pageClienteDTO);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<Cliente> list = clienteService.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}
