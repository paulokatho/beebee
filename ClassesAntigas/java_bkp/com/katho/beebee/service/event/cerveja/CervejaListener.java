package com.katho.beebee.service.event.cerveja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.katho.beebee.storage.FotoStorage;

/***
 * 
 * @author Katho
 * 
 * Será @Component Spring para podermos injetar coisas aqui dentro
 *
 */
@Component
public class CervejaListener {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	//Esse método será chamado quando publisher.publishEvent() do CadastroCervejaService for chamado
	//Ele sabe quando é pra chamar, pois estara com a anotação @EventListener, então toda vez que uma cerveja for salva ele será chamado.
	//só chama nessa condição se tem foto.
	@EventListener(condition = "#evento.temFoto()")//#evento é o objeto e temFoto é o método lá no CervejaSalvaEvento. Dessa forma posso chamar ele nessa Anotação
	public void cervejaSalva(CervejaSalvaEvent evento) {
		
		System.out.println("Nova cerveja salva - " + evento.getCerveja().getNome());
	
		//salvando a foto de fato
		//o evento é uma classe simples que posso carregar outros objetos (classe evento CervejaSalvaEvent)		
		fotoStorage.salvar(evento.getCerveja().getFoto());
	}
}
