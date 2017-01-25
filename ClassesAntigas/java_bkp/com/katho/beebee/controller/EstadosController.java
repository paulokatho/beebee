package com.katho.beebee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estados")
public class EstadosController {
	
	/*
	@Autowired
	private Estados estados;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Estado estado) {
		ModelAndView mv = new ModelAndView("estado/CadastroCliente");
		
		//TODO: Carregar todos os estados.... findAll();
		mv.addObject("estados", estados.findAll());
		
		return mv;
	}
	*/

}
