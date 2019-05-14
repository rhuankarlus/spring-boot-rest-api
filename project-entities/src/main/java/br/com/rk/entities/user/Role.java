package br.com.rk.entities.user;

import br.com.rk.entities.AbstractEntity;

import javax.persistence.Column;
import java.util.Objects;
import java.util.Set;

/**
 * System security roles
 *
 * @author Rhuan Karlus
 * @since 5/13/19
 */
public class Role extends AbstractEntity {

    @Column(name = "name", unique = true, nullable = false, updatable = false)
    private String name;
    private Set<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
