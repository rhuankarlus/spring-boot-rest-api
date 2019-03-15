package br.com.rk.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * API's Global Error Handler
 *
 * @author Rhuan Karlus
 * @since 15/03/19
 */
@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {


}
