
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lowagie.text.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

}
