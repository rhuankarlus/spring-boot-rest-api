package br.com.rk.services.user;

import br.com.rk.entities.user.User;
import br.com.rk.services.ProjectCrudService;

import java.util.Optional;

/**
 * @author Rhuan Karlus
 * @since 5/27/19
 */
public interface UserCrudService extends ProjectCrudService<User> {

    /**
     * Calculates the user entity by it's given username
     *
     * @param username The given username
     * @return An optional containing the user or empty
     */
    Optional<User> findByUsername(String username);

    /**
     * Calculates the user entity by it's given username and password (the password should not be encripted before
     * call this method)
     *
     * @param username The given username
     * @param password The given password
     * @return An optional containing the user or empty
     */
    Optional<User> findByUsernameAndPassword(String username, String password);

}
