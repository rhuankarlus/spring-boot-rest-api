package br.com.rk.repositories.specifications.datetime;

import br.com.rk.entities.ProjectEntity;
import br.com.rk.repositories.specifications.ProjectSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Rhuan Karlus
 * @since 27/03/19
 */
public abstract class AbstractDateTimeFieldSpecification<E extends ProjectEntity> implements ProjectSpecification<E> {

    @Override
    public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (getDateTimeSearchCriteria().getFirstDate() == null) {
            return null;
        }

        if (getDateTimeSearchCriteria().getSecondDate() == null) {
            return criteriaBuilder.between(
                    root.get(getDateTimeSearchCriteria().getField()),
                    LocalDateTime.of(
                            getDateTimeSearchCriteria().getFirstDate().toLocalDate(),
                            LocalTime.MIN),
                    LocalDateTime.of(
                            getDateTimeSearchCriteria().getFirstDate().toLocalDate(),
                            LocalTime.MAX));
        }

        return criteriaBuilder.between(
                root.get(getDateTimeSearchCriteria().getField()),
                getDateTimeSearchCriteria().getFirstDate(),
                getDateTimeSearchCriteria().getSecondDate());
    }

    protected abstract DateTimeSearchCriteria getDateTimeSearchCriteria();

}
