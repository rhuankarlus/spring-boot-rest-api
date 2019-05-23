package br.com.rk.repositories.user.specifications;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public class RoleNameSpecificationTest {

    @Test
    public void should_return_null_for_null_name() {
        assertNull(
                new RoleNameSpecification(null, null).toPredicate(null, null, null),
                "It should return null Predicate when value passed to RoleNameSpecification is null");
    }

    @Test
    public void should_return_null_for_empty_name() {
        assertNull(
                new RoleNameSpecification(null, "").toPredicate(null, null, null),
                "It should return null Predicate when value passed to RoleNameSpecification is empty");

        assertNull(
                new RoleNameSpecification(null, "     ").toPredicate(null, null, null),
                "It should return null Predicate when value passed to RoleNameSpecification is empty");

        assertNull(
                new RoleNameSpecification(null, "\t").toPredicate(null, null, null),
                "It should return null Predicate when value passed to RoleNameSpecification is empty");
    }

    @Test
    public void should_use_field_name() {
        assertEquals(
                "name",
                new RoleNameSpecification(null, null).getCriteria().getField(),
                "It should use field url for criteria when use RoleNameSpecification");
    }

}
