package br.com.rk.services.user;

import br.com.rk.entities.user.Role;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.services.ProjectCrudService;

import java.util.Optional;

/**
 * @author Rhuan Karlus
 * @since 5/27/19
 */
public interface RoleCrudService extends ProjectCrudService<Role> {

    /**
     * Search for the Role with the given name
     *
     * @param name The role's name
     * @return An optional containing the role with the given name or empty
     * @see Operation
     */
    Optional<Role> findByName(String name);

    /**
     * Search for the Role containing the given name using the specified operation
     *
     * @param name                The role's name
     * @param comparisonOperation The operation used to compare the name
     * @return An optional containing the role with the given name found using the operation or empty
     * @see Operation
     */
    Optional<Role> findByName(String name, Operation comparisonOperation);

}
