package br.com.rk.repositories.user.specifications;

import br.com.rk.entities.user.Role;
import br.com.rk.repositories.specifications.string.Operation;

/**
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public class RoleNameSpecification extends NameSpecification<Role> {

    public RoleNameSpecification(Operation operation, String role) {
        super(operation, role);
    }

}
