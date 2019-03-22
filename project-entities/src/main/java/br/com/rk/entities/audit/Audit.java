package br.com.rk.entities.audit;

import br.com.rk.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * @author Rhuan Karlus
 * @since 22/03/19
 */
@Entity
public class Audit extends AbstractEntity {

    @Column(name = "url")
    private String url;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "type", nullable = false)
    private AuditType type;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AuditType getType() {
        return type;
    }

    public void setType(AuditType type) {
        this.type = type;
    }
}
