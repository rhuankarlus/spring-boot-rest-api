package br.com.rk.entities.user;

import br.com.rk.entities.AbstractEntity;

import java.util.Set;

/**
 * User domain for the whole application.
 * You can customize this class or create another bean to separate security logic and domain properties.
 *
 * @author Rhuan Karlus
 * @since 5/13/2019
 */
public class User extends AbstractEntity {

    private String username;
    private String password;
    private Set<Permission> permissions;
    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
