
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

	@Query("select b.messages from MessageBox b where b.id = ?1")
	Collection<Message> findMessagesByMessageBoxesId(int messageBoxesId);

	@Query("select message from Message message where message.date = ?1 and message.sender = ?2")
	Collection<Message> findMessageBySendMomentAndSender(Date date, Actor actor);

	@Query("select mes from Message mes, MessageBox box join box.messages m where m.id = mes.id and box.actor.id = ?1 ")
	Collection<Message> findMessagesByActorId(int id);

}
