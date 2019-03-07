package br.com.rk.controller.dto;

/**
 * @author Rhuan Karlus
 * @since 06/03/2019
 */
public class ProjectResponse {

    private Metadata headers;
    private Object corpo;
    private Paginacao paginacao;

    public class Metadata {
        // todo: inserir os metadados padrões de uma resposta...
    }

    public class Paginacao {
        // todo: inserir informações sobre a página atual, caso não houver página pode ser nulo
    }

}
