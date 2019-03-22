package br.com.rk.entities.audit;

import java.util.Arrays;

/**
 * @author Rhuan Karlus
 * @since 22/03/19
 */
public enum AuditType {

    ERROR(1, "Error");

    private Integer code;
    private String description;

    AuditType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static AuditType fromCode(Integer code) {
        if (code == null) {
            return null;
        }

        return Arrays.stream(AuditType.values()).filter(auditType -> auditType.getCode().equals(code)).findFirst().orElse(null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
