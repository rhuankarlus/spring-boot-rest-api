package br.com.rk.entities.audit;

import br.com.rk.exceptions.EnumNotFoundException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rhuan Karlus
 * @since 22/03/19
 */
public class AuditTypeTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

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

    @Test
    public void should_throw_exception_when_code_is_null() throws EnumNotFoundException {
        exceptionRule.expect(EnumNotFoundException.class);
        exceptionRule.expectMessage("You should enter a valid argument in order to calculate the enum field.");

        AuditType.fromCode(null);
    }

    @Test
    public void should_throw_exception_when_code_not_exists() throws EnumNotFoundException {
        final Integer nonExistentCode = Arrays.stream(AuditType.values())
                .mapToInt(AuditType::getCode)
                .min()
                .orElse(0) - 1;

        exceptionRule.expect(EnumNotFoundException.class);
        exceptionRule.expectMessage("The code " + nonExistentCode + " doesn't belong to any constant.");

        AuditType.fromCode(nonExistentCode);
    }

}
