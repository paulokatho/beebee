package com.katho.beebee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.katho.beebee.model.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>{

	
}
