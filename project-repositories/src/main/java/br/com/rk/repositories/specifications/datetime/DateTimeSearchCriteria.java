package br.com.rk.repositories.specifications.datetime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Rhuan Karlus
 * @since 27/03/19
 */
@Getter
@RequiredArgsConstructor
public class DateTimeSearchCriteria {

    private final String field;
    private final LocalDateTime firstDate;
    private final LocalDateTime secondDate;

}
