package br.com.rk.services.user.impl;

import br.com.rk.entities.user.Role;
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
        return null;
    }

}
