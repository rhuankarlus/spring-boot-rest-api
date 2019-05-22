package br.com.rk.repositories.specifications.string;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Simple search criteria used in Strings.
 *
 * @author Rhuan Karlus
 * @see <a href="https://www.baeldung.com/rest-api-search-language-spring-data-specifications">
 * https://www.baeldung.com/rest-api-search-language-spring-data-specifications</a>
 * @since 26/03/19
 */
@Getter
@RequiredArgsConstructor
public class StringSearchCriteria {

    private final String field;
    private final Operation operation;
    private final String value;

}
