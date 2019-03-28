package br.com.rk.converters;

import br.com.rk.services.exception.ServiceException;

/**
 * Exception used for converters.
 *
 * @author Rhuan Karlus
 * @since 28/03/19
 */
public class ConverterException extends ServiceException {

    public ConverterException(String message) {
        super(message);
    }

    public ConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
