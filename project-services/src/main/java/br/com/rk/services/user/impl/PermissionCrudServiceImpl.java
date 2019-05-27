package br.com.rk.services.Permission.impl;

import br.com.rk.entities.user.Permission;
import br.com.rk.services.AbstractCrudService;
import br.com.rk.services.exception.ServiceException;
import br.com.rk.services.user.PermissionCrudService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Rhuan Karlus
 * @since 5/27/19
 */
public class PermissionCrudServiceImpl extends AbstractCrudService<Permission> implements PermissionCrudService {

    @Override
    protected void validateBeforeFindExample(Permission permission, Pageable pageable) throws ServiceException {

    }

    @Override
    protected Specification<Permission> buildAllSpecifications(Permission permission) {
        return null;
    }

}
