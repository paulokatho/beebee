package com.katho.beebee.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {

	private Page<T>  page;
	private UriComponentsBuilder uriBuilder;//

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest ) {
		this.page = page;
		this.uriBuilder = ServletUriComponentsBuilder.fromRequest(httpServletRequest);
	}
	
	/***
	 * 
	 * @return
	 * 	Retorna o conteúdo que será utilizado na página PersquisaCerveja, ao invés de chamar na tela o método implementamos aqui
	 */
	public List<T> getConteudo() {
		return page.getContent();
	}
	
	public boolean isVazia() {
		return page.getContent().isEmpty();
	}
	
	public int getAtual() {
		return page.getNumber();
	}
	
	public boolean isPrimeira() {
		return page.isFirst();
	}
	
	public boolean isUltima() {
		return page.isLast();
	}
	
	public int getTotal() {
		return page.getTotalPages();
	}
	
	/***
	 * 
	 * @param pagina
	 * @return
	 * 	Esse retorna o valor para montar a url quando for feita uma pesquisa, para manter o valor na url quando for mudar o número da paginação e levar o filtro da pesquisa para não apagar os valores e tambem mantendo os valores unitarios corretos caso exista no filtro
	 */
	public String urlParaPagina(int pagina) {
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}
}
