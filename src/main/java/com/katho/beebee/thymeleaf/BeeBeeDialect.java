package com.katho.beebee.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.katho.beebee.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.katho.beebee.thymeleaf.processor.MessageElementTagProcessor;

public class BeeBeeDialect extends AbstractProcessorDialect {

	public BeeBeeDialect() {
		super("Katho BeeBee", "beebee", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		return processadores;
	}

}
