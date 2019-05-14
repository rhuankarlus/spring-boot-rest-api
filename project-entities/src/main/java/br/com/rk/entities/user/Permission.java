package br.com.rk.entities.user;

import br.com.rk.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;

/**
 * Permissions used to configure authentication and authorization over the API
 *
 * @author Rhuan Karlus
 * @since 5/13/19
 */
public class Permission extends AbstractEntity {

    @Column(name = "name", unique = true, nullable = false, updatable = false)
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

    @ManyToMany(mappedBy = "permissions")
    private Set<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
