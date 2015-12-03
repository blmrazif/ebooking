package my.unimas.ebooking.core.model.lookup;

import javax.persistence.*;

/**
 * Created by RazifBaital on 7/31/2015.
 */
@Entity
@Table(name = "FACILITY_TYPE")
public class FacilityType {

    @Id
    @Basic(optional = false)
    @Column(name="CODE",nullable = false, length = 25)
    private String code;

    @Column(name = "NAME", length = 255)
    private String name;

    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    @Column(name = "THUMBNAIL", length = 500)
    private String thumbnail;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
