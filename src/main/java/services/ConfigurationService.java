
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ConfigurationRepository;
import domain.Configuration;

@Service
@Transactional
public class ConfigurationService {

	@Autowired
	private ConfigurationRepository	configurationRepository;

	@Autowired
	private ActorService			actorService;


	public Collection<Configuration> findAll() {
		Collection<Configuration> result;

		result = this.configurationRepository.findAll();

		Assert.notNull(result);

		return result;

	}

	public Configuration findOne(final Integer configurationId) {
		Configuration result;

		Assert.notNull(configurationId);

		result = this.configurationRepository.findOne(configurationId);

		Assert.notNull(result);

		return result;

	}

	public Configuration save(final Configuration configuration) {
		Configuration result;

		Assert.notNull(configuration);

		result = this.configurationRepository.save(configuration);

		Assert.notNull(result);

		return result;
	}

}
