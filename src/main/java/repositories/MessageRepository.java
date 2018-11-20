
package repositories;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select m from Message m where m.messageBoxes.id=?1 order by m.priority desc")
	Collection<Message> findMessagesByMessageBoxesId(int messageBoxesId);

	@Query("select message from Message message where message.date = ?1 and message.sender = ?2")
	Collection<Message> findMessageBySendMomentAndSender(Date date, Actor actor);

}
