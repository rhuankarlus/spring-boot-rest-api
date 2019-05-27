package br.com.rk.repositories.user;

import br.com.rk.entities.user.User;
import br.com.rk.repositories.ProjectRepository;

import java.util.Optional;

/**
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public interface UserRepository extends ProjectRepository<User> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

}
