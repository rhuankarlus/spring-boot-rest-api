package br.com.rk.repositories.audit.specifications;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Rhuan Karlus
 * @since 28/03/19
 */
public class DateTimeSpecificationTest {

    @Test
    public void should_return_null_for_null_first_date() {
        assertNull(
                new DateTimeSpecification(null).toPredicate(null, null, null),
                "It should return null Predicate when first date passed to DateTimeSpecification is null");
    }

    @Test
    public void should_return_null_for_null_first_date_with_whatever_second_date() {
        assertNull(
                new DateTimeSpecification(null, null).toPredicate(null, null, null),
                "It should return null Predicate when first date passed to DateTimeSpecification is null");
    }

    @Test
    public void should_use_field_date_time() {
        assertEquals(
                "dateTime",
                new DateTimeSpecification(null).getDateTimeSearchCriteria().getField(),
                "It should use field dateTime for criteria when use DateTimeSpecification");
    }

}
