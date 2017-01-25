package com.katho.beebee.repository.filter;

import java.math.BigDecimal;

import com.katho.beebee.model.Estilo;
import com.katho.beebee.model.Origem;
import com.katho.beebee.model.Sabor;

/***
 * 
 * @author Katho
 *
 *	Essa classe é responsáver por receber o valor dos filtros da página de pesquisaCerveja.html
 *	depois esses parametros serão enviados para o repositorio e então o utilizar o Criteria do Hibernate 
 *  para fazer a pesquisa.
 *  
 *  Depois de criar os atributos aqui é necessário fazer o binding com os inputTex da tela de pesquisa e também
 *  fazer a referencia ao objeto declarando no form com o th:object
 */

public class CervejaFilter {

	private String sku;
	private String nome;
	private Estilo estilo;
	private Sabor sabor;
	private Origem origem;
	private BigDecimal valorDe;
	private BigDecimal valorAte;
	
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Estilo getEstilo() {
		return estilo;
	}
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	public Sabor getSabor() {
		return sabor;
	}
	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}
	public Origem getOrigem() {
		return origem;
	}
	public void setOrigem(Origem origem) {
		this.origem = origem;
	}
	public BigDecimal getValorDe() {
		return valorDe;
	}
	public void setValorDe(BigDecimal valorDe) {
		this.valorDe = valorDe;
	}
	public BigDecimal getValorAte() {
		return valorAte;
	}
	public void setValorAte(BigDecimal valorAte) {
		this.valorAte = valorAte;
	}
	
	
}
