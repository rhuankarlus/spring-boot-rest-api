package br.com.rk.repositories.audit;

import br.com.rk.entities.audit.Audit;
import br.com.rk.entities.audit.AuditType;
import br.com.rk.repositories.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

/**
 * @author Rhuan Karlus
 * @since 22/03/19
 */
public interface AuditRepository extends ProjectRepository<Audit> {

    Page<Audit> findByUrl(String url, Pageable pageable);

    Page<Audit> findByType(AuditType type, Pageable pageable);

    Page<Audit> findByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);

}
