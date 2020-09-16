package br.com.harrypotter.makemagic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {
	
	private static final long serialVersionUID = 6484554698703280922L;

	public NotFoundException(final String message) {
		super(message);
	}
}