/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.unimas.ebooking.core.service;


import my.unimas.ebooking.core.dao.PersonLdapDAO;
import my.unimas.ebooking.core.model.PersonLdap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author MohdRazif
 */

@Service("personService")
public class PersonService {
    @Autowired
    private PersonLdapDAO personLdapDAO;


    /**
     * This service method will retrieve single data of PersonLdap from the ldap directory based on the given username
     * @param username
     * @return
     */
    public PersonLdap findPersonLdapByUsername(String username){
        return personLdapDAO.findByUsername(username);
    }

    /**
     * This service will retrieve the list of PersonLdap from the ldap directory using the name given
     * @param name The name that is to be search
     * @return The corresponsing PersonLdap list
     */
    public List<PersonLdap> listPersonLdapByFullName(String name){ 
        return personLdapDAO.listByFullName(name);
    }




}
