package com.katho.beebee.service.event.cerveja;

import org.springframework.util.StringUtils;

import com.katho.beebee.model.Cerveja;

/***
 * 
 * @author Katho
 * 
 * Aqui vamos criar o envento para salvar o tumbnail da imagem no banco
 * Essa classe será chamada no CadastroCervejaService
 *
 */

public class CervejaSalvaEvent {
	
	private Cerveja cerveja;

	public CervejaSalvaEvent(Cerveja cerveja) {
		this.cerveja = cerveja;
	}

	public Cerveja getCerveja() {
		return cerveja;
	}
	
	//verificando se na hora de salvar se no objeto carregou alguma imagem
	public boolean temFoto() {
		return !StringUtils.isEmpty(cerveja.getFoto());
	}

}
