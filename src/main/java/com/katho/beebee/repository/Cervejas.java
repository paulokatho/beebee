package com.katho.beebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.katho.beebee.model.Cerveja;
import com.katho.beebee.repository.helper.cerveja.CervejasQueries;

/***
 * 
 * @author Katho
 * 
 *  { Conceitos java de interface:
 * 	Uma classe pode implementar várias interfaces 
 * 	Uma interface pode extender várias interfaces }
 *
 *	Essa interface cerveja vai implementar também os métodos de CervejasQueries, isso também significa que em CervejasController na pesquisa hora que 
 *	injetar essa interface Cervejas ele também vai poder ter acesso aos métodos de CervejasQueries, além das implemenatações que já tem no
 *	JpaRepository que tem varias queries prontas.
 *
 *	E a implementação das Queries estarão em CervejasImpl
 */
@Repository
public interface Cervejas extends JpaRepository<Cerveja, Long>, CervejasQueries {
	//Cerveja é o Repositorio do tipo cerveja e Long é o tipo do 
	//@id da classe cerveja

	
}
