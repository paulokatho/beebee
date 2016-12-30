package com.katho.beebee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.katho.beebee.model.Cliente;

@Controller
public class ClientesController {

	@RequestMapping("/clientes/novo")
	public String novo(Cliente cliente) {
		
		return "cliente/CadastroCliente";
	}
	
	@RequestMapping(value = "/clientes/novo", method = RequestMethod.POST)
	public String cadastro() {
		
		return "cliente/CadastroCliente";
	}
}
