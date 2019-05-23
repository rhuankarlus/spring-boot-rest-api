package br.com.rk.repositories.user.specifications;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.repositories.specifications.string.AbstractStringFieldSpecification;
import br.com.rk.repositories.specifications.string.Operation;
import br.com.rk.repositories.specifications.string.StringSearchCriteria;

/**
 * Given that multiple beans in this package use the same field name, this is an abstraction for them
 *
 * @author Rhuan Karlus
 * @since 5/23/19
 */
public abstract class NameSpecification<E extends ProjectEntity> extends AbstractStringFieldSpecification<E> {

    private final StringSearchCriteria nameSearchCriteria;

    public NameSpecification(final Operation operation, final String value) {
        this.nameSearchCriteria = new StringSearchCriteria("name", operation, value);
    }

    @Override
    protected StringSearchCriteria getCriteria() {
        return nameSearchCriteria;
    }

}
