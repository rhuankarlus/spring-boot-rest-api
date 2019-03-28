package br.com.rk.entities.builder;

import br.com.rk.entities.audit.Audit;
import br.com.rk.entities.audit.AuditType;

import java.time.LocalDateTime;

/**
 * @author Rhuan Karlus
 * @since 28/03/19
 */
public class AuditBuilder {

    private final Audit audit;

    public AuditBuilder() {
        this.audit = new Audit();
    }

    public AuditBuilder url(String url) {
        this.audit.setUrl(url);
        return this;
    }

    public AuditBuilder content(String content) {
        this.audit.setContent(content);
        return this;
    }

    public AuditBuilder type(AuditType auditType) {
        this.audit.setType(auditType);
        return this;
    }

    public AuditBuilder dateTime(LocalDateTime dateTime) {
        this.audit.setDateTime(dateTime);
        return this;
    }

    public Audit build() {
        return this.audit;
    }

}
