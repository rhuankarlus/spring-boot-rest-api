package br.com.rk.services.user.impl;

import br.com.rk.entities.user.Role;
import br.com.rk.repositories.ProjectRepository;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.repositories.user.RoleRepository;
import br.com.rk.repositories.user.specifications.RoleNameSpecification;
import br.com.rk.services.AbstractCrudService;
import br.com.rk.services.user.RoleCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Rhuan Karlus
 * @since 5/27/19
 */
@Service
public class RoleCrudServiceImpl extends AbstractCrudService<Role> implements RoleCrudService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleCrudServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    protected RoleRepository getRepository() {
        return roleRepository;
    }

    @Override
    protected Specification<Role> buildAllSpecifications(Role role) {
        return Specification.where(new RoleNameSpecification(Operation.EQ, role.getName()));
    }

    @Override
    public Optional<Role> findByName(String name) {
        return getRepository().findByName(name);
    }

    @Override
    public Optional<List<Role>> findByName(String name, Operation comparisonOperation) {
        return Optional.ofNullable(getRepository().findAll(Specification.where(
                new RoleNameSpecification(comparisonOperation, name))));
    }

}
