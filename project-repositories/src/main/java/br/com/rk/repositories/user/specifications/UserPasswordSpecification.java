package br.com.rk.repositories.user.specifications;

import br.com.rk.entities.user.User;
import br.com.rk.repositories.specifications.string.AbstractStringFieldSpecification;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.repositories.specifications.string.StringSearchCriteria;

/**
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public class UserPasswordSpecification extends AbstractStringFieldSpecification<User> {

    private final StringSearchCriteria passwordSearchCriteria;

    /**
     * Searchs for a user with the password passed, using {@link Operation#EQ}
     *
     * @param password The password to be search
     */
    public UserPasswordSpecification(final String password) {
        this.passwordSearchCriteria = new StringSearchCriteria("password", Operation.EQ, password);
    }

    /**
     * @param operation The required operation
     * @param password  The value to search for
     */
    public UserPasswordSpecification(final Operation operation, final String password) {
        this.passwordSearchCriteria = new StringSearchCriteria("password", operation, password);
    }

    @Override
    protected StringSearchCriteria getCriteria() {
        return passwordSearchCriteria;
    }

}
