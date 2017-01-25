package com.katho.beebee.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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
	private Cidade cidade;
	private Estado estado;

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
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Transient
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
