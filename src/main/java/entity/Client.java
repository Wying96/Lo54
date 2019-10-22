/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wuying
 */
@Entity
@Table(name = "CLIENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.id = :id"),
    @NamedQuery(name = "Client.findByLastname", query = "SELECT c FROM Client c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "Client.findByFirstname", query = "SELECT c FROM Client c WHERE c.firstname = :firstname"),
    @NamedQuery(name = "Client.findByAddress", query = "SELECT c FROM Client c WHERE c.address = :address"),
    @NamedQuery(name = "Client.findByPhone", query = "SELECT c FROM Client c WHERE c.phone = :phone"),
    @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LASTNAME")
    private Character lastname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FIRSTNAME")
    private Character firstname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADDRESS")
    private Character address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PHONE")
    private Character phone;
    @Column(name = "EMAIL")
    private Character email;
    @JoinColumn(name = "COURSE_SESSION_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CourseSession courseSessionId;

    public Client() {
    }

    public Client(Integer id) {
        this.id = id;
    }

    public Client(Integer id, Character lastname, Character firstname, Character address, Character phone) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getLastname() {
        return lastname;
    }

    public void setLastname(Character lastname) {
        this.lastname = lastname;
    }

    public Character getFirstname() {
        return firstname;
    }

    public void setFirstname(Character firstname) {
        this.firstname = firstname;
    }

    public Character getAddress() {
        return address;
    }

    public void setAddress(Character address) {
        this.address = address;
    }

    public Character getPhone() {
        return phone;
    }

    public void setPhone(Character phone) {
        this.phone = phone;
    }

    public Character getEmail() {
        return email;
    }

    public void setEmail(Character email) {
        this.email = email;
    }

    public CourseSession getCourseSessionId() {
        return courseSessionId;
    }

    public void setCourseSessionId(CourseSession courseSessionId) {
        this.courseSessionId = courseSessionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Client[ id=" + id + " ]";
    }
    
}
