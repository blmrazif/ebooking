package my.benzourry.ebooking.core.model;

import my.benzourry.ebooking.core.model.lookup.FacilityType;

import javax.persistence.*;

/**
 * Created by RazifBaital on 7/27/2015.
 */
@Entity
@Table(name = "FACILITY")
public class Facility {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME", length = 255)
    private String name;

    @Column(name = "LOCATION", length = 255)
    private String location;

    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    @Column(name = "THUMBNAIL", length = 500)
    private String thumbnail;

    @Column(name = "ADMINISTRATOR", length = 500)
    private String administrator;
    @Column(name = "ADMINISTRATOR_EMAIL", length = 500)
    private String administratorEmail;

    @JoinColumn(name = "FACILITY_TYPE", referencedColumnName = "CODE")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private FacilityType facilityType;

    @Column(name = "TIME_LIMIT")
    private Long timeLimit;

//    @OneToMany(fetch = FetchType.EAGER)
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY)

//    @JoinColumn(name = "BOOKING", referencedColumnName = "ID")
//    @ManyToOne(optional=false,fetch = FetchType.LAZY)
//    @JsonBackReference("facility-booking")
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facility", fetch = FetchType.LAZY)
//    @JsonManagedReference("facility-booking")
//    private List<Booking> bookingList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    public List<Booking> getBookingList() {
//        return bookingList;
//    }
//
//    public void setBookingList(List<Booking> bookingList) {
//        this.bookingList = bookingList;
//    }


    public FacilityType getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public String getAdministratorEmail() {
        return administratorEmail;
    }

    public void setAdministratorEmail(String administratorEmail) {
        this.administratorEmail = administratorEmail;
    }

    public Long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }
}
