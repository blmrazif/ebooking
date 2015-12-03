package my.benzourry.ebooking.core.dao;

import my.benzourry.ebooking.core.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by RazifBaital on 7/28/2015.
 */
@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

    @Query("SELECT f FROM Facility f WHERE f.facilityType.code = :type")
    public List<Facility> findByFacilityType(@Param("type") String type);

    public List<Facility> findByAdministrator(@Param("administrator") String administrator);

}
