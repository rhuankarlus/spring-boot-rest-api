package br.com.rk.services.user.impl;

import br.com.rk.entities.user.User;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.repositories.user.specifications.UserPasswordSpecification;
import br.com.rk.repositories.user.specifications.UserUsernameSpecification;
import br.com.rk.services.AbstractCrudService;
import br.com.rk.services.user.UserCrudService;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

/**
 * @author Rhuan Karlus
 * @since 5/27/19
 */
public class UserCrudServiceImpl extends AbstractCrudService<User> implements UserCrudService {

    @Override
    protected Specification<User> buildAllSpecifications(User user) {
        return Specification
                .where(new UserUsernameSpecification(Operation.EQ, user.getUsername()))
                .and(new UserPasswordSpecification(Operation.EQ, encrypt(user.getPassword())));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return Optional.empty();
    }

    private String encrypt(String password) {
        return null;
    }

}
