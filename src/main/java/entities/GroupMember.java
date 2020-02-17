package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name = "GroupMember.deleteAllRows", query = "DELETE from GroupMember"),
    @NamedQuery(name = "GroupMember.getAll", query = "SELECT m FROM GroupMember m"),
    @NamedQuery(name = "GroupMember.getByName", query = "SELECT m FROM GroupMember m WHERE m.name LIKE :name")
})
public class GroupMember implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String studentId;
    private String color;

    @Temporal(TemporalType.DATE)
    private Date created;

    public GroupMember() {
    }

    public GroupMember(String name, String studentId, String color) {
        this.name = name;
        this.studentId = studentId;
        this.color = color;
        this.created = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getCreated() {
        return created;
    }
    
}
