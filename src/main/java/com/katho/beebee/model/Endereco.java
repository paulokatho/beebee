package com.katho.beebee.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/***
 * 
 * @author Katho
 *
 *  Significa que essa classe pode ser injetada, utilizada em outra classe.
 *         
 *	Essa classe não precisa de HashCode e Equals
 *         
 */
@Embeddable
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String cep;
	private String cidade;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@ManyToOne
	@JoinColumn(name = "codigo_cidade")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
