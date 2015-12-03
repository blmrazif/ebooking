package my.benzourry.ebooking.core.model;

//import org.springframework.ldap.odm.annotations.Attribute;
//import org.springframework.ldap.odm.annotations.DnAttribute;
//import org.springframework.ldap.odm.annotations.Entry;
//import org.springframework.ldap.odm.annotations.Id;

//import javax.naming.Name;

/**
* Created by user on 5/19/14.
*/
//@Entry(objectClasses = { "inetOrgPerson", "organizationalPerson", "person", "top", "orcluser", "orcluserv2" }, base="cn=Users,dc=dc,dc=unimas,dc=my")
public class PersonLdap {
//    @Id
//    private Name dn;

//    @Attribute(name="cn")
//    @DnAttribute(value="cn", index=1)
    private String username;

//    @Attribute(name="uid")
    private String uid;

//    @Attribute(name = "identificationnumber")
    private String citizenNo;
    
//    @Attribute(name = "employeenumber")
    private String staffNo;

//    @Attribute(name = "givenname")
    private String fullName;

//    @Attribute(name = "mail")
    private String email;

    private String departmentCode;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCitizenNo() {
        return citizenNo;
    }

    public void setCitizenNo(String citizenNo) {
        this.citizenNo = citizenNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    /**
     * @return the staffNo
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * @param staffNo the staffNo to set
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }
}
