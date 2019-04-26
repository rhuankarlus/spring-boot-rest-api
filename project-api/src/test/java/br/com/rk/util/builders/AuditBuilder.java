package br.com.rk.util.builders;

import br.com.rk.controller.audit.dto.AuditDTO;
import br.com.rk.entities.audit.Audit;
import br.com.rk.entities.audit.AuditType;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Rhuan Karlus
 * @since 11/04/2019
 */
public class AuditBuilder {

    private AuditBuilder() {
    }

    public static AuditEntityBuilder initEntity() {
        return new AuditEntityBuilder();
    }

    public static AuditDTOBuilder initDTO() {
        return new AuditDTOBuilder();
    }

    public static class AuditEntityBuilder {
        private final Audit audit;

        public AuditEntityBuilder() {
            this.audit = new Audit();
        }

        public AuditEntityBuilder id(final Long id) {
            audit.setId(id);
            return this;
        }

        public AuditEntityBuilder url(final String url) {
            audit.setUrl(url);
            return this;
        }

        public AuditEntityBuilder content(final String content) {
            audit.setContent(content);
            return this;
        }

        public AuditEntityBuilder type(final AuditType type) {
            audit.setType(type);
            return this;
        }

        public AuditEntityBuilder dateTime(final LocalDateTime dateTime) {
            audit.setDateTime(dateTime);
            return this;
        }

        public Audit build() {
            return audit;
        }
    }

    public static class AuditDTOBuilder {
        private final AuditDTO audit;

        public AuditDTOBuilder() {
            this.audit = new AuditDTO();
        }

        public AuditDTOBuilder id(final Long id) {
            audit.setId(id);
            return this;
        }

        public AuditDTOBuilder url(final String url) {
            audit.setUrl(url);
            return this;
        }

        public AuditDTOBuilder content(final String content) {
            audit.setContent(content);
            return this;
        }

        public AuditDTOBuilder type(final AuditType type) {
            audit.setType(type.getCode());
            return this;
        }

        public AuditDTOBuilder dateTime(final LocalDateTime dateTime) {
            audit.setDateTime(dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            return this;
        }

        public AuditDTO build() {
            return audit;
        }
    }

}
