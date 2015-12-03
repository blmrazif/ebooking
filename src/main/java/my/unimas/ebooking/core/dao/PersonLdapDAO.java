package my.unimas.ebooking.core.dao;


import my.unimas.ebooking.core.model.PersonLdap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.filter.LikeFilter;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

//import my.unimas.iris.model.Account;

/**
* Created by user on 5/20/14.
*/
@Repository("personLdapDAO")
public class PersonLdapDAO {
    @Autowired
    private LdapTemplate ldapTemplate;


    public PersonLdap findByUsername(String username) {

        try {
            Filter filter = new EqualsFilter("cn", username);
            List<PersonLdap> users = ldapTemplate.search("cn=Users,dc=dc,dc=unimas,dc=my",
                    filter.encode(), new PersonAttributesMapper());

            if (!users.isEmpty()) {
                return users.get(0);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    public List<PersonLdap> findByGroup(String group) {

        try {
            Filter filter = new EqualsFilter("cn", group);
            List<PersonLdap> users = ldapTemplate.search("cn=Users,dc=dc,dc=unimas,dc=my",
                    filter.encode(), new PersonAttributesMapper());

            if (!users.isEmpty()) {
                return users;
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }



    public List<PersonLdap> listByFullName(String name) {

        try {
            Filter filter = new LikeFilter("givenname", "*"+name+"*");
            List<PersonLdap> users = ldapTemplate.search("cn=Users,dc=dc,dc=unimas,dc=my",
                    filter.encode(), new PersonAttributesMapper());

            if (!users.isEmpty()) {
                return users;
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    

    public PersonLdap findByCitizenNo(String citizenNo) {

        try {
            Filter filter = new EqualsFilter("employeenumber", citizenNo);
            List<PersonLdap> users = ldapTemplate.search("cn=Users,dc=dc,dc=unimas,dc=my",
                    filter.encode(), new PersonAttributesMapper());
            if (!users.isEmpty()) {
                return users.get(0);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }


    private static class PersonAttributesMapper implements AttributesMapper {

        public Object mapFromAttributes(Attributes attrs)
                throws NamingException {
            PersonLdap personLdap = new PersonLdap();
           /* for (NamingEnumeration attrEnum = attrs.getAll(); attrEnum.hasMore();) {
                Attribute attr = (Attribute)attrEnum.next();
                System.out.println("IDs:"+attr.getID()+";");
            }*/
            if (attrs.get("uid") != null) {
                personLdap.setUsername((String) attrs.get("uid").get());
            }
            if (attrs.get("sn") != null) {
                personLdap.setFullName((String) attrs.get("sn").get());
            }
            if (attrs.get("givenname") != null) {
                personLdap.setFullName((String) attrs.get("givenname").get());
            }
            if (attrs.get("mail") != null) {
                personLdap.setEmail((String) attrs.get("mail").get());
            }
            if (attrs.get("employeenumber") != null) {
                personLdap.setStaffNo((String) attrs.get("employeenumber").get());
            }
            if (attrs.get("identificationnumber") != null) {
                personLdap.setCitizenNo((String) attrs.get("identificationnumber").get());
            }
            if (attrs.get("departmentnumber") != null) {
                personLdap.setDepartmentCode((String) attrs.get("departmentnumber").get());
            }
            return personLdap;
        }
    }
}
