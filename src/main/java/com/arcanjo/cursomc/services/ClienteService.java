package com.arcanjo.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.arcanjo.cursomc.domain.Cidade;
import com.arcanjo.cursomc.domain.Cliente;
import com.arcanjo.cursomc.domain.Endereco;
import com.arcanjo.cursomc.domain.enums.Perfil;
import com.arcanjo.cursomc.domain.enums.TipoCliente;
import com.arcanjo.cursomc.dto.ClienteDTO;
import com.arcanjo.cursomc.dto.ClienteNewDTO;
import com.arcanjo.cursomc.repositories.CidadeRepository;
import com.arcanjo.cursomc.repositories.ClienteRepository;
import com.arcanjo.cursomc.repositories.EnderecoRepository;
import com.arcanjo.cursomc.security.UserSS;
import com.arcanjo.cursomc.services.exceptions.AuthorizationException;
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

	@Autowired
	private ImageService imageService;

	@Autowired
	private S3Service s3Service;

	@Value("${img.prefix.client.profile}")
	private String imgPrefix;

	@Value("${img.profile.size}")
	private Integer imgSize;

	public Cliente find(Integer id) {

		UserSS user = UserService.authenticated();

		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Objeto nao encontrado! Id: %s, Tipo: %s", id, Cliente.class.getName())));
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

	public void delete(Integer id) {
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

	public Cliente findByEmail(String email) {

		UserSS user = UserService.authenticated();

		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Cliente> obj = repo.findById(user.getId());

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Objeto nao encontrado! Id: %s, Tipo: %s", user.getId(), Cliente.class.getName())));

	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDto(ClienteDTO obj) {
		return new Cliente(obj.getId(), obj.getNome(), obj.getEmail(), null, null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {

		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipoCliente()), bCryptPasswordEncoder.encode(objDto.getSenha()));

		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		cidadeRepository.save(cid);

		Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cliente, cid);

		cliente.getEndereco().add(endereco);
		cliente.getTelefones().add(objDto.getTelefone());

		if (objDto.getTelefone2() != null) {
			cliente.getTelefones().add(objDto.getTelefone2());
		}

		if (objDto.getTelefone3() != null) {
			cliente.getTelefones().add(objDto.getTelefone3());
		}

		return cliente;
	}

	private void updataData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFIle(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, imgSize);

		String filename = imgPrefix + user.getId() + ".jpg";
		URI uri = s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), filename, "image");

		return uri;
	}

}
