package com.katho.beebee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.katho.beebee.model.Estilo;
import com.katho.beebee.repository.helper.estilo.EstilosQueries;

@Repository
public interface Estilos extends JpaRepository<Estilo, Long>, EstilosQueries{

	/*
	 	VAlidando se existe um estilo persistido
	 	
	 	Para isso podemos usar o criador de consulta do Spring data JPA
	 	Optional, pois pode ser que exista ou não  ******NÃO SERÁ USADO NO MOMENTO, POIS VAMOS USAR A BUSCA POR FILTRO******
	*/
	
	public Optional<Estilo> findByNomeIgnoreCase(String nome);
	 	
}
