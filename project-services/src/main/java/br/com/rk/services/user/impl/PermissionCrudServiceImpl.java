package br.com.rk.services.user.impl;

import br.com.rk.entities.user.Permission;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.repositories.user.PermissionRepository;
import br.com.rk.repositories.user.specifications.PermissionNameSpecification;
import br.com.rk.services.AbstractCrudService;
import br.com.rk.services.user.PermissionCrudService;
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
public class PermissionCrudServiceImpl extends AbstractCrudService<Permission> implements PermissionCrudService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionCrudServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    protected PermissionRepository getRepository() {
        return permissionRepository;
    }

    @Override
    protected Specification<Permission> buildAllSpecifications(Permission permission) {
        return Specification.where(new PermissionNameSpecification(Operation.EQ, permission.getName()));
    }

    @Override
    public Optional<Permission> findByName(String name) {
        return permissionRepository.findByName(name);
    }

    @Override
    public Optional<List<Permission>> findByName(String name, Operation comparisonOperation) {
        return Optional.ofNullable(permissionRepository.findAll(Specification.where(
                new PermissionNameSpecification(comparisonOperation, name))));
    }

}
