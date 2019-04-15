package br.com.rk.util.builders;

import br.com.rk.entities.audit.Audit;
import br.com.rk.entities.audit.AuditType;

import java.time.LocalDateTime;

/**
 * @author Rhuan Karlus
 * @since 11/04/2019
 */
public class AuditEntityBuilder extends AbstractEntityBuilder<Audit> {

    private final Audit audit;

    private AuditEntityBuilder() {
        this.audit = new Audit();
    }

    public static AuditEntityBuilder init() {
        return new AuditEntityBuilder();
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

    @Override
    protected Audit getEntity() {
        return audit;
    }
}
