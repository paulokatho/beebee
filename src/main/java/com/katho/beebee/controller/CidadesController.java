package com.katho.beebee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CidadesController {

	@RequestMapping("/cidades/novo")
	public String novo() {
		return "cidade/CadastroCidade";
	}
	
	@RequestMapping(value = "/cidades/novo", method = RequestMethod.POST)
	public String cadastro() {
		
		return "cidade/CadastroCidade";
	}
}
