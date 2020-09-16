package br.com.harrypotter.makemagic.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public @interface JsonRequestMapping {

	@AliasFor(attribute = "path", annotation = RequestMapping.class)
	String[] path() default {};

	@AliasFor(attribute = "value", annotation = RequestMapping.class)
	String[] value() default {};

	@AliasFor(attribute = "method", annotation = RequestMapping.class)
	RequestMethod[] method() default {};

	@AliasFor(attribute = "params", annotation = RequestMapping.class)
	String[] params() default {};

	@AliasFor(attribute = "headers", annotation = RequestMapping.class)
	String[] headers() default {};
}