package br.com.harrypotter.makemagic.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.harrypotter.makemagic.util.ExceptionUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public void handleRuntimeException(final RuntimeException e) {
		throw ExceptionUtils.responseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}
}