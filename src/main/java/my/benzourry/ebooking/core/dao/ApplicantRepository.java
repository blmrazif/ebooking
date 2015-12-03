package my.benzourry.ebooking.core.dao;

import my.benzourry.ebooking.core.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by RazifBaital on 7/28/2015.
 */
@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
