package com.katho.beebee.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

/***
 * 
 * @author Katho
 *	Foi pego do MessageElementTagProcessor a partir do "extends" ate o fim da classe e foram alteradas algumas coisinhas.
 */

public class OrderElementTagProcessor extends AbstractElementTagProcessor {

	private static final String NOME_TAG = "order"; //é o nome da tag la em pesquisaCerveja.html
	private static final int PRECEDENCIA = 1000;
	
	public OrderElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, NOME_TAG, true, null, false, PRECEDENCIA);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
		IElementTagStructureHandler estructureHandler) {
		
		IModelFactory modelFactory = context.getModelFactory();
		
		//abaixo é como pegar o valor como string da página de pesquisaCerveja.html, como o page, field e text. Dessa tag  <brewer:order page="${pagina}" field="sku" text="SKU"/>
		IAttribute page = tag.getAttribute("page");
		IAttribute field = tag.getAttribute("field");
		IAttribute text = tag.getAttribute("text");
		
		//fim 
		
		IModel model = modelFactory.createModel();
		model.add(modelFactory.createStandaloneElementTag("th:block"
				, "th:replace"
				, String.format("fragments/Ordenacao :: order (%s, %s, %s)", page.getValue(), field.getValue(), text.getValue()))); //Substituindo os atributos page, field e text pelos definidos no IAttribute acima.
		
		estructureHandler.replaceWith(model, true);
		
		//Depois de terminar aqui tem que lembrar de acrescentar no BeeBeeDialect.
		
	}
}
