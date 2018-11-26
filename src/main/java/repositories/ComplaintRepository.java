
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

	@Query("select c from Complaint c where c.fixUpTasks.customer.id = ?1")
	Collection<Complaint> getListOfComplaintsPerCustomer(Integer id);

	@Query("select c from Complaint where c.fixUpTasks.applications.handyWorker.id =?1 and c.fixUpTasks.applications.status='ACCEPTED'")
	Collection<Complaint> getListOfComplaintsPerHandyWorker(Integer id);

}
