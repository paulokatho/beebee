package com.katho.beebee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/estados")
public class EstadosController {
	
	@RequestMapping("novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("/estado/CadastroEstado");
		
		//TODO: Carregar todos os estados.... findAll();
		
		return mv;
	}

}
