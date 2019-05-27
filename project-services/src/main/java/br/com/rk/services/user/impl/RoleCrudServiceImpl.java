package br.com.rk.services.user.impl;

import br.com.rk.entities.user.Role;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.repositories.user.specifications.RoleNameSpecification;
import br.com.rk.services.AbstractCrudService;
import br.com.rk.services.user.RoleCrudService;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Rhuan Karlus
 * @since 5/27/19
 */
public class RoleCrudServiceImpl extends AbstractCrudService<Role> implements RoleCrudService {

    @Override
    protected Specification<Role> buildAllSpecifications(Role role) {
        return Specification.where(new RoleNameSpecification(Operation.EQ, role.getName()));
    }

}
