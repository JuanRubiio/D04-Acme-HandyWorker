
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SpamRepository;
import domain.Spam;

@Service
@Transactional
public class SpamService {

	@Autowired
	private SpamRepository	spamRepository;


	public Spam create() {
		Spam res;

		res = new Spam();

		return res;
	}

	public Collection<Spam> findAll() {
		Collection<Spam> result;

		result = this.spamRepository.findAll();

		Assert.notNull(result);

		return result;

	}

	public Spam findOne(final Integer spamId) {
		Spam result;

		Assert.notNull(spamId);

		result = this.spamRepository.findOne(spamId);

		Assert.notNull(result);

		return result;

	}

	public Spam save(final Spam spam) {
		Spam result;

		Assert.notNull(spam);
		result = this.spamRepository.save(spam);

		return result;

	}

	public void delete(final Spam spam) {
		Assert.notNull(spam);

		this.spamRepository.delete(spam);

	}

}
