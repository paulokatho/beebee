package com.katho.beebee.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.katho.beebee.model.Estilo;



public class EstiloConverter implements Converter<String, Estilo> {

	@Override
	public Estilo convert(String codigo) {
		//essa validação if() não precisa com essa versão do Spring, pois ele já reconhece o valor null
		//quando não digita nada no campo estilo da tela de cadastro de cerveja, mas esta como aprendizado
		if(!StringUtils.isEmpty(codigo)) {
			Estilo estilo = new Estilo();
			estilo.setCodigo(Long.valueOf(codigo));
			return estilo;
		}
	return null;
	}
	
}
