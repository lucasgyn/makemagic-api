package br.com.harrypotter.makemagic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends Exception {

	public BusinessException(final String message) {
		super(message);
	}
}