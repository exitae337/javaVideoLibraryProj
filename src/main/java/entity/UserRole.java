package entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;
    @Column
    private String role_name;

    public UserRole () {}

    public int getRole_id() {
        return role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    // Setters

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
