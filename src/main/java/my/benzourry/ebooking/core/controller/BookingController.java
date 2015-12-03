package my.benzourry.ebooking.core.controller;

import my.benzourry.ebooking.core.model.Booking;
import my.benzourry.ebooking.core.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Created by RazifBaital on 7/28/2015.
 */
@RestController
@RequestMapping("api/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Booking save(@RequestBody Booking booking){
        Booking b = bookingService.save(booking);
        return b;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Booking getBooking(@PathVariable("id") Long id){
        Booking b = bookingService.findById(id);
        return b;
    }

    @RequestMapping(value="list", method = RequestMethod.GET)
    public List<Booking> getBookingList(){
        List<Booking> bookingList = bookingService.findAll();
        return bookingList;
    }

    @RequestMapping(value="user/list", method = RequestMethod.GET)
    public List<Booking> getBookingUserList(Principal principal,
                                            @RequestParam(value = "status", defaultValue = "0,1,2") Integer[] status){
        List<Booking> bookingList = bookingService.findByFacilityAdmin(principal.getName(), status);
        return bookingList;
    }

    @RequestMapping(value="", method=RequestMethod.DELETE)
    public void deleteBooking(@RequestParam("id") Long id){
        bookingService.delete(id);
    }

    @RequestMapping(value="{id}/approve", method = RequestMethod.POST)
    public Booking approveBooking(@PathVariable("id") Long id,
                                  @RequestParam("status") Integer status){
        return bookingService.approve(id, status);
    }

    @RequestMapping(value="list-by-facility/{id}", method = RequestMethod.GET)
    public List<Booking> listByFacility(@PathVariable("id") Long facilityId,
                                        @RequestParam("status") Integer[] status){
        return bookingService.findByFacilityId(facilityId, status);
    }

    @RequestMapping(value="list-calendar/{id}", method = RequestMethod.GET)
    public List<Map> listforCalendar(@PathVariable("id") Long facilityId,
                                     @RequestParam("status") Integer[] status){
        return bookingService.findCalendarListByFacilityId(facilityId,status);
    }


}
