package br.com.rk.repositories.user.specifications;

import br.com.rk.entities.user.User;
import br.com.rk.repositories.specifications.string.AbstractStringFieldSpecification;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.repositories.specifications.string.StringSearchCriteria;

/**
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public class UserUsernameSpecification extends AbstractStringFieldSpecification<User> {

    private static final String USERNAME = "username";

    private final StringSearchCriteria nameSearchCriteria;

    /**
     * Searchs for a user with the value passed, using {@link Operation#EQ}
     *
     * @param value The value to be search
     */
    public UserUsernameSpecification(final String value) {
        this.nameSearchCriteria = new StringSearchCriteria(USERNAME, Operation.EQ, value);
    }

    /**
     * @param operation The required operation
     * @param value     The value to search for
     */
    public UserUsernameSpecification(final Operation operation, final String value) {
        this.nameSearchCriteria = new StringSearchCriteria(USERNAME, operation, value);
    }

    @Override
    protected StringSearchCriteria getCriteria() {
        return nameSearchCriteria;
    }

}
