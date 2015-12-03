package my.benzourry.ebooking.core.model;

import javax.persistence.*;

/**
 * Created by RazifBaital on 7/27/2015.
 */
@Entity
@Table(name = "APPLICANT")
public class Applicant {

    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

//    @Column(name = "fullname")
    @Column(name = "NAME", length = 255)
    private String name;

    @Column(name = "EMAIL", length = 255)
    private String email;

    @Column(name = "PHONE", length = 255)
    private String phone;

    @Column(name = "TYPE")
    private Integer type; // applicant type

    public Applicant(){

    }

    public Applicant(Long id){
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
