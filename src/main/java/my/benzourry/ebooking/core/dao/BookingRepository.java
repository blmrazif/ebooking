package my.benzourry.ebooking.core.dao;

import my.benzourry.ebooking.core.model.Booking;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by RazifBaital on 7/27/2015.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b JOIN b.applicant a WHERE a.email = :email")
    public List<Booking> findByApplicantEmail(@Param("email") String email);

    @Query("SELECT b FROM Booking b JOIN b.facility f WHERE f.id = :facilityId AND b.status in :status")
    public List<Booking> findByFacilityId(@Param("facilityId") Long facilityId, @Param("status") List<Integer> statuses);

    @Query("SELECT b FROM Booking b JOIN b.facility f WHERE f.administrator = :admin AND b.status in :status")
    public List<Booking> findByFacilityAdmin(@Param("admin") String admin, @Param("status") List<Integer> statuses);

//    @Query("SELECT b FROM Booking b JOIN b.facility f WHERE f.id=:facilityId AND (b.startDate BETWEEN :startDate AND :endDate" +
//            " OR b.endDate BETWEEN :startDate AND :endDate)")
    @Query("SELECT b FROM Booking b JOIN b.facility f WHERE f.id=:facilityId AND (b.startDate <= :endDate AND :startDate <= b.endDate) AND b.status IN (0,2)")
    public List<Booking> findByFacilityIdAndStartDateBetween(@Param("facilityId") Long facilityId,
                                                             @Param("startDate") DateTime startDate,
                                                             @Param("endDate") DateTime endDate);

    public List<Booking> findByStatus(Integer status);

}
