package br.com.harrypotter.makemagic.service.impl;

import br.com.harrypotter.makemagic.entity.Character;
import br.com.harrypotter.makemagic.exception.BusinessException;
import br.com.harrypotter.makemagic.exception.CharacterNotFoundException;
import br.com.harrypotter.makemagic.repository.ICharacterRepository;
import br.com.harrypotter.makemagic.service.ICharacterService;
import br.com.harrypotter.makemagic.to.CharacterHouseTO;
import br.com.harrypotter.makemagic.util.ExceptionUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

@Service
@Scope(value = "prototype")
public class CharacterServiceImpl implements ICharacterService {

    private static String potterApiKey;
    private static String potterApiHousesUrl;

    private final ICharacterRepository characterRepository;
    private final Environment appProperties;
    private final RestTemplate restTemplate;

    @Autowired
    public CharacterServiceImpl(final ICharacterRepository characterRepository, final Environment appProperties, final RestTemplate restTemplate) {
        this.characterRepository = characterRepository;
        this.appProperties = appProperties;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    private void postConstruct() {
        potterApiKey = this.appProperties.getProperty("makemagic.potter-api-key");
        potterApiHousesUrl = this.appProperties.getProperty("makemagic.potter-api.houses.url");
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, timeout = 150, readOnly = true)
    public Collection<Character> findAll() throws CharacterNotFoundException {
        final Collection<Character> characters = this.characterRepository.findAll();
        if (CollectionUtils.isEmpty(characters)) {
            throw ExceptionUtils.characterNotFoundException("There are no registered characters");
        }
        // else
        return characters;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, timeout = 150, readOnly = true)
    public Character findById(final Long characterId) throws CharacterNotFoundException {
        return this.characterRepository.findById(characterId)
                .orElseThrow(() -> ExceptionUtils.characterNotFoundException(String.format("Character not found with id %s", characterId)));
    }

    @Override
    public Character save(final Character character) throws BusinessException {
        if (this.characterRepository.findByName(character.getName()).isPresent()) {
            throw ExceptionUtils.businessException("There is already a character with that name");
        }
        // else
        this.validateHouse(character.getHouse());
        return this.characterRepository.save(character);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, timeout = 150, rollbackFor = Exception.class)
    public void deleteById(final Long characterId) throws CharacterNotFoundException {
        final Character character = this.findById(characterId);
        this.characterRepository.delete(character);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, timeout = 150, rollbackFor = Exception.class)
    public Character update(final Character character) throws BusinessException {
        this.validateHouse(character.getHouse());
        return this.characterRepository.save(character);
    }

    private void validateHouse(final String houseId) throws BusinessException {
        final HttpHeaders httpHeaders = new HttpHeaders() {
            private static final long serialVersionUID = -8815888696043278574L;

            {
                this.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                this.setContentType(MediaType.APPLICATION_JSON);
                this.add("User-Agent", StringUtils.EMPTY);
            }
        };
        this.restTemplate.setMessageConverters(Arrays.asList(new StringHttpMessageConverter(), new MappingJackson2HttpMessageConverter()));
        final String houseUrl = MessageFormat.format(potterApiHousesUrl, houseId, potterApiKey);
        final ResponseEntity<String> response = this.restTemplate.exchange(houseUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
        if (HttpStatus.OK != response.getStatusCode()) {
            throw ExceptionUtils.restClientException(response.getBody());
        }
        try {
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.fromJson(response.getBody(), CharacterHouseTO[].class);
        } catch (RuntimeException e) {
            throw ExceptionUtils.businessException("The informed house is invalid");
        }
    }

    @Override
    public Collection<Character> findByHouse(final String house) throws CharacterNotFoundException {
        return this.characterRepository.findByHouse(house)
                .orElseThrow(() -> ExceptionUtils.characterNotFoundException(String.format("Character(s) not found with the house %s", house)));
    }
}