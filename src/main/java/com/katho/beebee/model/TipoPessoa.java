package com.katho.beebee.model;

import com.katho.beebee.model.validation.CnpjGroup;
import com.katho.beebee.model.validation.CpfGroup;

/***
 * 
 * @author Katho
 *
 *	Enum para a tela de cadastro de cliente e/ou onde tenha que usar cpf e cnpj.
 *	Depois de criar o Enum tem que mandar ele pra tela. Olhar na aula 16.4.
 *  É possível acrescentar argumentos como descricao, documento, mascara para chamar depois na tela de cadastro de cliente
 */

public enum TipoPessoa {

	FISICA("Física", "CPF", "000.000.000-00", CpfGroup.class),
	JURIDICA("Jurídica", "CNPJ", "00.000.000/0000-00", CnpjGroup.class);
	
	private String descricao;
	private String documento;
	private String mascara;
	private Class<?> groupo;
	
	TipoPessoa(String descricao, String documento, String mascara, Class<?> grupo) {
		
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.groupo = grupo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}
	
	public Class<?> getGroupo() {
		return groupo;
	}
	
}
