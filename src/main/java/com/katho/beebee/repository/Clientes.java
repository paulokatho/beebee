package com.katho.beebee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.katho.beebee.model.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>{
	
	/***
	 * 
	 * @param cpfOuCnpj
	 * 
	 * @return Clientes que já tenham sido cadastrados com o mesmo cpf ou cnpj
	 * 
	 * 	Optional é porque pode voltar um resultado ou não (true or false). 
	 *  Para não precisar ficar validando com objeto.getQqCoisa() == null, por exemplo. Assim é mais orientado a objetos.
	 *  Só funciona a partir do java 8.
	 */

	public Optional<Cliente> fingByCpfOuCnpj(String cpfOuCnpj);

	
}
