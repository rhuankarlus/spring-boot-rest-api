package br.com.rk.entities.converters;

import br.com.rk.entities.audit.AuditType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Rhuan Karlus
 * @since 28/03/19
 */
public class AuditTypeConverterTest {

    public AuditTypeConverter sut;

    @BeforeEach
    public void setUp() {
        sut = new AuditTypeConverter();
    }

    @Test
    public void should_convert_audit_to_correct_code() {
        for (AuditType auditType : AuditType.values()) {
            assertEquals(
                    auditType.getCode(),
                    sut.convertToDatabaseColumn(auditType),
                    "The code was converted incorrectly");
        }
    }

    @Test
    public void should_convert_code_to_correct_audit() {
        for (AuditType auditType : AuditType.values()) {
            assertEquals(
                    auditType,
                    sut.convertToEntityAttribute(auditType.getCode()),
                    "The auditType was converted incorrectly");
        }
    }

    @Test
    public void should_convert_to_null_when_audit_type_is_null() {
        assertNull(
                sut.convertToDatabaseColumn(null),
                "The conversor should return null to persist on database when auditType is null.");
    }

    @Test
    public void should_convert_to_null_when_code_is_null() {
        assertNull(
                sut.convertToEntityAttribute(null),
                "The conversor should return null when the table value is null.");
    }

}
