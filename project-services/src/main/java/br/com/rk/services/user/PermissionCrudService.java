package br.com.rk.services.user;

import br.com.rk.entities.user.Permission;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.services.ProjectCrudService;

import java.util.Optional;

/**
 * @author Rhuan Karlus
 * @since 5/27/19
 */
public interface PermissionCrudService extends ProjectCrudService<Permission> {

    /**
     * Search for the permission with the given name
     *
     * @param name The permission's name
     * @return An optional containing the permission with the given name or empty
     * @see Operation
     */
    Optional<Permission> findByName(String name);

    /**
     * Search for the permission containing the given name using the specified operation
     *
     * @param name                The permission's name
     * @param comparisonOperation The operation used to compare the name
     * @return An optional containing the permission with the given name found using the operation or empty
     * @see Operation
     */
    Optional<Permission> findByName(String name, Operation comparisonOperation);

}
