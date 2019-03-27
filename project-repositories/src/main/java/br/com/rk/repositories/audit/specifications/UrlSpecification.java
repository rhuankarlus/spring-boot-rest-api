package br.com.rk.repositories.audit.specifications;

import br.com.rk.entities.audit.Audit;
import br.com.rk.repositories.specifications.string.AbstractStringFieldSpecification;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.repositories.specifications.string.StringSearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Search specification for the {@code url} field from {@link Audit} entity
 *
 * @author Rhuan Karlus
 * @since 22/03/19
 */
public class UrlSpecification extends AbstractStringFieldSpecification<Audit> {

    private final StringSearchCriteria urlSearchCriteria;

    public UrlSpecification(final Operation operation, final String value) {
        this.urlSearchCriteria = new StringSearchCriteria("url", operation, value);
    }

    @Override
    protected StringSearchCriteria getCriteria() {
        return urlSearchCriteria;
    }

}
