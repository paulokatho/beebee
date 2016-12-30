package com.katho.beebee.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.util.FieldUtils;
import org.thymeleaf.templatemode.TemplateMode;

public class ClassForErrorAttributeTagProcessor extends AbstractAttributeTagProcessor {

	private static final String NOME_ATRIBUTO = "classforerror";
	private static final int PRECEDENCIA = 1000;
	
	public ClassForErrorAttributeTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, NOME_ATRIBUTO, true, PRECEDENCIA, true);
	}
	
	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		
		// attributeValue por causa do field th:field da view
		// contex, pois tem que informar o contexto, que é o que recebe o objeto cerveja, por ex. na view. th:object="${cerveja}"
		boolean temErro = FieldUtils.hasErrors(context, attributeValue);
		
		if (temErro) {
			String classesExistentes = tag.getAttributeValue("class");// passando a class que queremos la da view que seja carregada o que tem dentro dela nessa string "<div class="col-sm-2  form-group".....>"
			structureHandler.setAttribute("class", classesExistentes + " has-error");// aqui seta no atributo class as classes existentes + a classe has-error
			
		}
	}

	
}
