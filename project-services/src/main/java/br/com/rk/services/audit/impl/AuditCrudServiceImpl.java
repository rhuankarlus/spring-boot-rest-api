package br.com.rk.services.audit.impl;

import br.com.rk.entities.audit.Audit;
import br.com.rk.repositories.audit.specifications.AuditTypeSpecification;
import br.com.rk.repositories.audit.specifications.DateTimeSpecification;
import br.com.rk.repositories.audit.specifications.UrlSpecification;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.services.AbstractCrudService;
import br.com.rk.services.audit.AuditProjectCrudService;
import br.com.rk.services.exception.ServiceException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link AuditProjectCrudService}
 *
 * @author Rhuan Karlus
 * @since 26/03/19
 */
@Service
public class AuditCrudServiceImpl extends AbstractCrudService<Audit> implements AuditProjectCrudService {

    @Override
    protected void validateParams(Audit audit, Pageable pageable) throws ServiceException {
        if (audit == null) {
            throw new ServiceException("The audit object can't be null.");
        }

        if (pageable == null) {
            throw new ServiceException("The pageable object can't be null.");
        }
    }

    @Override
    protected Specification<Audit> buildSpecifications(final Audit audit) {
        return Specification
                .where(new UrlSpecification(Operation.LK, audit.getUrl()))
                .and(new AuditTypeSpecification(audit.getType()))
                .and(new DateTimeSpecification(audit.getDateTime()));
    }

}
