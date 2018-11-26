
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SectionRepository;
import domain.Section;

@Service
@Transactional
public class SectionService {

	//Managed repo
	@Autowired
	private SectionRepository	sectionRepository;


	//Supporting services
	public Section create() {
		final Section res;
		res = new Section();
		//set
		return res;

	}

	public Section findOne(final Integer sectionId) {
		final Section res;
		Assert.notNull(sectionId);
		res = this.sectionRepository.findOne(sectionId);
		Assert.notNull(res);

		return res;
	}

	public Collection<Section> findAll() {
		final Collection<Section> res;
		res = this.sectionRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Section save(final Section section) {
		final Section res;
		Assert.notNull(section);
		res = this.sectionRepository.save(section);
		Assert.notNull(res);

		return res;
	}

	public void delete(final Section section) {
		Assert.notNull(section);
		this.sectionRepository.delete(section);
	}
}
