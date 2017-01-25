package com.katho.beebee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.katho.beebee.model.Cliente;
import com.katho.beebee.model.TipoPessoa;
import com.katho.beebee.repository.Estados;
import com.katho.beebee.service.CadastroClienteService;
import com.katho.beebee.service.exception.CpfCnpjClienteJaCadastradoException;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estados.findAll());
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
				return novo(cliente);
		}
		
		/***
		 * Nesse momento vamos tentar salvar, mas se houver um cpf/cnpj já cadastrado vamos rejeitar a requisição e retornar uma mensagem
		 */
		try {
			cadastroClienteService.salvar(cliente);
		}catch (CpfCnpjClienteJaCadastradoException e){
			result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage()); //O reject pode ser usado quando necessita de uma regra mais especifica que o bean validation não pega ou não valida.
			return novo(cliente); //tem que rejeitar e retornar para a tela principal para não cair na instrução abaixo.
		}
		
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		return new ModelAndView("redirect:/clientes/novo");
	}
}
