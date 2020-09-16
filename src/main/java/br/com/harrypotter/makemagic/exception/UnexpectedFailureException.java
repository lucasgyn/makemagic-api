package br.com.harrypotter.makemagic.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

@ResponseStatus(reason = "Unexpected failure")
public class UnexpectedFailureException extends RuntimeException {}