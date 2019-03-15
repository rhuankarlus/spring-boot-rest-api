package br.com.rk.controller.dto;

import org.springframework.data.domain.Sort;

/**
 * @author Rhuan Karlus
 * @since 06/03/2019
 */
public class ProjectResponse {

    private Metadata headers;
    private Object body;
    private Pagination pagination;

    public class Metadata {
        // todo: inserir os metadados padrões de uma resposta...
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

    public static ProjectResponse of(final Object body) {
        return of(null, body, null);
    }

    public static ProjectResponse of(final Metadata metadata, final Object body) {
        return of(metadata, body, null);
    }

    public static ProjectResponse of(final Metadata metadata, final Object body, final Pagination pagination) {
        final ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.headers = metadata;
        projectResponse.body = body;
        projectResponse.pagination = pagination;
        return projectResponse;
    }

    public Metadata getHeaders() {
        return headers;
    }

    public void setHeaders(Metadata headers) {
        this.headers = headers;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
