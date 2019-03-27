package br.com.rk.repositories.audit.specifications;

import br.com.rk.entities.audit.Audit;
import br.com.rk.entities.audit.AuditType;
import br.com.rk.repositories.specifications.ProjectSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Rhuan Karlus
 * @since 27/03/19
 */
public class AuditTypeSpecification implements ProjectSpecification<Audit> {

    private final AuditType auditType;

    public AuditTypeSpecification(final AuditType auditType) {
        this.auditType = auditType;
    }

    @Override
    public Predicate toPredicate(Root<Audit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (auditType == null) {
            return null;
        }

        return criteriaBuilder.equal(root.get("type"), auditType);
    }

}
