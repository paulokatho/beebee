package com.katho.beebee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.katho.beebee.model.Cerveja;
import com.katho.beebee.repository.Cervejas;
import com.katho.beebee.service.event.cerveja.CervejaSalvaEvent;

//Anotação que informa que é uma classe de serviço, que existem regras de negocio
@Service
public class CadastroCervejaService {

	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional //qdo salvar for chamado inicia a transação que já esta configurada no jpaConfig
	public void salvar(Cerveja cerveja) {
		cervejas.save(cerveja);
		
		//Evento para salvar o tumbnail da Cerveja***Tira do diretorio temporario e salva no banco como tumbnail
		publisher.publishEvent(new CervejaSalvaEvent(cerveja));
		
		//Entao criamos o evento que após cumprir as regras e salvar no banco ele pode ser utilizado em um Listener que ficara escutando
		//e caso seja necessário enviar e-mail, redimensionar imagem...é só utilizar o evento
		//Criar a classe CervejaListener
	}
}
