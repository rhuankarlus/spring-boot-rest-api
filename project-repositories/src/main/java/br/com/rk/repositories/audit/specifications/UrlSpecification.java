package br.com.rk.repositories.audit.specifications;

import br.com.rk.entities.audit.Audit;
import br.com.rk.repositories.specifications.string.AbstractStringFieldSpecification;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.repositories.specifications.string.StringSearchCriteria;

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
