package br.com.rk.repositories.audit.specifications;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Rhuan Karlus
 * @since 28/03/19
 */
public class UrlSpecificationTest {

    @Test
    public void should_return_null_for_null_url() {
        assertNull(
                new UrlSpecification(null, null).toPredicate(null, null, null),
                "It should return null Predicate when value passed to UrlSpecification is null");
    }

    @Test
    public void should_return_null_for_empty_url() {
        assertNull(
                new UrlSpecification(null, "").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UrlSpecification is empty");

        assertNull(
                new UrlSpecification(null, "     ").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UrlSpecification is empty");

        assertNull(
                new UrlSpecification(null, "\t").toPredicate(null, null, null),
                "It should return null Predicate when value passed to UrlSpecification is empty");
    }

    @Test
    public void should_use_field_url() {
        assertEquals(
                "url",
                new UrlSpecification(null, null).getCriteria().getField(),
                "It should use field url for criteria when use UrlSpecification");
    }

}
