package br.com.rk.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Rhuan Karlus
 * @since 06/03/2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectResponse {

    private Metadata metadata;
    private Object content;
    private Pagination pagination;

    public static class Metadata {

        private final int errorCode;
        private final String errorDescription;

        public Metadata(int errorCode, String errorDescription) {
            this.errorCode = errorCode;
            this.errorDescription = errorDescription;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getErrorDescription() {
            return errorDescription;
        }
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

    public static ProjectResponse ok(final Object content) {
        return of(null, content, null);
    }

    public static ProjectResponse ok(final Object content, final Page<?> page) {
        return of(null, content, page);
    }

    public static ProjectResponse error(int errorCode, String errorDescription) {
        return error(errorCode, errorDescription, null);
    }

    public static ProjectResponse error(int errorCode, String errorDescription, final Object content) {
        return of(new Metadata(errorCode, errorDescription), content, null);
    }

    public static ProjectResponse of(final Metadata metadata, final Object content, final Page<?> page) {
        final ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.metadata = metadata;
        projectResponse.content = content;
        projectResponse.pagination = convertToPagination(page);
        return projectResponse;
    }

    public static ProjectResponse of(int errorCode, String errorDescription, final Object content, final Page<?> page) {
        final ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.metadata = new Metadata(errorCode, errorDescription);
        projectResponse.content = content;
        projectResponse.pagination = convertToPagination(page);
        return projectResponse;
    }

    private static Pagination convertToPagination(final Page<?> page) {
        return new Pagination(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getContent().size(),
                page.getSort());
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
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
