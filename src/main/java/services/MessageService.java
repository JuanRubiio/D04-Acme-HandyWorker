
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Message;
import domain.MessageBox;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository	messageRepository;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private MessageBoxService	messageBoxService;
	@Autowired
	private SpamService			spamService;


	public Message create() {
		Message result;
		Actor sender;
		MessageBox messageBox;

		sender = this.actorService.getPrincipal();
		messageBox = this.messageBoxService.findSystemMessageBox("out box");

		result = new Message();
		result.setSender(sender);
		result.setDate(new Date(System.currentTimeMillis()));
		final Collection<MessageBox> mesBox = new ArrayList<MessageBox>();
		mesBox.add(messageBox);
		result.setMessageBoxes(mesBox);

		return result;
	}

	public void spam(final Message message) {
		Assert.notNull(message);
		final Actor a = this.actorService.getPrincipal();
		final Collection<MessageBox> f = this.messageBoxService.getMessageBoxsByActor(a.getId());
		Assert.notNull(f);

		MessageBox spamBox = new MessageBox();
		for (final MessageBox m : f)
			if (m.getName().equals("spam box"))
				spamBox = m;
		Assert.notNull(spamBox);
		final Collection<MessageBox> mesBox = new ArrayList<MessageBox>();
		mesBox.add(spamBox);
		final Message mess = this.messageRepository.findOne(message.getId());
		mess.setMessageBoxes(mesBox);
		this.messageRepository.save(mess);

	}

	public Collection<Message> findAll() {
		Collection<Message> result;

		result = this.messageRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public Message findOne(final Integer messageId) {
		Message result;

		Assert.notNull(messageId);

		result = this.messageRepository.findOne(messageId);

		Assert.notNull(result);
		return result;
	}

	public Message save(final Message message) {
		Message result;

		Assert.notNull(message);

		result = this.messageRepository.save(message);

		return result;
	}

	//TODO Seguramente haya que modificar las relaciones entre mensaje y box para facilitar la mensajeria
	public Message saveToSend(final Message message) {
		Assert.notNull(message);
		Message result;

		result = this.save(message);
		//		Message copy;
		//		Actor receiver;
		//		MessageBox messageBox;
		//
		//		copy = result;
		//		receiver = message.getRecipient();
		//		messageBox = this.messageBoxService.getMessageBoxAndCheckSpam(copy, receiver);
		//
		//		
		//		
		//		copy.setMessageBoxes(me);
		//		this.save(copy);

		return result;

	}

	public void delete(final Message message) {
		Assert.notNull(message);

		this.messageRepository.delete(message);
	}

	//TODO falta cambiar las relaciones
	public void moveMessage(final Message message, final Integer messageBoxId) {
		final Actor actor = this.actorService.getPrincipal();
		final MessageBox messageBox = this.messageBoxService.findOne(messageBoxId);
		Assert.isTrue(messageBox.getActor().getId() == actor.getId());
		message.setFolder(messageBox);
		messageBox.setMessages(messageBox.getMessages().add(message));
	}

	public void delete(final Integer messageId) {
		Assert.isTrue(messageId != 0 && messageId != null);
		Message message;
		message = this.messageRepository.findOne(messageId);
		Assert.isTrue(message != null);

		this.checkPrincipalActor(message);
		final String folderName = message.getFolder().getName();
		if (folderName.equals("trash box") && message.getFolder().getSystem())
			this.delete(message);
		else {
			final MessageBox folder = this.messageBoxService.findSystemMessageBox("trash box");
			message.setFolder(folder);
			this.save(message);
		}
	}

	public void checkPrincipalActor(final Message message) {
		Assert.notNull(message);

		Actor actor;

		actor = this.actorService.getPrincipal();

		Assert.isTrue(actor.getId() == message.getFolder().getActor().getId());

	}

	public void move(final Integer messageId, final Integer folderId) {
		MessageBox folder;
		Message message;

		folder = this.messageBoxService.findOne(folderId);
		this.messageBoxService.checkPrincipalActor(folder);
		message = this.findOne(messageId);

		message.setFolder(folder);

		this.save(message);
	}

}
