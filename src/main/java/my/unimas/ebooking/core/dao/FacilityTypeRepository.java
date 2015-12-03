package my.unimas.ebooking.core.dao;

import my.unimas.ebooking.core.model.lookup.FacilityType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by MohdRazif on 8/4/2015.
 */
public interface FacilityTypeRepository extends JpaRepository<FacilityType, String> {
}
