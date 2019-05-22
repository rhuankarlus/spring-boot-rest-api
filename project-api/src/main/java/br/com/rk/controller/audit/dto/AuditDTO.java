package br.com.rk.controller.audit.dto;

import br.com.rk.controller.dto.DTO;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Rhuan Karlus
 * @since 27/03/19
 */
@Getter
@Builder
public class AuditDTO implements DTO {

    private Long id;
    private String url;
    private String content;
    private Integer type;
    private Long dateTime;

}
