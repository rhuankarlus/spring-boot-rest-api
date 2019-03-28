package br.com.rk.entities.converters;

import br.com.rk.entities.audit.AuditType;
import br.com.rk.exceptions.EnumNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Rhuan Karlus
 * @since 22/03/19
 */
@Converter(autoApply = true)
public class AuditTypeConverter implements AttributeConverter<AuditType, Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditTypeConverter.class);

    @Override
    public Integer convertToDatabaseColumn(AuditType auditType) {
        return auditType == null ? null : auditType.getCode();
    }

    @Override
    public AuditType convertToEntityAttribute(Integer code) {
        try {
            return AuditType.fromCode(code);
        } catch (EnumNotFoundException e) {
            LOGGER.error("Error trying to convert code {} to entity attribute.", code, e);
            return null;
        }
    }

}
