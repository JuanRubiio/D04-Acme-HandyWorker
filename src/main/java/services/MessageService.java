
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Administrator;
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


	public Message create() {
		Message result;
		Actor sender;

		sender = this.actorService.getPrincipal();

		result = new Message();
		result.setSender(sender);
		result.setDate(new Date(System.currentTimeMillis()));

		return result;
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
		MessageBox messageBox;

		messageBox = this.messageBoxService.findSystemMessageBox("out box");

		Assert.notNull(message);

		result = this.messageRepository.save(message);
		this.messageBoxService.saveMessageInBox(result, messageBox);

		return result;
	}

	public void delete(final Message message) {
		Assert.notNull(message);

		this.messageRepository.delete(message);
	}

	public void moveMessage(final Message message, final Integer messageBoxId) {
		final Actor actor = this.actorService.getPrincipal();
		final MessageBox messageBox = this.messageBoxService.findOne(messageBoxId);
		Assert.notNull(messageBox);
		Assert.isTrue(messageBox.getActor().getId() == actor.getId());
		final Collection<Message> mes = this.messageRepository.findMessagesByMessageBoxesId(messageBoxId);
		Assert.isTrue(!mes.contains(message));
		this.messageBoxService.saveMessageInBox(message, messageBox);
	}

	public void delete(final Integer messageId) {
		Assert.isTrue(messageId != 0 && messageId != null);
		Message message;
		message = this.messageRepository.findOne(messageId);
		Assert.isTrue(message != null);

		Assert.isTrue(this.checkPrincipalActor(message));
		final MessageBox mes = this.messageBoxService.findSystemMessageBox("trash box");
		Assert.notNull(mes);
		final Collection<MessageBox> boxes = this.messageBoxService.getMessageBoxesByMessageId(message.getId());

		if (!mes.getMessages().contains(message))
			this.messageBoxService.saveMessageInBox(message, mes);
		else if (boxes.isEmpty())
			this.delete(message);
		else
			for (final MessageBox b : boxes) {
				final Collection<Message> m = b.getMessages();
				m.remove(message);
				b.setMessages(m);
				this.messageBoxService.save(b);
			}

	}
	public void broadcast(final Message message) {
		Assert.notNull(message);
		this.save(message);
		final Administrator administrator = (Administrator) this.actorService.getPrincipal();
		final String subject = message.getSubject();
		final String body = message.getBody();
		final String priority = message.getPriority();
		final Boolean spam = message.getSpam();

		final Collection<Actor> actors = this.actorService.findAll();
		final Message m = this.create();
		MessageBox mesbox = new MessageBox();
		m.setSender(administrator);
		m.setSubject(subject);
		m.setBody(body);
		m.setPriority(priority);
		m.setSpam(spam);
		for (final Actor a : actors) {

			final Collection<MessageBox> messageBoxes = this.messageBoxService.getMessageBoxsByActor(a.getId());

			for (final MessageBox met : messageBoxes)
				if (met.equals("in box"))
					mesbox = met;

			m.setRecipient(a);
			this.messageBoxService.saveMessageInBox(m, mesbox);
		}
	}

	public Boolean checkPrincipalActor(final Message message) {
		Boolean res = false;
		Assert.notNull(message);

		Actor actor;

		actor = this.actorService.getPrincipal();

		final Collection<MessageBox> messageboxes = this.messageBoxService.getMessageBoxsByActor(actor.getId());
		for (final MessageBox mes : messageboxes)
			if (mes.getMessages().contains(message))
				res = true;
		return res;
	}

	public Collection<Message> findMessagesByActorId(final int id) {
		return this.messageRepository.findMessagesByActorId(id);
	}

}
