package com.katho.beebee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.katho.beebee.model.Cliente;
import com.katho.beebee.repository.Clientes;
import com.katho.beebee.service.exception.CpfCnpjClienteJaCadastradoException;

@Service
public class CadastroClientesService {

	@Autowired
	private Clientes clientes;
	
	@Transactional
	public void salvar(Cliente cliente) {
		Optional<Clientes> clienteExistente = clientes.fingByCpfOuCnpj(cliente.getCpfOuCnpj());
		if (clienteExistente.isPresent()) {
			throw new CpfCnpjClienteJaCadastradoException("CPF/CNPJ já cadastrado");
		}
		
		clientes.save(cliente)	;
	}

}
