package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ask on 06.02.2018
 */

@Entity(name = "App_Roles")
public class AppRoles {

    @Id
    private Long id;

    @Column(nullable = false)
    private String role_name; /* Standard_user or Admin_user */

    @Column(nullable = false)
    private String role_desc; /* Standard User - Has no admin rights, Admin User - Has permission to perform admin tasks */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_desc() {
        return role_desc;
    }

    public void setRole_desc(String role_desc) {
        this.role_desc = role_desc;
    }
}
