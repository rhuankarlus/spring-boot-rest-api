package br.com.rk.repositories.user.specifications;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public class UserPasswordSpecificationTest {

    @Test
    public void should_return_null_for_null_password() {
        assertNull(
                new UserPasswordSpecification(null).toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserPasswordSpecification is null");

        assertNull(
                new UserPasswordSpecification(null, null).toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserPasswordSpecification is null");
    }

    @Test
    public void should_return_null_for_empty_password() {
        assertNull(
                new UserPasswordSpecification("").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserPasswordSpecification is empty");

        assertNull(
                new UserPasswordSpecification("     ").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserPasswordSpecification is empty");

        assertNull(
                new UserPasswordSpecification("\t").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserPasswordSpecification is empty");

        assertNull(
                new UserPasswordSpecification(null, "").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserPasswordSpecification is empty");

        assertNull(
                new UserPasswordSpecification(null, "     ").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserPasswordSpecification is empty");

        assertNull(
                new UserPasswordSpecification(null, "\t").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UserPasswordSpecification is empty");
    }

    @Test
    public void should_use_field_password() {
        assertEquals(
                "password",
                new UserPasswordSpecification(null).getCriteria().getField(),
                "It should use field url for criteria when use UserPasswordSpecification");

        assertEquals(
                "password",
                new UserPasswordSpecification(null, null).getCriteria().getField(),
                "It should use field url for criteria when use UserPasswordSpecification");
    }

}
