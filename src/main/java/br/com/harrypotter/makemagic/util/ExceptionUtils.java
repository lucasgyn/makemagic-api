package br.com.harrypotter.makemagic.util;

import br.com.harrypotter.makemagic.exception.BusinessException;
import br.com.harrypotter.makemagic.exception.CharacterNotFoundException;
import br.com.harrypotter.makemagic.exception.RestClientException;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

@UtilityClass
public class ExceptionUtils {

    public static CharacterNotFoundException characterNotFoundException(final String message) {
        return new CharacterNotFoundException(message);
    }

    public static ResponseStatusException responseStatusException(final HttpStatus status, final String reason) {
        return new ResponseStatusException(status, reason);
    }

    public static BusinessException businessException(final String message) {
        return new BusinessException(message);
    }

    public static RestClientException restClientException(final String message) {
        return new RestClientException(message);
    }
}