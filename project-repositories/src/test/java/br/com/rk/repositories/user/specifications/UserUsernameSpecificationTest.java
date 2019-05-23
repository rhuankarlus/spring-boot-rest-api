package br.com.rk.repositories.user.specifications;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public class UserUsernameSpecificationTest {

    @Test
    public void should_return_null_for_null_username() {
        assertNull(
                new UserUsernameSpecification(null).toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserUsernameSpecification is null");

        assertNull(
                new UserUsernameSpecification(null, null).toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserUsernameSpecification is null");
    }

    @Test
    public void should_return_null_for_empty_username() {
        assertNull(
                new UserUsernameSpecification("").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserUsernameSpecification is empty");

        assertNull(
                new UserUsernameSpecification("     ").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserUsernameSpecification is empty");

        assertNull(
                new UserUsernameSpecification("\t").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserUsernameSpecification is empty");

        assertNull(
                new UserUsernameSpecification(null, "").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserUsernameSpecification is empty");

        assertNull(
                new UserUsernameSpecification(null, "     ").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserUsernameSpecification is empty");

        assertNull(
                new UserUsernameSpecification(null, "\t").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserUsernameSpecification is empty");
    }

    @Test
    public void should_use_field_username() {
        assertEquals(
                "username",
                new UserUsernameSpecification(null).getCriteria().getField(),
                "It should use field url for criteria when use UserUsernameSpecification");

        assertEquals(
                "username",
                new UserUsernameSpecification(null, null).getCriteria().getField(),
                "It should use field url for criteria when use UserUsernameSpecification");
    }

}
