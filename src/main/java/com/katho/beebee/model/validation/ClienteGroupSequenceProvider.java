package com.katho.beebee.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.katho.beebee.model.Cliente;


/***
 * 
 * @author Katho
 *
 *	Classe para validação de cpf e cnpj no momento que passam pelo bean validation, para poder ficar vermelho na tela e saber qual é o cpf e qual é o cnpj
 *	Aula 16.9 - Agrupando validações para CPF ou CNPJ 28:00 minutos
 *
 *	OBS: O groupSequenceProvider só valida quando o grupo anterior estiver validado, Ex: quando o nome estiver validado corretamente ele passa para o 
 *			proximo grupo e se for o sequenceProvider aí ele vai validar!
 */
public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Cliente>{

	@Override
	public List<Class<?>> getValidationGroups(Cliente cliente) {
		List<Class<?>> grupos = new ArrayList<>();
		grupos.add(Cliente.class);
		
		if(isPessoaSelecionada(cliente)) {
			grupos.add(cliente.getTipoPessoa().getGroupo());
		}
		
		return grupos;
	}

	private boolean isPessoaSelecionada(Cliente cliente) {
		return cliente != null &&  cliente.getTipoPessoa() != null;
	}
}
