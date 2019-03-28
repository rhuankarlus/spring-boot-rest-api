package br.com.rk.exceptions;

/**
 * Exception for constant fields not found on enums
 *
 * @author Rhuan Karlus
 * @since 28/03/19
 */
public class EnumNotFoundException extends Exception {

    public EnumNotFoundException(String message) {
        super(message);
    }

}
