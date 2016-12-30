package com.katho.beebee.service.exception;

public class NomeEstiloCadastroException extends RuntimeException {
	
	//Criando a excessão utilizada no EstiloService para validar se existe o nome ja cadastrado no banco
	private static final long serialVersionUID = 1L;

	public NomeEstiloCadastroException(String message) {
		super(message);
	}
}
