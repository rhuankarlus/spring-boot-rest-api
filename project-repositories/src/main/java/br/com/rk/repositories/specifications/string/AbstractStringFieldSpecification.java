package br.com.rk.repositories.specifications.string;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.repositories.specifications.ProjectSpecification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * A simple abstraction to search Strings over dataset
 *
 * @author Rhuan Karlus
 * @since 26/03/19
 */
public abstract class AbstractStringFieldSpecification<E extends ProjectEntity> implements ProjectSpecification<E> {

    @Override
    public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (!StringUtils.hasText(getCriteria().getValue())) {
            return null;
        }

        switch (getCriteria().getOperation()) {
            case EQ:
                return criteriaBuilder.equal(root.get(getCriteria().getField()), getCriteria().getValue());

            case GT:
                return criteriaBuilder.greaterThan(
                        root.get(getCriteria().getField()), getCriteria().getValue());

            case GTE:
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(getCriteria().getField()), getCriteria().getValue());

            case LT:
                return criteriaBuilder.lessThan(
                        root.get(getCriteria().getField()), getCriteria().getValue());

            case LTE:
                return criteriaBuilder.lessThanOrEqualTo(
                        root.get(getCriteria().getField()), getCriteria().getValue());

            case LK:
                return criteriaBuilder.like(
                        root.get(getCriteria().getField()), "%" + getCriteria().getValue() + "%");

            case LK_BEFORE:
                return criteriaBuilder.like(
                        root.get(getCriteria().getField()), "%" + getCriteria().getValue());

            case LK_AFTER:
                return criteriaBuilder.like(
                        root.get(getCriteria().getField()), getCriteria().getValue() + "%");

            default:
                return null;
        }
    }

    protected abstract StringSearchCriteria getCriteria();

}
