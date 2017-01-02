package com.katho.beebee.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
	
	public String urlOrdenada(String propriedade) {
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder
				.fromUriString(uriBuilder.build(true).encode().toUriString());
		
		String valorSort = String .format("%s,%s", propriedade, inverterDirecao(propriedade));
		
		return uriBuilderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();
	}

	private String inverterDirecao(String propriedade) {
		
		String direcao = "asc";
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		if (order != null) {
			direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		
		return direcao;
	}
	
	public boolean descendente(String propriedade) {
		return inverterDirecao(propriedade).equals("asc");
	}
	
	/***
	 * 
	 * @param propriedade
	 * @return se a página está ordenada ou não
	 * 
	 * 	Na pagina de pesquisa se não tem nenhuma ordenação ou é a primeira vez que a pesquisa é realizada
	 * 		não tem nenhuma setinha, pra cima ou pra baixo, mas caso for clicado na ordenação por sku ou nome,
	 * 		a ordenação é realizada.
	 */
	public boolean ordenada(String propriedade) {
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null; //Order também é para descobrir se a página está ordenada.
		
		if (order == null) { //se não tem ordem nenhuma ele já sai do método
			return false;
		}
		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}
	
}
