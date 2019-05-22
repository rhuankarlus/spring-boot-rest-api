package br.com.rk.controller.audit.dto;

import br.com.rk.controller.dto.DTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Rhuan Karlus
 * @since 27/03/19
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class AuditDTO implements DTO {

    private Long id;
    private String url;
    private String content;
    private Integer type;
    private Long dateTime;

}
