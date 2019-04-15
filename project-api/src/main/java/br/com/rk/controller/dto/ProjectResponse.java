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

    public ProjectResponse() {
    }

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

    public static class Metadata {

        private int errorCode;
        private String errorDescription;

        public Metadata() {
        }

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

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public void setErrorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
        }
    }

    public static class Pagination {

        private int pageNumber;
        private int elementsInPage;
        private long totalElements;
        private int totalPages;
        private Sort sort;

        public Pagination() {
        }

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

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public void setElementsInPage(int elementsInPage) {
            this.elementsInPage = elementsInPage;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public void setSort(Sort sort) {
            this.sort = sort;
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
        if (page == null) {
            return null;
        }

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
