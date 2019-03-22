package br.com.rk.entities.converters;

import br.com.rk.entities.audit.AuditType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Rhuan Karlus
 * @since 22/03/19
 */
@Converter(autoApply = true)
public class AuditTypeConverter implements AttributeConverter<AuditType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AuditType auditType) {
        return auditType.getCode();
    }

    @Override
    public AuditType convertToEntityAttribute(Integer code) {
        return AuditType.fromCode(code);
    }

}
