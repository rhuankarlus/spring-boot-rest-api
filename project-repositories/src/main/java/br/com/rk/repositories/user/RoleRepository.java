package br.com.rk.repositories.user;

import br.com.rk.entities.user.Role;
import br.com.rk.repositories.ProjectRepository;

import java.util.Optional;

/**
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public interface RoleRepository extends ProjectRepository<Role> {

    Optional<Role> findByName(String name);

}
