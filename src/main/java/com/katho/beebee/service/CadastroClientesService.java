package com.katho.beebee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.katho.beebee.model.Cliente;
import com.katho.beebee.repository.Clientes;

@Service
public class CadastroClientesService {

	@Autowired
	private Clientes clientes;
	
	@Transactional
	public void salvar(Cliente cliente) {
		clientes.save(cliente)	;
	}
}
