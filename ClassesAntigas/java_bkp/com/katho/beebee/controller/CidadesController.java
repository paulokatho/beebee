package com.katho.beebee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.katho.beebee.model.Cidade;
import com.katho.beebee.repository.Cidades;

@Controller
@RequestMapping("cidades")
public class CidadesController {

	@Autowired
	private Cidades cidades;
	
	@RequestMapping("/nova")
	public String novo() {
		return "cidade/CadastroCidade";
	}
	
	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public String cadastro() {
		
		return "cidade/CadastroCidade";
	}
	
	/***
	 * 
	 * @param codigoEstado
	 * @return lista todas as cidades pertencentes ao codigo do estado
	 * 
	 * 	Nesse métod quando recebe um Get da url e o contenttype é o APPLICATION_JSON_VALUE ele cai aqui nesse método
	 */
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisaPorCodigoEstado(
			@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) {//RequestParam é para retornar o valor do List com o código do estado e se não for selecionado nada retorna vazio []
		
			try {
				Thread.sleep(500);//fica aparecendo um pouco mais a imagem de carregando quando seleciona o estado na tela de cliente.
			} catch (InterruptedException e) { }
		
		return cidades.findByEstadoCodigo(codigoEstado);
	}

}
