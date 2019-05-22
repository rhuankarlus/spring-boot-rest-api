package br.com.rk.entities.audit;

import br.com.rk.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Rhuan Karlus
 * @since 22/03/19
 */
@Getter
@Setter
@Entity
@Table(name = "audit")
public class Audit extends AbstractEntity {

    @Column(name = "url")
    private String url;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "type", nullable = false)
    private AuditType type;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

}
