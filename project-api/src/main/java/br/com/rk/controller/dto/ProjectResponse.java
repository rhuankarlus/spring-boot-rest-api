package br.com.rk.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

/**
 * @author Rhuan Karlus
 * @since 06/03/2019
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectResponse {

    private Metadata metadata;
    private Object content;
    private Pagination pagination;

    public ProjectResponse(Object content) {
        this.content = content;
    }

    public ProjectResponse(Metadata metadata, Object content) {
        this.metadata = metadata;
        this.content = content;
    }

    public ProjectResponse(Object content, Pagination pagination) {
        this.content = content;
        this.pagination = pagination;
    }

    public ProjectResponse(Metadata metadata, Object content, Pagination pagination) {
        this.metadata = metadata;
        this.content = content;
        this.pagination = pagination;
    }

    /**
     * Simple implementation for errors
     *
     * @see <a href="https://www.baeldung.com/global-error-handler-in-a-spring-rest-api">
     * https://www.baeldung.com/global-error-handler-in-a-spring-rest-api</a>
     */
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata {

        private HttpStatus status;
        private String message;
        private List<String> errors;

        public Metadata(HttpStatus status, String message) {
            super();
            this.status = status;
            this.message = message;
        }

        public Metadata(HttpStatus status, String message, List<String> errors) {
            super();
            this.status = status;
            this.message = message;
            this.errors = errors;
        }

        public Metadata(HttpStatus status, String message, String error) {
            super();
            this.status = status;
            this.message = message;
            errors = Collections.singletonList(error);
        }

    }

    /**
     * Show searchs pagination
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Pagination {

        private int pageNumber;
        private int elementsInPage;
        private long totalElements;
        private int totalPages;
        private Sort sort;

        public Pagination(int pageNumber, int elementsInPage, long totalElements, int totalPages, Sort sort) {
            this.pageNumber = pageNumber;
            this.elementsInPage = elementsInPage;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.sort = sort;
        }

    }

    public static ProjectResponse ok(final Object content) {
        return of(null, content, (Pagination) null);
    }

    public static ProjectResponse ok(final Object content, final Page<?> page) {
        return of(null, content, page);
    }

    public static ProjectResponse error(HttpStatus status, String message) {
        return error(status, message, null);
    }

    public static ProjectResponse error(HttpStatus status, String message, List<String> errors) {
        return of(new Metadata(status, message, errors), null, (Pagination) null);
    }

    public static ProjectResponse of(final Page<?> page) {
        return of(null, page.getContent(), page);
    }

    public static ProjectResponse of(final Metadata metadata, final Object content, final Page<?> page) {
        return of(metadata, content, convertToPagination(page));
    }

    public static ProjectResponse of(final Metadata metadata, final Object content, final Pagination pagination) {
        final ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.metadata = metadata;
        projectResponse.content = content;
        projectResponse.pagination = pagination;
        return projectResponse;
    }

    private static Pagination convertToPagination(final Page<?> page) {
        if (page == null) {
            return null;
        }

        return new Pagination(
                page.getNumber(),
                page.getNumberOfElements(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getSort());
    }

}
