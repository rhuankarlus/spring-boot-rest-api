package br.com.rk.util.builders;

import br.com.rk.entities.audit.Audit;
import br.com.rk.entities.audit.AuditType;

import java.time.LocalDateTime;

/**
 * @author Rhuan Karlus
 * @since 11/04/2019
 */
public class AuditBuilder {

    private final Audit audit;

    private AuditBuilder() {
        this.audit = new Audit();
    }

    public static AuditBuilder init() {
        return new AuditBuilder();
    }

    public AuditBuilder id(final Long id) {
        audit.setId(id);
        return this;
    }

    public AuditBuilder url(final String url) {
        audit.setUrl(url);
        return this;
    }

    public AuditBuilder content(final String content) {
        audit.setContent(content);
        return this;
    }

    public AuditBuilder type(final AuditType type) {
        audit.setType(type);
        return this;
    }

    public AuditBuilder dateTime(final LocalDateTime dateTime) {
        audit.setDateTime(dateTime);
        return this;
    }

    public Audit build() {
        return audit;
    }

}
