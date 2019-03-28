package br.com.rk.repositories.audit.specifications;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rhuan Karlus
 * @since 28/03/19
 */
public class DateTimeSpecificationTest {

    @Test
    public void should_return_null_for_null_first_date() {
        Assert.assertNull(
                "It should return null Predicate when first date passed to DateTimeSpecification is null",
                new DateTimeSpecification(null).toPredicate(null, null, null));
    }

    @Test
    public void should_return_null_for_null_first_date_with_whatever_second_date() {
        Assert.assertNull(
                "It should return null Predicate when first date passed to DateTimeSpecification is null",
                new DateTimeSpecification(null, null).toPredicate(null, null, null));
    }

    @Test
    public void should_use_field_date_time() {
        Assert.assertEquals(
                "It should use field dateTime for criteria when use DateTimeSpecification",
                "dateTime",
                new DateTimeSpecification(null).getDateTimeSearchCriteria().getField());
    }

}
