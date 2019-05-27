package br.com.rk.repositories.user;

import br.com.rk.entities.user.Permission;
import br.com.rk.repositories.ReadOnlyRepository;

import java.util.Optional;

/**
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public interface PermissionRepository extends ReadOnlyRepository<Permission> {

    Optional<Permission> findByName(String name);

}
