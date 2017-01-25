package com.katho.beebee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.katho.beebee.controller.page.PageWrapper;
import com.katho.beebee.model.Cerveja;
import com.katho.beebee.model.Origem;
import com.katho.beebee.model.Sabor;
import com.katho.beebee.repository.Cervejas;
import com.katho.beebee.repository.Estilos;
import com.katho.beebee.repository.filter.CervejaFilter;
import com.katho.beebee.service.CadastroCervejaService;

@Controller
@RequestMapping("/cervejas")
public class CervejasController {
			
	@Autowired
	private Estilos estilos;//injetei o Estilo, pois não é enum! para poder rodar o combo e buscar do banco as informações.
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;
	
	@Autowired
	private Cervejas cervejas;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(Cerveja cerveja) {
		
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());//findaAll(), pois esta retornando uma lista de uma pesquisa no banco na tabela estilo
		mv.addObject("origens", Origem.values());//value(), pois é um array que está retornando
		
		return mv;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
			
		if (result.hasErrors()) {
			return novo(cerveja);
		}
				
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");			
		return new ModelAndView("redirect:/cervejas/novo");
	}
	
	//CervejaFilter foi add para poder funcionar na página de pesquisasCervejas o Spring é esperto e sabe q qdo submeter o formulário vai pegar essas informações
	//Pegeable é do pacote data.domain e serve para paginação da pag de pesquisa de cerveja. Para fazermos a paginação vamos integrar SpringData e SpringMVC. Recebe objetos do tipo page e ordenação	
	@GetMapping
	public ModelAndView pesquisar(CervejaFilter cervejaFilter, BindingResult bindingResult, 
			@PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		
		ModelAndView mv = new ModelAndView("cerveja/PesquisaCervejas");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("sabores", Sabor.values());
		mv.addObject("origens", Origem.values());
		
		//mv.addObject("cervejas", cervejas.filtrar(cervejaFilter, pageable)); --metodo alterado para retornar uma Page
		//mv.addObject("cervejas", cervejas.findAll(pegeable)); -- metodo alterado para retornar cervejasFiltrar() descrito acima
		
		PageWrapper<Cerveja> paginaWrapper = new PageWrapper<>(cervejas.filtrar(cervejaFilter, pageable),
				httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
}