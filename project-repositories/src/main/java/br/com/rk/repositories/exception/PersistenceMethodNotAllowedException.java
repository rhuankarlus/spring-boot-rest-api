package br.com.rk.repositories.exception;

/**
 * When a not allowed method is called into the persistence context of this architecture
 *
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public class PersistenceMethodNotAllowedException extends RuntimeException {

    public PersistenceMethodNotAllowedException(String message) {
        super(message);
    }

    public PersistenceMethodNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceMethodNotAllowedException(Throwable cause) {
        super(cause);
    }
}
