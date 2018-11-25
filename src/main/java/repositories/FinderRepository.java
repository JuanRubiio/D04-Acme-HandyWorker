
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.FixUpTask;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select fut from FixUpTask fut where (fut.minPrice>?1 and fut.maxPrice < ?2 and fut.minDate > ?3 and fut.maxDate < ?4")
	Collection<FixUpTask> findFixUpTasksNoKey(Double minPrice, Double maxPrice, Date minDate, Date maxDate);

	@Query("select fut from FixUpTask fut where (fut.ticker like %?1% or fut.description like %?1% or fut.address like %?1% or fut.minPrice>?2 and fut.maxPrice < ?3 and fut.minDate > ?4 and fut.maxDate < ?5")
	Collection<FixUpTask> findFixUpTasks(String s, Double minPrice, Double maxPrice, Date minDate, Date maxDate);
}
