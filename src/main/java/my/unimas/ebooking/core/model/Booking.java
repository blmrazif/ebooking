package my.unimas.ebooking.core.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by RazifBaital on 7/27/2015.
 */
@Entity
@Table(name = "BOOKING")
public class Booking {

    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION", length = 50)
    private String description;

    @Column(name = "START_DATE")
    @JsonSerialize(using = DateTimeSerializer.class)
//    @Temporal(TemporalType.TIMESTAMP)
    private DateTime startDate;

    @Column(name = "END_DATE")
    @JsonSerialize(using = DateTimeSerializer.class)
//    @Temporal(TemporalType.TIMESTAMP)
    private DateTime endDate;

    @Column(name = "APPLICATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicationDate;

    @Column(name = "APPROVAL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvalDate;

    @Column(name = "STATUS")
    private Integer status;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "FACILITY", referencedColumnName = "ID")
//    @ManyToOne(optional = false, fetch = FetchType.EAGER)


    @JoinColumn(name = "FACILITY", referencedColumnName = "ID")
    @ManyToOne(optional=false,fetch = FetchType.EAGER)
//    @JsonBackReference("facility-booking")
    private Facility facility;

    @JoinColumn(name = "APPLICANT", referencedColumnName = "ID")
    @OneToOne(cascade = CascadeType.ALL)
    private Applicant applicant;

    public static final Integer PENDING = 0;
    public static final Integer REJECTED = 1;
    public static final Integer APPROVED = 2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
}
