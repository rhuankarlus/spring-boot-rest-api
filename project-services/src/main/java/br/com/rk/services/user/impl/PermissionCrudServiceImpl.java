package br.com.rk.services.user.impl;

import br.com.rk.entities.user.Permission;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.repositories.user.specifications.PermissionNameSpecification;
import br.com.rk.services.AbstractCrudService;
import br.com.rk.services.user.PermissionCrudService;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Rhuan Karlus
 * @since 5/27/19
 */
public class PermissionCrudServiceImpl extends AbstractCrudService<Permission> implements PermissionCrudService {

    @Override
    protected Specification<Permission> buildAllSpecifications(Permission permission) {
        return Specification.where(new PermissionNameSpecification(Operation.EQ, permission.getName()));
    }

}
