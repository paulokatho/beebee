package com.katho.beebee.repository.paginacao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/***
 * 
 * @author Katho
 *	Obs: Para uma classe do Spring ser um Bean ela tem que estar anotada com @Component e depois esse Bean tem que ser enxergado.
 *		 Então lá no JPAConfig tem que conseguir encontrar esse nosso Bean dentro do pacote Repository. Portanto temos que ter um
 *			@ComponentScan(Cervejas.class) indicando o pacote que deve ser rastreado.
 *		 	O JPAConfig esta somente com a assinatura @EnableJpaRepositories(), portanto só está enxergando os repositórios JPA.
 *			Dessa forma não esquecer de acrescentar o @ComponentScan() para poder varrer e procurar esse nosso Bean no pacote Repository.
 */	
@Component
public class PaginacaoUtil {

	public void preparar(Criteria criteria, Pageable pageable) {
		
	 //Começo do calculo de paginação para o Pageable
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina; //é o primeiro registro de cada aba da paginação
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);			
	//Fim do calculo de paginação
		
	//Adicionando ordenação dos parametros na tela de busca - Sort
		Sort sort = pageable.getSort(); //Sort tem que ser do pacote spring.data.domain
		System.out.println(">>> Sort: " + sort);
		if (sort != null) {
			Sort.Order order = sort.iterator().next();//Order é do pacote do Spring.domain e o iterator pode colocar na url ordernação por vários campos (sku, nome, estilo...)
			String property = order.getProperty();// property é o campo field lá da pagina de pesquisa... Ex: propriedade do campo SKU
			//Abaixo estamos traduzinho o Order do hibernate para o Order do Criteria
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));//Esse Oder é do pacote do hibernate.criterion
		}
	}	
}
