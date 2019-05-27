package br.com.rk.services.user.impl;

import br.com.rk.entities.user.User;
import br.com.rk.services.AbstractCrudService;
import br.com.rk.services.user.UserCrudService;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Rhuan Karlus
 * @since 5/27/19
 */
public class UserCrudServiceImpl extends AbstractCrudService<User> implements UserCrudService {

    @Override
    protected Specification<User> buildAllSpecifications(User user) {
        return null;
    }

}
