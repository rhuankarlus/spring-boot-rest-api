package br.com.rk.entities.audit;

import br.com.rk.exceptions.EnumNotFoundException;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author Rhuan Karlus
 * @since 22/03/19
 */
@Getter
public enum AuditType {

    ERROR(1, "Error"),
    INFO(2, "Information");

    private Integer code;
    private String description;

    AuditType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static AuditType fromCode(Integer code) throws EnumNotFoundException {
        if (code == null) {
            throw new EnumNotFoundException("You should enter a valid argument in order to calculate the enum field.");
        }

        return Arrays.stream(AuditType.values())
                .filter(auditType -> auditType.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new EnumNotFoundException("The code " + code + " doesn't belong to any constant."));
    }

}
