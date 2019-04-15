package br.com.rk.exception;

import br.com.rk.services.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * API's Global Error Handler
 *
 * @author Rhuan Karlus
 * @since 15/03/19
 */
@RestControllerAdvice
public class GlobalErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorHandler.class);

    @ExceptionHandler({ServiceException.class})
    public void handleServiceException() {

    }

}
