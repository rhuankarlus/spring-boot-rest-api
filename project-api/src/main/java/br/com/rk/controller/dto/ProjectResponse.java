package br.com.rk.controller.dto;

/**
 * @author Rhuan Karlus
 * @since 06/03/2019
 */
public class ProjectResponse {

    private Metadata headers;
    private DTO body;
    private Paginacao pagination;

    public class Metadata {
        // todo: inserir os metadados padrões de uma resposta...
    }

    public class Paginacao {
        // todo: inserir informações sobre a página atual, caso não houver página pode ser nulo
    }

    public static ProjectResponse of(final DTO dto) {
        return of(null, dto, null);
    }

    public static ProjectResponse of(final Metadata metadata, final DTO dto) {
        return of(metadata, dto, null);
    }

    public static ProjectResponse of(final Metadata metadata, final DTO dto, final Paginacao pagination) {
        final ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.headers = metadata;
        projectResponse.body = dto;
        projectResponse.pagination = pagination;
        return projectResponse;
    }

    public Metadata getHeaders() {
        return headers;
    }

    public void setHeaders(Metadata headers) {
        this.headers = headers;
    }

    public DTO getBody() {
        return body;
    }

    public void setBody(DTO body) {
        this.body = body;
    }

    public Paginacao getPagination() {
        return pagination;
    }

    public void setPagination(Paginacao pagination) {
        this.pagination = pagination;
    }
}
