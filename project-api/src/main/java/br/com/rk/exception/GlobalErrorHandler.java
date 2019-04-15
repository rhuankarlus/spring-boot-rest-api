package br.com.rk.exception;

import br.com.rk.controller.dto.ProjectResponse;
import br.com.rk.services.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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
    public ResponseEntity<ProjectResponse> handleServiceException(final ServiceException ex, final WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ProjectResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

}
