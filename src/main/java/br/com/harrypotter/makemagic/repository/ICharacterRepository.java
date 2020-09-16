package br.com.harrypotter.makemagic.repository;

import br.com.harrypotter.makemagic.entity.Character;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

@Repository
@Scope("prototype")
public interface ICharacterRepository extends JpaRepository<Character, Long> {
	
	Optional<Character> findByName(final String name);

	Optional<Collection<Character>> findByHouse(final String house);
}