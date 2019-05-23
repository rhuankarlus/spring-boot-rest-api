package br.com.rk.repositories.user.specifications;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public class PermissionNameSpecificationTest {

    @Test
    public void should_return_null_for_null_name() {
        assertNull(
                new PermissionNameSpecification(null, null).toPredicate(null, null, null),
                "It should return null Predicate when value passed to PermissionNameSpecification is null");
    }

    @Test
    public void should_return_null_for_empty_name() {
        assertNull(
                new PermissionNameSpecification(null, "").toPredicate(null, null, null),
                "It should return null Predicate when value passed to PermissionNameSpecification is empty");

        assertNull(
                new PermissionNameSpecification(null, "     ").toPredicate(null, null, null),
                "It should return null Predicate when value passed to PermissionNameSpecification is empty");

        assertNull(
                new PermissionNameSpecification(null, "\t").toPredicate(null, null, null),
                "It should return null Predicate when value passed to PermissionNameSpecification is empty");
    }

    @Test
    public void should_use_field_name() {
        assertEquals(
                "name",
                new PermissionNameSpecification(null, null).getCriteria().getField(),
                "It should use field url for criteria when use PermissionNameSpecification");
    }

}
