
package services;

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

		if (mes.getMessages().contains(message))
			this.delete(message);
		else
			this.messageBoxService.saveMessageInBox(message, mes);
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

}
