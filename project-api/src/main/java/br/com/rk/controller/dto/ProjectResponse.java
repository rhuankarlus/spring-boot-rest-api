package br.com.rk.controller.dto;

/**
 * @author Rhuan Karlus
 * @since 06/03/2019
 */
public class ProjectResponse {

    private Metadata headers;
    private DTO body;
    private Paginacao pagination;

    public static ProjectResponse of(DTO dto) {
        final ProjectResponse projectResponse = new ProjectResponse();
        projectResponse.body = dto;
        return projectResponse;
    }

    public class Metadata {
        // todo: inserir os metadados padrões de uma resposta...
    }

    public class Paginacao {
        // todo: inserir informações sobre a página atual, caso não houver página pode ser nulo
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
