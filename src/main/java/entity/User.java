package entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column
    private String user_fullname;
    @Column
    private String user_email;
    @Column
    private String user_password;
    @Column
    private int user_role_id;

    public User() {}

    // Getters


    public int getUser_id() {
        return user_id;
    }

    public int getUser_role_id() {
        return user_role_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public String getUser_password() {
        return user_password;
    }

    // Setters

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public void setUser_role_id(int user_role_id) {
        this.user_role_id = user_role_id;
    }
}
