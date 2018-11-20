
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SocialProfileRepository;
import domain.Actor;
import domain.SocialProfile;

@Service
@Transactional
public class SocialProfileService {

	@Autowired
	private SocialProfileRepository	socialProfileRepository;

	@Autowired
	private ActorService			actorService;


	public SocialProfile create() {
		SocialProfile result;
		Actor actor;

		actor = this.actorService.getPrincipal();
		result = new SocialProfile();
		result.setActor(actor);

		return result;
	}

	public Collection<SocialProfile> findAll() {
		Collection<SocialProfile> result;

		result = this.socialProfileRepository.findAll();

		Assert.notNull(result);

		return result;

	}

	public SocialProfile findOne(final Integer socialProfileId) {
		SocialProfile result;

		Assert.notNull(socialProfileId);

		result = this.socialProfileRepository.findOne(socialProfileId);

		Assert.notNull(result);

		return result;

	}

	public SocialProfile save(final SocialProfile socialProfile) {
		SocialProfile result;

		Assert.notNull(socialProfile);
		result = this.socialProfileRepository.save(socialProfile);

		return result;

	}

	public void delete(final SocialProfile socialProfile) {

		Actor actor;

		actor = this.actorService.getPrincipal();
		Assert.isTrue(actor.getId() == socialProfile.getActor().getId());

		Assert.notNull(socialProfile);
		this.socialProfileRepository.delete(socialProfile);
	}

}
