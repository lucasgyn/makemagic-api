package br.com.harrypotter.makemagic.exception;


import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

@ResponseStatus
public class RestClientException extends RuntimeException {

    public RestClientException(final String message) {
        super(message);
    }
}