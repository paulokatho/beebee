package com.katho.beebee.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.katho.beebee.service.exception.NomeEstiloCadastroException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(NomeEstiloCadastroException.class)
	public ResponseEntity<String> handleNomeEstiloCadastroException(NomeEstiloCadastroException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
