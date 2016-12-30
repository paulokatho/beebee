package com.katho.beebee.repository.helper.cerveja;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.katho.beebee.model.Cerveja;
import com.katho.beebee.repository.filter.CervejaFilter;

/***
 * 
 * @author Katho
 *
 *	Criar uma implementação dessa interface nesse mesmo pacote chamado CervejasImpl -- SEMPER MTO ATENTO AO NOME "Imp" no final
 */

public interface CervejasQueries {

	//public List<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable); --alterado para retornar uma Page
	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
}
