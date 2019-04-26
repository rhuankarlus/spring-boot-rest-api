package br.com.rk.controller.audit.dto;

import br.com.rk.controller.dto.DTO;

/**
 * @author Rhuan Karlus
 * @since 27/03/19
 */
public class AuditDTO implements DTO {

    private Long id;
    private String url;
    private String content;
    private Integer type;
    private Long dateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }
}
