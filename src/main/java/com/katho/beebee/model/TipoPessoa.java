package com.katho.beebee.model;

/***
 * 
 * @author Katho
 *
 *	Enum para a tela de cadastro de cliente e/ou onde tenha que usar cpf e cnpj.
 *	Depois de criar o Enum tem que mandar ele pra tela. Olhar na aula 16.4.
 *  É possível acrescentar argumentos como descricao, documento, mascara para chamar depois na tela de cadastro de cliente
 */

public enum TipoPessoa {

	FISICA("Física", "CPF", "000.000.000-00"),
	JURIDICA("Jurídica", "CNPJ", "00.000.000/0000-00");
	
	private String descricao;
	private String documento;
	private String mascara;
	
	TipoPessoa(String descricao, String documento, String mascara) {
		
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
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
	
	
}
