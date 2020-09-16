package br.com.harrypotter.makemagic.service;

import br.com.harrypotter.makemagic.entity.Character;
import br.com.harrypotter.makemagic.exception.CharacterNotFoundException;

import java.util.Collection;

/**
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

public interface ICharacterService extends ICrudService<Character> {

    Collection<Character> findByHouse(final String house) throws CharacterNotFoundException;
}