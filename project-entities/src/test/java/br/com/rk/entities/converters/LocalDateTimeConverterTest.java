package br.com.rk.entities.converters;

import br.com.rk.entities.audit.AuditType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Rhuan Karlus
 * @since 28/03/19
 */
public class AuditTypeConverterTest {

    public AuditTypeConverter sut;

    @Before
    public void setUp() {
        sut = new AuditTypeConverter();
    }

    @Test
    public void should_convert_audit_to_correct_code() {
        for (AuditType auditType : AuditType.values()) {
            Assert.assertEquals(
                    "The code was converted incorrectly",
                    auditType.getCode(),
                    sut.convertToDatabaseColumn(auditType));
        }
    }

    @Test
    public void should_convert_code_to_correct_audit() {
        for (AuditType auditType : AuditType.values()) {
            Assert.assertEquals(
                    "The auditType was converted incorrectly",
                    auditType,
                    sut.convertToEntityAttribute(auditType.getCode()));
        }
    }

    @Test
    public void should_convert_to_null_when_audit_type_is_null() {
        Assert.assertNull(
                "The conversor should return null to persist on database when auditType is null.",
                sut.convertToDatabaseColumn(null));
    }

    @Test
    public void should_convert_to_null_when_code_is_null() {
        Assert.assertNull(
                "The conversor should return null when the table value is null.",
                sut.convertToEntityAttribute(null));
    }

}
