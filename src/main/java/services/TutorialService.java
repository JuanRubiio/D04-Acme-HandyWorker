
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	//Managed repo
	@Autowired
	private TutorialRepository	tutorialRepository;


	//Supporting services
	public Tutorial create() {
		final Tutorial res;
		res = new Tutorial();
		//set handyworker
		return res;
	}

	public Tutorial findOne(final Integer tutorialId) {
		final Tutorial res;
		Assert.notNull(tutorialId);
		res = this.tutorialRepository.findOne(tutorialId);
		Assert.notNull(res);

		return res;

	}

	public Collection<Tutorial> findAll() {
		final Collection<Tutorial> res;
		res = this.tutorialRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Tutorial save(final Tutorial tutorial) {
		final Tutorial res;
		Assert.notNull(tutorial);
		res = this.tutorialRepository.save(tutorial);
		Assert.notNull(res);

		return res;
	}

	public void delete(final Tutorial tutorial) {
		Assert.notNull(tutorial);
		this.tutorialRepository.delete(tutorial);

	}

}
