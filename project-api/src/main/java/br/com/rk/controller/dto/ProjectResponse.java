package br.com.rk.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Sort;

/**
 * @author Rhuan Karlus
 * @since 06/03/2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectResponse {

    private Metadata headers;
    private Object content;
    private Pagination pagination;

    public class Metadata {

    }

    public static class Pagination {

        private final int pageNumber;
        private final int elementsInPage;
        private final long totalElements;
        private final int totalPages;
        private final Sort sort;

        public Pagination(int pageNumber, int elementsInPage, long totalElements, int totalPages, Sort sort) {
            this.pageNumber = pageNumber;
            this.elementsInPage = elementsInPage;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.sort = sort;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public int getElementsInPage() {
            return elementsInPage;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public long getTotalPages() {
            return totalPages;
        }

        public Sort getSort() {
            return sort;
        }
    }

    public static ProjectResponse of(final Object content) {
        return of(null, content, null);
    }

    public static ProjectResponse of(final Metadata metadata, final Object content) {
        return of(metadata, content, null);
    }

    public static ProjectResponse of(final Metadata metadata, final Object content, final Pagination pagination) {
        final ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.headers = metadata;
        projectResponse.content = content;
        projectResponse.pagination = pagination;
        return projectResponse;
    }

    public Metadata getHeaders() {
        return headers;
    }

    public void setHeaders(Metadata headers) {
        this.headers = headers;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
