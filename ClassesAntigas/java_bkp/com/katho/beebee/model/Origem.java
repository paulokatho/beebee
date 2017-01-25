package com.katho.beebee.model;

public enum Origem {
	
	/*
	 	Quando se se da um nome para o Enum ("Nacional") é necessário declarar esse atributo
	 	no contrutor do Enumerador.
	 */
	
	NACIONAL("Nacional"),
	INTERNACIONAL("Internacional");
	
	private String descricao;
	
	Origem(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
