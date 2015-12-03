package my.benzourry.ebooking.core.dao;

import my.benzourry.ebooking.core.model.lookup.FacilityType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by MohdRazif on 8/4/2015.
 */
public interface FacilityTypeRepository extends JpaRepository<FacilityType, String> {
}
