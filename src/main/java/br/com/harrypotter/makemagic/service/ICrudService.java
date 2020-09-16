package br.com.harrypotter.makemagic.service;

import br.com.harrypotter.makemagic.exception.BusinessException;
import br.com.harrypotter.makemagic.exception.NotFoundException;

import java.util.Collection;

/**
 * @author Lucas de Oliveira
 * @since 15/09/2020
 */
public interface ICrudService<T> {

	Collection<T> findAll() throws NotFoundException;

	T findById(final Long characterId) throws NotFoundException;

	T save(final T character) throws BusinessException;

	void deleteById(final Long characterId) throws NotFoundException;

	T update(final T character) throws BusinessException;
}