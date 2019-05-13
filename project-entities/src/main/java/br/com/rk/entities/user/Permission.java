package br.com.rk.entities.user;

import br.com.rk.entities.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Permissions used to configure authentication and authorization over the API
 *
 * @author Rhuan Karlus
 * @since 5/13/19
 */
public class Permission extends AbstractEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;
    private List<Role> roles;
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
