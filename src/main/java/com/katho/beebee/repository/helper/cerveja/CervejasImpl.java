package com.katho.beebee.repository.helper.cerveja;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.katho.beebee.model.Cerveja;
import com.katho.beebee.repository.filter.CervejaFilter;

/***
 * 
 * @author Katho
 *
 *	Impelementando a interface CervejasQueries.
 *
 *	Utilizando o metodo filtrar() já corrige o erro n+1 que faz vários acessos ao banco para trazer, por exemplo o "estilo", pois com a busca do .findAll() do jpaConfig ele faz uma nova pesquisa para cada linha que tem em estilo do banco.
 *	Nesse caso ele faz um innerJoin de cerveja com estilo, chamado fatch de cerveja com estilo.
 *	
 */
public class CervejasImpl implements CervejasQueries{

	//Abaixo é para injetar o Entity manager, para conseguir pegar a Session e utilizar as classes que são dessa sessão com manager.unwrap(...)
	// Não utiliza o @Autowired aqui para injetar o manager usar o @PersistenceContext
	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)//esse, para poder na pag de pesquisaCerveja obter a Transação de leitura, pois a sessão foi pega e como não foi uma nova Transacao de gravação"comit", podemos usar a que já existe"ReadOnly"
	//public List<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable) { --não vai mais retornar uma List<> vai retornar uma Page
	public Page<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable) {
		/***
		 *	Implementando o Criteria
		 *	O manager tira a sessão com unwrap() e cria o criteria createCriteria com a Cerveja.class.
		 *	Assim o "criteria" está criado.
		 */
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		
		//Começo do calculo de paginação para o Pageable
			int paginaAtual = pageable.getPageNumber();
			int totalRegistrosPorPagina = pageable.getPageSize();
			int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
			
			criteria.setFirstResult(primeiroRegistro);
			criteria.setMaxResults(totalRegistrosPorPagina);
			
		//Fim do calculo de paginação
				
		adicionarFiltro(filtro, criteria);
		
		/***
		 * 	O Page recebe o tipo de retorno  trazendo a lista, mas também o pageable e um total que se refere a quantidade de registros que tem no banco para fazermos dinamicamente a quantidade de números na paginação dos registro no html pesquisaCerveja.
		 */
		//return criteria.list(); --agora vai retornar outro tipo para o Page		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(CervejaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		adicionarFiltro(filtro, criteria);//para funcionar o filtro e aparece o número correto de paginas dinamicamente na pesquisa
		criteria.setProjection(Projections.rowCount());//rowCount vai retornar a quantidade de registros que tem o Cerveja.class
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(CervejaFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if(!StringUtils.isEmpty(filtro.getSku())) {
				criteria.add(Restrictions.eq("sku", filtro.getSku()));
			}
			
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));//qq letra que digitar la na pag de pesquisa ele traz os resultados referentes
			}
			
			if(isEstiloPresente(filtro)) {
				criteria.add(Restrictions.eq("estilo", filtro.getEstilo()));
			}
			
			if(filtro.getSabor() != null) {
				criteria.add(Restrictions.eq("sabor", filtro.getSabor()));
			}
			
			if(filtro.getOrigem() != null) {
				criteria.add(Restrictions.eq("origem", filtro.getOrigem()));
			}
			
			if(filtro.getValorDe() != null) {
				criteria.add(Restrictions.ge("valor", filtro.getValorDe()));// ge "greater than or equal"
			}
			
			if(filtro.getValorAte() != null) {
				criteria.add(Restrictions.le("valor", filtro.getValorAte()));// le  "less than or equal"
			}
		}
	}
	
	private boolean isEstiloPresente(CervejaFilter filtro) {
		return filtro.getEstilo() != null && filtro.getEstilo().getCodigo() != null;
	}
	
}
