package com.katho.beebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.katho.beebee.model.Estado;

//@Repository
public interface Estados extends JpaRepository<Estado, Long> {

	//public Optional<Estado> findByNomeIgnoreCase(String nome);
}
