package br.com.rk.repositories.audit.specifications;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rhuan Karlus
 * @since 28/03/19
 */
public class UrlSpecificationTest {

    @Test
    public void should_return_null_for_null_url() {
        Assert.assertNull(
                "It should return null Predicate when value passed to UrlSpecification is null",
                new UrlSpecification(null, null).toPredicate(null, null, null));
    }

    @Test
    public void should_return_null_for_empty_url() {
        Assert.assertNull(
                "It should return null Predicate when value passed to UrlSpecification is empty",
                new UrlSpecification(null, "").toPredicate(null, null, null));

        Assert.assertNull(
                "It should return null Predicate when value passed to UrlSpecification is empty",
                new UrlSpecification(null, "     ").toPredicate(null, null, null));

        Assert.assertNull(
                "It should return null Predicate when value passed to UrlSpecification is empty",
                new UrlSpecification(null, "\t").toPredicate(null, null, null));
    }

    @Test
    public void should_use_field_url() {
        Assert.assertEquals(
                "It should use field url for criteria when use UrlSpecification",
                "url",
                new UrlSpecification(null, null).getCriteria().getField());
    }

}
