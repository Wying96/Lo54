/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wuying
 */
@Entity
@Table(name = "COURSE_SESSION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseSession.findAll", query = "SELECT c FROM CourseSession c"),
    @NamedQuery(name = "CourseSession.findById", query = "SELECT c FROM CourseSession c WHERE c.id = :id"),
    @NamedQuery(name = "CourseSession.findByStartDate", query = "SELECT c FROM CourseSession c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "CourseSession.findByEndDate", query = "SELECT c FROM CourseSession c WHERE c.endDate = :endDate"),
    @NamedQuery(name = "CourseSession.findByMaxNumber", query = "SELECT c FROM CourseSession c WHERE c.maxNumber = :maxNumber")})
public class CourseSession implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "MAX_NUMBER")
    private Integer maxNumber;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Location locationId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseSessionId")
    private Collection<Client> clientCollection;

    public CourseSession() {
    }

    public CourseSession(Integer id) {
        this.id = id;
    }

    public CourseSession(Integer id, Date startDate, Date endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(Integer maxNumber) {
        this.maxNumber = maxNumber;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    @XmlTransient
    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
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
        if (!(object instanceof CourseSession)) {
            return false;
        }
        CourseSession other = (CourseSession) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CourseSession[ id=" + id + " ]";
    }
    
}