package com.katho.beebee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.katho.beebee.model.Estilo;
import com.katho.beebee.repository.Estilos;
import com.katho.beebee.service.exception.NomeEstiloCadastroException;

@Service
public class CadastroEstiloService {
	
	@Autowired
	private Estilos estilos;
	
	@Transactional
	public Estilo salvar(Estilo estilo) {
		//Verificando se existe um nome já persistido
		//Pensar que se caso for editar essa validação não pode pegar.
		Optional<Estilo> estiloOptional = estilos.findByNomeIgnoreCase(estilo.getNome());
		
		if (estiloOptional.isPresent()) {
			throw new NomeEstiloCadastroException("Nome do estilo já cadastrado");
		}
		
		return estilos.saveAndFlush(estilo);
		//save and flush salva no banco e retorna com o código que foi atribuído para ele no momento de salvar
		//usamos ele para depois que fechar o modal do estilo rápido consigamos pegar o código que foi atribuido para o novo estilo cadastrado.
	}
	
}