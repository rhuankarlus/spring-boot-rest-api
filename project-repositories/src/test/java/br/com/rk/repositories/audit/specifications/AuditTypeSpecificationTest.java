package br.com.rk.repositories.audit.specifications;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Rhuan Karlus
 * @since 27/03/19
 */
public class AuditTypeSpecificationTest {

    @Test
    public void should_return_null_for_null_audit_type() {
        Assertions.assertNull(
                new AuditTypeSpecification(null).toPredicate(null, null, null),
                "It should return null Predicate when audit type passed to AuditTypeSpecification is null");
    }

}
