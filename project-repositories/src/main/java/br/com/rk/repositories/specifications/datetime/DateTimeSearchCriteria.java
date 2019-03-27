package br.com.rk.repositories.specifications.datetime;

import java.time.LocalDateTime;

/**
 * @author Rhuan Karlus
 * @since 27/03/19
 */
public class DateTimeSearchCriteria {

    private final String field;
    private final LocalDateTime firstDate;
    private LocalDateTime secondDate;

    public DateTimeSearchCriteria(String field, LocalDateTime firstDate, LocalDateTime secondDate) {
        this.field = field;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    public String getField() {
        return field;
    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    public LocalDateTime getSecondDate() {
        return secondDate;
    }
}
