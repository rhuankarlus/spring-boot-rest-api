package br.com.rk.repositories.specifications.string;

/**
 * Simple search criteria used in Strings.
 *
 * @author Rhuan Karlus
 * @see <a href="https://www.baeldung.com/rest-api-search-language-spring-data-specifications">
 * https://www.baeldung.com/rest-api-search-language-spring-data-specifications</a>
 * @since 26/03/19
 */
public class StringSearchCriteria {

    private final String field;
    private final Operation operation;
    private final String value;

    /**
     * @param field     Key that will be search
     * @param operation Operation over that field
     * @param value     Value attributed to the field into the dataset
     */
    public StringSearchCriteria(final String field,
                                final Operation operation,
                                final String value) {
        this.field = field;
        this.operation = operation;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public Operation getOperation() {
        return operation;
    }

    public String getValue() {
        return value;
    }
}
