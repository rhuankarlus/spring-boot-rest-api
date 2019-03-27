package br.com.rk.controller.audit.converters;

import br.com.rk.controller.audit.dto.AuditDTO;
import br.com.rk.converters.Conversor;
import br.com.rk.entities.audit.Audit;
import br.com.rk.entities.audit.AuditType;
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
    public Audit toEntity(final AuditDTO dto) {
        final Audit audit = new Audit();
        audit.setUrl(dto.getUrl());
        audit.setContent(dto.getContent());
        audit.setType(AuditType.fromCode(dto.getType()));

        if (dto.getDateTime() != null) {
            audit.setDateTime(Instant.ofEpochMilli(dto.getDateTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
        }

        return audit;
    }

    @Override
    public AuditDTO toDTO(final Audit audit) {
        final AuditDTO dto = new AuditDTO();
        dto.setUrl(audit.getUrl());
        dto.setContent(audit.getContent());
        dto.setType(audit.getType().getCode());
        dto.setDateTime(null);

        return dto;
    }

}
