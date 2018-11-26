
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MessageBox;

@Repository
public interface MessageBoxRepository extends JpaRepository<MessageBox, Integer> {

	//Folders by user account
	@Query("select f from MessageBox f where f.actor.userAccount.id=?1")
	Collection<MessageBox> findMessageBoxsByUserAccount(int userAccountId);

	@Query("select f from MessageBox f where f.name = ?1 and f.actor.id = ?2 and f.system is true")
	MessageBox findSystemMessageBox(String nameBox, int actorId);

	@Query("select f from MessageBox f  where f.actor.id =?1")
	Collection<MessageBox> getMessageBoxsByActor(int id);

	@Query("select b from MessageBox b join b.messages mes where mes.id = ?1")
	Collection<MessageBox> getMessageBoxesByMessageId(int id);

}
