package br.com.rk.repositories.user.specifications;

import br.com.rk.entities.user.Permission;
import br.com.rk.repositories.specifications.string.Operation;

/**
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public class PermissionNameSpecification extends NameSpecification<Permission> {

    public PermissionNameSpecification(Operation operation, String value) {
        super(operation, value);
    }

}
