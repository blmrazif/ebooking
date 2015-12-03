package my.unimas.ebooking.core.controller;

//import my.unimas.ebooking.core.dao.BookingRepository;
//import my.unimas.ebooking.core.model.Booking;
//import my.unimas.ebooking.core.model.Facility;
//import my.unimas.ebooking.core.service.BookingService;
import my.unimas.ebooking.core.model.PersonLdap;
import my.unimas.ebooking.core.service.FacilityService;
import my.unimas.ebooking.core.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
* Created by RazifBaital on 7/27/2015.
*/
@Controller
public class HomeController {

    @Autowired
    FacilityService facilityService;

    @Autowired
    PersonService personService;

    @RequestMapping("/")
    public String getHome(Principal principal,ModelMap model){
        String template = "public";
        if (principal!=null){
            PersonLdap personLdap = personService.findPersonLdapByUsername(principal.getName());
            model.addAttribute("activeProfile", personLdap);
            template = "admin";
        }else{
            template = "public";
        }
        return template;
    }

//
//    @RequestMapping(value = "facility-add", method = RequestMethod.GET)
//    public String viewFacilityForm(ModelMap model){
//        model.addAttribute("facility", new Facility());
//        return "facility/facility-add";
//    }
//
//    @RequestMapping(value="facility-add", method = RequestMethod.POST)
//    public String saveFacility(@ModelAttribute Facility facility, ModelMap model){
//        Facility newFacility = facilityService.saveFacility(facility);
//        List<Facility> facilityList = facilityService.findFacility();
//        model.addAttribute("facilityList", facilityList);
//        model.addAttribute("facility", newFacility);
//        return "facility/facility-add";
//    }

    @RequestMapping(value = "login")
    public String printLogin() {
        return "login";
    }

    @RequestMapping({
            "/facility/**",
            "/booking/**",
            "/category/**",
            "/admin/**",
            "/contact/**",
            "/about/**",
            "/index",
            "/404",
            "/profile/**",
            "/lookup/**",
            "/list/**",
            "/public/**",
            "/about"
    })
    public String index() {
        return "forward:/";
    }



}
