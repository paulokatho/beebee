package com.katho.beebee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.katho.beebee.model.Estilo;
import com.katho.beebee.service.CadastroEstiloService;
import com.katho.beebee.service.exception.NomeEstiloCadastroException;



@Controller
@RequestMapping("/estilos") //tiramos o request mapping do ModelAndView e deixamos o que é repetido do mapeamento no controller
public class EstilosController {

	@Autowired
	private CadastroEstiloService cadastroEstiloService;
	
	//@RequestMapping("/estilos/novo")
	@RequestMapping("/novo")
	public ModelAndView novo(Estilo estilo) {
		
		ModelAndView mv = new ModelAndView("estilo/CadastroEstilo");
		
		return mv;
	}
	
	//@RequestMapping(value = "/estilos/novo", method = RequestMethod.POST)
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(estilo);
		}
		try {
			cadastroEstiloService.salvar(estilo);
		} catch (NomeEstiloCadastroException e) {
			//o result vai ver o que tem no campo nome e vai exibir a mensagem de que já existe o campo cadastrado no sistema.
			
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}
		
		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso!");	
		return new ModelAndView("redirect:/estilos/novo");
	}
	
	//Mapeando a url /beebee/estilo capturado com o th:action no modal de cadastro rapido
	//@RequestMapping(value = "/estilos", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	
	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result) {
	//para entender json tem que declarar o requestBody (pega o corpo da requisição e transforma no objeto estilo)

		if(result.hasErrors()) {
			//abaixo trata a mensagem para que apareça o que estiver lá no bean validation do Estilo no atributo "nome"
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		//try { *** TIROU O try, pois criou a ExceptionHandler no ControllerAdvice
		
			estilo = cadastroEstiloService.salvar(estilo);			
		
		//} catch (NomeEstiloCadastroException e) {
			//return ResponseEntity.badRequest().body(e.getMessage());
		//}
		
		
		/* para testes quando estou transformando em json e objetos
		
			System.out.println(">>> estilo: " + estilo.getNome());
			return ResponseEntity.badRequest().body("Erro salvando estilo");
		*/		
		//mais ou menos estamos devolvendo uma resposta para a página, um objeto e vamos testar como uma .badRequest(), com se tivesse dado erro, só para testes
		//vai dar erro de unssuported media type, pois precisamos de uma biblioteca que ajuda a transformar o objeto para que possamos pegar o valor que era json... dependencia jackson.
		return ResponseEntity.ok(estilo);
		
		//aqui estou retornando para o javascript o estilo, então posso pegar ele nos argumentos que são passados na tela
	}
}
