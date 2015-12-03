package my.benzourry.ebooking.core.service;

import my.benzourry.ebooking.core.dao.BookingRepository;
import my.benzourry.ebooking.core.dao.FacilityRepository;
import my.benzourry.ebooking.core.model.Facility;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by RazifBaital on 7/30/2015.
 */
@Service("facilityService")
public class FacilityService {

    @Autowired
    FacilityRepository facilityRepository;
    @Autowired
    BookingRepository bookingRepository;

    public Facility save(Facility facility){
        return facilityRepository.save(facility);
    }

    public List<Facility> findFacility(){
        return facilityRepository.findAll();
    }

    public List<Facility> findByAdministrator(String admin){
        return facilityRepository.findByAdministrator(admin);
    }
    public List<Facility> findByFacilityType(String type){
        return facilityRepository.findByFacilityType(type);
    }

    public Facility getFacility(Long id){
        return facilityRepository.findOne(id);
    }

    public Facility saveFacility(Facility facility){
        return facilityRepository.save(facility);
    }

    public boolean check(Long facilityId, DateTime startDate, DateTime endDate){
        return bookingRepository.findByFacilityIdAndStartDateBetween(facilityId,startDate, endDate).size()==0;
    }


}
