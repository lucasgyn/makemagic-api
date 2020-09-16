package br.com.harrypotter.makemagic.controller;

import br.com.harrypotter.makemagic.annotation.JsonRequestMapping;
import br.com.harrypotter.makemagic.entity.Character;
import br.com.harrypotter.makemagic.exception.BusinessException;
import br.com.harrypotter.makemagic.exception.NotFoundException;
import br.com.harrypotter.makemagic.service.ICharacterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

@JsonRequestMapping(path = "/v1/public/characters")
public class CharacterController {

    private final ICharacterService characterService;

    @Autowired
    public CharacterController(final ICharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    @ApiOperation(value = "Returns a collection of characters", tags = "Collection Characters")
    public ResponseEntity<? super Character> find(@RequestParam(name = "house", required = false) Optional<String> house) throws NotFoundException {
        final Collection<Character> characters = house.isPresent()
                ? this.characterService.findByHouse(house.get())
                : this.characterService.findAll();
        return ResponseEntity.ok(characters);
    }

    @GetMapping("/{characterId}")
    @ApiOperation(value = "Returns a single character", tags = "Single Character")
    public ResponseEntity<Character> findById(@PathVariable(name = "characterId") final Long characterId) throws NotFoundException {
        return ResponseEntity.ok(this.characterService.findById(characterId));
    }

    @PostMapping
    @ApiOperation(value = "Create a new character", tags = "Create Character")
    public ResponseEntity<?> create(@Valid @RequestBody final Character character) throws BusinessException {
        final Character savedCharacter = this.characterService.save(character);
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCharacter.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{characterId}")
    @ApiOperation(value = "Delete a single character", tags = "Delete Character")
    public ResponseEntity<?> delete(@PathVariable(name = "characterId") final Long characterId) throws NotFoundException {
        this.characterService.deleteById(characterId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{characterId}")
    @ApiOperation(value = "Update a single character", tags = "Update Character")
    public ResponseEntity<Character> update(final @PathVariable(name = "characterId") Long characterId, final @Valid @RequestBody Character characterBody) throws NotFoundException, BusinessException {
        Character character = this.characterService.findById(characterId);
        character.setRole(characterBody.getRole());
        character.setSchool(characterBody.getSchool());
        character.setHouse(characterBody.getHouse());
        character.setPatronus(characterBody.getPatronus());
        character = this.characterService.update(character);
        return ResponseEntity.ok(character);
    }
}