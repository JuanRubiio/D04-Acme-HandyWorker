
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageBoxRepository;
import domain.Actor;
import domain.Message;
import domain.MessageBox;
import domain.Spam;

@Service
@Transactional
public class MessageBoxService {

	@Autowired
	private MessageBoxRepository	messageboxRepository;
	@Autowired
	private ActorService			actorService;
	@Autowired
	private SpamService				spamService;


	public MessageBox create() {
		final MessageBox res;

		res = new MessageBox();
		res.setActor(this.actorService.getPrincipal());
		res.setSystem(false);
		return res;
	}

	public Collection<MessageBox> findAll() {
		Collection<MessageBox> result;

		result = this.messageboxRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public MessageBox findOne(final Integer messageBoxId) {
		MessageBox result;

		Assert.notNull(messageBoxId);

		result = this.messageboxRepository.findOne(messageBoxId);

		Assert.notNull(result);

		return result;
	}

	public MessageBox save(final MessageBox messageBox) {
		MessageBox result;

		Assert.notNull(messageBox);
		result = this.messageboxRepository.save(messageBox);

		Assert.notNull(result);

		return result;
	}

	public void delete(final MessageBox messageBox) {
		Assert.notNull(messageBox);
		Assert.isTrue(messageBox.getSystem() == false);
		this.messageboxRepository.delete(messageBox);
	}

	public void addDefaultMessageBoxs(final Actor actor) {
		Assert.notNull(actor);

		MessageBox f1, f2, f3, f4;

		f1 = new MessageBox();
		f1.setName("in box");
		f1.setActor(actor);
		f1.setSystem(true);

		f2 = new MessageBox();
		f2.setName("out box");
		f2.setActor(actor);
		f2.setSystem(true);

		f3 = new MessageBox();
		f3.setName("trash box");
		f3.setActor(actor);
		f3.setSystem(true);

		f4 = new MessageBox();
		f4.setName("spam box");
		f4.setActor(actor);
		f4.setSystem(true);

		f1 = this.messageboxRepository.save(f1);
		Assert.notNull(f1);
		f2 = this.messageboxRepository.save(f2);
		Assert.notNull(f2);
		f3 = this.messageboxRepository.save(f3);
		Assert.notNull(f3);
		f4 = this.messageboxRepository.save(f4);
		Assert.notNull(f4);

	}

	public Collection<MessageBox> findMessageBoxsByPrincipal() {
		Collection<MessageBox> result;
		Actor actor;

		actor = this.actorService.getPrincipal();
		Assert.notNull(actor, "EL actor es nulo");
		result = this.messageboxRepository.findMessageBoxsByUserAccount(actor.getUserAccount().getId());
		Assert.notNull(result, "La coleccion de MessageBoxs es nula");

		this.checkPrincipalActorMessageBoxs(result);

		return result;
	}

	public MessageBox findSystemMessageBox(final String nameMessageBox) {
		Assert.notNull(nameMessageBox, "La carpeta es nula");

		Actor actor;
		MessageBox MessageBox;

		actor = this.actorService.getPrincipal();
		MessageBox = this.messageboxRepository.findSystemMessageBox(nameMessageBox, actor.getId());
		Assert.notNull(MessageBox, "La carpeta es nula");

		return MessageBox;
	}

	private MessageBox findSystemMessageBoxByActor(final String nameMessageBox, final int actorId) {
		Assert.notNull(nameMessageBox);
		MessageBox MessageBox;

		MessageBox = this.messageboxRepository.findSystemMessageBox(nameMessageBox, actorId);
		Assert.notNull(MessageBox);
		return MessageBox;
	}

	public void getMessageBoxAndCheckSpam(final Message message, final Actor recipient) {
		Assert.notNull(message);
		MessageBox messageBox;
		Collection<Spam> spamList;
		final Message mes = new Message();

		spamList = this.spamService.findAll();

		for (final Spam sp : spamList)
			if (message.getBody().toLowerCase().contains(sp.getSpamWords().toLowerCase()) || message.getSubject().toLowerCase().contains(sp.getSpamWords().toLowerCase())) {
				message.setSpam(true);
				break;
			}

		if (message.getSpam()) {
			messageBox = this.findSystemMessageBoxByActor("spam box", recipient.getId());
			mes.setBody(message.getBody());
			mes.setDate(message.getDate());
			mes.setPriority(message.getPriority());
			mes.setRecipient(message.getRecipient());
			mes.setSender(message.getSender());
			mes.setSpam(message.getSpam());
			mes.setSubject(message.getSubject());
			if (message.getTags() != null)
				mes.setTags(message.getTags());
			this.saveMessageInBox(mes, messageBox);
		} else {
			messageBox = this.findSystemMessageBoxByActor("in box", recipient.getId());
			mes.setBody(message.getBody());
			mes.setDate(message.getDate());
			mes.setPriority(message.getPriority());
			mes.setRecipient(message.getRecipient());
			mes.setSender(message.getSender());
			mes.setSpam(message.getSpam());
			mes.setSubject(message.getSubject());
			if (message.getTags() != null)
				mes.setTags(message.getTags());
			this.saveMessageInBox(mes, messageBox);
		}
	}

	private void checkPrincipalActorMessageBoxs(final Collection<MessageBox> MessageBoxs) {
		Assert.notNull(MessageBoxs);

		for (final MessageBox f : MessageBoxs)
			this.checkPrincipalActor(f);
	}

	public void checkPrincipalActor(final MessageBox MessageBox) {
		Assert.notNull(MessageBox);

		Actor actor;

		actor = this.actorService.getPrincipal();

		Assert.isTrue(actor.getId() == MessageBox.getActor().getId());
	}

	public List<MessageBox> getMessageBoxsByActor(final int actorId) {
		Assert.notNull(actorId);
		final List<MessageBox> result = (List<MessageBox>) this.messageboxRepository.getMessageBoxsByActor(actorId);

		return result;
	}

	public MessageBox saveMessageInBox(final Message result, final MessageBox messageBox) {

		Assert.notNull(result);
		Assert.notNull(messageBox);
		final Collection<Message> mes = messageBox.getMessages();
		mes.add(result);
		messageBox.setMessages(mes);

		this.messageboxRepository.save(messageBox);
		return messageBox;
	}

	public Collection<MessageBox> getMessageBoxesByMessageId(final int id) {
		return this.getMessageBoxesByMessageId(id);
	}

}
