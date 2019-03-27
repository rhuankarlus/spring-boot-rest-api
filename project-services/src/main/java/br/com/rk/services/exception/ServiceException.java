package br.com.rk.services.exception;

/**
 * @author Rhuan Karlus
 * @since 04/03/2019
 */
public class ServicoException extends Exception {

    public ServicoException(String message) {
        super(message);
    }

    public ServicoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServicoException(Throwable cause) {
        super(cause);
    }

}
