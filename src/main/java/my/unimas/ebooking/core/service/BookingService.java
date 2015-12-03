package my.unimas.ebooking.core.service;

import my.unimas.ebooking.core.dao.BookingRepository;
import my.unimas.ebooking.core.model.Booking;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by RazifBaital on 7/28/2015.
 */
@Service("bookingService")
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    MailService mailService;


    public Booking save(Booking booking){
        Date today = new Date();
        booking.setStatus(Booking.PENDING);
        booking.setApplicationDate(today);

        Booking b = bookingRepository.save(booking);

        mailService.sendMail("irisadmin@unimas.my",booking.getApplicant().getEmail(),"Your request awaiting approval","Thank you. Please wait for the approval");
        mailService.sendMail("irisadmin@unimas.my",booking.getFacility().getAdministrator()+"@unimas.my" ,"eBooking: Pending approval for #"+booking.getId(),"Hi, pending approval for "+ booking.getApplicant().getName()+ " to use "+ booking.getFacility().getName() + " from "+ booking.getStartDate() + " until " + booking.getEndDate());

        return b;
//        mailService
    }

    public void delete(Long id){
        bookingRepository.delete(id);
    }

    public Booking findById(Long id){
        return bookingRepository.findOne(id);
    }

    public List<Booking> findByApplicantEmail(String email){
        return bookingRepository.findByApplicantEmail(email);
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

     public List<Booking> findByFacilityId(Long facilityId, Integer[] status){
         List<Integer> statusesList = Arrays.asList(status);
        return bookingRepository.findByFacilityId(facilityId, statusesList);
    }

     public List<Booking> findByFacilityAdmin(String admin, Integer[] status){
         List<Integer> statusesList = Arrays.asList(status);
        return bookingRepository.findByFacilityAdmin(admin, statusesList);
     }

     public List<Map> findCalendarListByFacilityId(Long facilityId, Integer[] status){
         List<Integer> statusList = Arrays.asList(status);

         List<Booking> bookingList = bookingRepository.findByFacilityId(facilityId,statusList);

         List<Map> items = new ArrayList<>();
         for (Booking b: bookingList){
             Map<String, Object> row = new HashMap<>();
             row.put("title", b.getApplicant().getName());
             DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");
             row.put("start", b.getStartDate().toString(formatter));
             row.put("end", b.getEndDate().toString(formatter));
             items.add(row);
         }
         return items;

    }


    public Booking approve(Long bookingId, Integer status){
        Booking booking = bookingRepository.findOne(bookingId);
        booking.setStatus(status);

        String [] statusTxt = {"PENDING","REJECTED","APPROVED"};

        mailService.sendMail("irisadmin@unimas.my",booking.getApplicant().getEmail(),"Your request to use "+ booking.getFacility().getName(),"Hi, Your request to use "+ booking.getFacility().getName() + " has been " + statusTxt[status] );
        mailService.sendMail("irisadmin@unimas.my",booking.getFacility().getAdministrator()+"@unimas.my" ,"eBooking","");

        return bookingRepository.save(booking);
    }

}
