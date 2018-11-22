
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query("select n from Note n where n.report.referee.id=?1")
	Collection<Note> getNotesByReferee(Integer refereeId);

}
