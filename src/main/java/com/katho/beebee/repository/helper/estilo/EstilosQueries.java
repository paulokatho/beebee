package com.katho.beebee.repository.helper.estilo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.katho.beebee.model.Estilo;
import com.katho.beebee.repository.filter.EstiloFilter;

public interface EstilosQueries {

	public Page<Estilo> filtrar(EstiloFilter estiloFilter, Pageable pageable);

}
