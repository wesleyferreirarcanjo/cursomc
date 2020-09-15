package com.arcanjo.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.arcanjo.cursomc.domain.Cliente;
import com.arcanjo.cursomc.domain.enums.TipoCliente;
import com.arcanjo.cursomc.dto.ClienteNewDTO;
import com.arcanjo.cursomc.repositories.ClienteRepository;
import com.arcanjo.cursomc.resources.exceptions.FieldMessage;
import com.arcanjo.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		boolean continueValidation = true;
		
		if(objDto.getTipoCliente() == null) {
			list.add(new FieldMessage("tipoCliente", "Cliente esta nulo"));
			continueValidation = false;
			
		}
		
		if (continueValidation && ( objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj()) ) ) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if ( continueValidation && (objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) ) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email ja existente"));
		}

		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
	
	

}