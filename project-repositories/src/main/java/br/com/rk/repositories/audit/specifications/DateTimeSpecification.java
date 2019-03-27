package br.com.rk.repositories.audit.specifications;

import br.com.rk.entities.audit.Audit;
import br.com.rk.repositories.specifications.datetime.AbstractDateTimeFieldSpecification;
import br.com.rk.repositories.specifications.datetime.DateTimeSearchCriteria;

import java.time.LocalDateTime;

/**
 * @author Rhuan Karlus
 * @since 27/03/19
 */
public class DateTimeSpecification extends AbstractDateTimeFieldSpecification<Audit> {

    private final DateTimeSearchCriteria dateTimeSearchCriteria;

    public DateTimeSpecification(final LocalDateTime dateTime) {
        this(dateTime, null);
    }

    public DateTimeSpecification(final LocalDateTime firstDateTime, final LocalDateTime secondDateTime) {
        this.dateTimeSearchCriteria = new DateTimeSearchCriteria("dateTime", firstDateTime, secondDateTime);
    }

    @Override
    protected DateTimeSearchCriteria getDateTimeSearchCriteria() {
        return dateTimeSearchCriteria;
    }

}
