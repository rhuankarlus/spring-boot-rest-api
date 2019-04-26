package br.com.rk.controller.audit.converters;

import br.com.rk.controller.audit.dto.AuditDTO;
import br.com.rk.converters.Conversor;
import br.com.rk.converters.ConverterException;
import br.com.rk.entities.audit.Audit;
import br.com.rk.entities.audit.AuditType;
import br.com.rk.exceptions.EnumNotFoundException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;

/**
 * @author Rhuan Karlus
 * @since 27/03/19
 */
@Component
public class AuditConversor implements Conversor<AuditDTO, Audit> {

    @Override
    public Audit toEntity(final AuditDTO dto) throws ConverterException {
        final Audit audit = new Audit();
        audit.setId(dto.getId());
        audit.setUrl(dto.getUrl());
        audit.setContent(dto.getContent());

        try {
            if (dto.getType() != null) {
                audit.setType(AuditType.fromCode(dto.getType()));
            }
        } catch (EnumNotFoundException e) {
            throw new ConverterException("Error when trying to convert AuditType code.", e);
        }

        if (dto.getDateTime() != null) {
            audit.setDateTime(Instant.ofEpochMilli(dto.getDateTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        return audit;
    }

    @Override
    public AuditDTO toDTO(final Audit audit) {
        final AuditDTO dto = new AuditDTO();
        dto.setId(audit.getId());
        dto.setUrl(audit.getUrl());
        dto.setContent(audit.getContent());
        dto.setType(audit.getType().getCode());
        dto.setDateTime(audit.getDateTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        return dto;
    }

}
