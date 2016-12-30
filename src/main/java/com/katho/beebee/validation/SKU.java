package com.katho.beebee.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

//Quem pode utilizar essa anotação de validação: Campos, Métodos e outras Anotações
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)//será utilizada em tempo de execução
@Constraint(validatedBy = {})//aqui pode ser passado classes para validação
@Pattern(regexp = "([a-zA-Z]{2}\\d{4})?")//padarão 2 letras e 4 números.
public @interface SKU {

	@OverridesAttribute(constraint = Pattern.class, name = "message")
	//sobrescrevendo a mensagem default da validação Pattern
	String message() default "SKU deve seguir o padrão XX9999";
	
	
	Class<?>[] groups() default {};//aqui pode-se agrupar validações, ainda será feito
	Class<? extends Payload>[] payload() default {};
	//o Payload é porque pode carregar mais informações do validation
	//No payload poderia ser passado o nível do erro (Grave, Médio, Baixo)
}
