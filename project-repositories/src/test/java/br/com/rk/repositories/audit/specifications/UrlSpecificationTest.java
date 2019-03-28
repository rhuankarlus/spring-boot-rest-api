package br.com.rk.repositories.audit.specifications;

import br.com.rk.repositories.specifications.string.Operation;
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
                new UrlSpecification(Operation.EQ, null).toPredicate(null, null, null));
    }

    @Test
    public void should_return_null_for_empty_url() {
        Assert.assertNull(
                "It should return null Predicate when value passed to UrlSpecification is empty",
                new UrlSpecification(Operation.EQ, "").toPredicate(null, null, null));

        Assert.assertNull(
                "It should return null Predicate when value passed to UrlSpecification is empty",
                new UrlSpecification(Operation.EQ, "     ").toPredicate(null, null, null));

        Assert.assertNull(
                "It should return null Predicate when value passed to UrlSpecification is empty",
                new UrlSpecification(Operation.EQ, "\t").toPredicate(null, null, null));
    }

}
