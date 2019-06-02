package br.com.rk.services.audit.impl;

import br.com.rk.entities.audit.Audit;
import br.com.rk.repositories.ProjectRepository;
import br.com.rk.repositories.audit.AuditRepository;
import br.com.rk.repositories.audit.specifications.AuditTypeSpecification;
import br.com.rk.repositories.audit.specifications.DateTimeSpecification;
import br.com.rk.repositories.audit.specifications.UrlSpecification;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.services.AbstractCrudService;
import br.com.rk.services.audit.AuditCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link AuditCrudService}
 *
 * @author Rhuan Karlus
 * @since 26/03/19
 */
@Service
public class AuditCrudServiceImpl extends AbstractCrudService<Audit> implements AuditCrudService {

    private final AuditRepository auditRepository;

    @Autowired
    public AuditCrudServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    protected AuditRepository getRepository() {
        return auditRepository;
    }

    @Override
    protected Specification<Audit> buildAllSpecifications(final Audit audit) {
        return Specification
                .where(new UrlSpecification(Operation.LK, audit.getUrl()))
                .and(new AuditTypeSpecification(audit.getType()))
                .and(new DateTimeSpecification(audit.getDateTime()));
    }

}
