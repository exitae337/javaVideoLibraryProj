package entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "actors_and_directors")
public class ActorOrDirector implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;
    @Column
    private String person_fullname;
    @Column
    private String person_role;

    public ActorOrDirector() {}

    public int getPerson_id() {
        return person_id;
    }

    public String getPerson_fullname() {
        return person_fullname;
    }

    public String getPerson_role() {
        return person_role;
    }

    // Setters

    public void setPerson_fullname(String person_fullname) {
        this.person_fullname = person_fullname;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public void setPerson_role(String person_role) {
        this.person_role = person_role;
    }
}
