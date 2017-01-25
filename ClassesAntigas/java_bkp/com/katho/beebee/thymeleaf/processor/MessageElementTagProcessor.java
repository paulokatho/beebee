package com.katho.beebee.thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class MessageElementTagProcessor extends AbstractElementTagProcessor {

	private static final String NOME_TAG = "message";
	private static final int PRECEDENCIA = 1000;
	
	public MessageElementTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, NOME_TAG, true, null, false, PRECEDENCIA); //uma tag será substituida, por isso não precisa passar true na frente da PRECEDENCIA
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
		IElementTagStructureHandler estructureHandler) {
		
		//para implementar o codigo da view no proprio java, para substitiu o elemento da view pelo que tem aqui.
		// precisaremos de uma interface/objeto como abaixo
		IModelFactory modelFactory = context.getModelFactory();
		IModel model = modelFactory.createModel();
		
		//agora o model adiciona os elementos que eu quero que sejam criados no HTML
		model.add(modelFactory.createStandaloneElementTag("th:block", "th:replace", "fragments/MensagemSucesso :: messageSucces")); //("nomeDaTag", "atributo", "valorDoAtributo");
		model.add(modelFactory.createStandaloneElementTag("th:block", "th:replace", "fragments/MensagensErroValidacao :: messageError"));
		
		estructureHandler.replaceWith(model, true);
		//precisamos que a estrutura seja substituida pelo "model" que criamos e passamos "true", pois o Thymeleaf ainda tem que processar, se fosse direto HTML o valor seria "false".
		
		/**** DEPOIS DE PRONTO AQUI TEMOS QUE ADICIONAR NO BeeBeeDialect e apagar os elementos <th:block> das mensagens na view "CadastroCerveja.html "****/
	}

}
