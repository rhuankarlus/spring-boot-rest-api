package br.com.rk.services.user.impl;

import br.com.rk.entities.user.User;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.repositories.user.UserRepository;
import br.com.rk.repositories.user.specifications.UserPasswordSpecification;
import br.com.rk.repositories.user.specifications.UserUsernameSpecification;
import br.com.rk.services.AbstractCrudService;
import br.com.rk.services.user.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Rhuan Karlus
 * @since 5/27/19
 */
@Service
public class UserCrudServiceImpl extends AbstractCrudService<User> implements UserCrudService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserCrudServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }

    @Override
    protected Specification<User> buildAllSpecifications(User user) {
        return Specification
                .where(new UserUsernameSpecification(Operation.EQ, user.getUsername()))
                .and(new UserPasswordSpecification(Operation.EQ, passwordEncoder.encode(user.getPassword())));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return getRepository().findByUsername(username);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return getRepository().findByUsernameAndPassword(username, passwordEncoder.encode(password));
    }

}
