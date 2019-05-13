package br.com.rk.entities.user;

import br.com.rk.entities.AbstractEntity;

import java.util.List;

/**
 * System security roles
 *
 * @author Rhuan Karlus
 * @since 5/13/19
 */
public class Role extends AbstractEntity {

    private String name;
    private List<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
