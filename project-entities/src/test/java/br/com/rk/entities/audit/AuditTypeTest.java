package br.com.rk.entities.audit;

import br.com.rk.exceptions.EnumNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
            fail("The following AuditTypes are repeated: " + String.join(",", enumsWithSameCode));
        }

    }

    @Test
    public void should_throw_exception_when_code_is_null() throws EnumNotFoundException {
        final EnumNotFoundException expectedException =
                assertThrows(EnumNotFoundException.class, () -> AuditType.fromCode(null));

        assertEquals(
                "You should enter a valid argument in order to calculate the enum field.",
                expectedException.getMessage());
    }

    @Test
    public void should_throw_exception_when_code_not_exists() throws EnumNotFoundException {
        final Integer nonExistentCode = -1;

        final EnumNotFoundException expectedException =
                assertThrows(EnumNotFoundException.class, () -> AuditType.fromCode(nonExistentCode));

        assertEquals(
                "The code " + nonExistentCode + " doesn't belong to any constant.",
                expectedException.getMessage());
    }

}
