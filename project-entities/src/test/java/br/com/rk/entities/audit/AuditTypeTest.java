package br.com.rk.entities.audit;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rhuan Karlus
 * @since 22/03/19
 */
public class AuditTypeTest {

    @Test
    public void dont_use_same_code_for_two_audit_types() {
        final List<Integer> enumsCodes = new ArrayList<>();
        final List<String> enumsWithSameCode = new ArrayList<>();

        for (AuditType auditType : AuditType.values()) {
            if (enumsCodes.contains(auditType.getCode())) {
                enumsWithSameCode.add(auditType.name());
            }

            enumsCodes.add(auditType.getCode());
        }

        if (!enumsWithSameCode.isEmpty()) {
            Assert.fail("The following AuditTypes are repeated: " + String.join(",", enumsWithSameCode));
        }

    }

}
