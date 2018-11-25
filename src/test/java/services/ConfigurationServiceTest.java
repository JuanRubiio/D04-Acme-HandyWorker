
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Configuration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ConfigurationServiceTest extends AbstractTest {

	@Autowired
	private ConfigurationService	configurationService;


	@Test
	public void testSaveConfiguration() {
		Configuration configlate = new Configuration();
		Configuration confignew = new Configuration();

		final String message = "HOLA";
		final Collection<Configuration> configurations = this.configurationService.findAll();
		for (final Configuration t : configurations)
			if (configurations.size() == 1)
				configlate = t;

		configlate.setWelcomMessageEs(message);
		this.configurationService.save(configlate);

		final Collection<Configuration> configurations2 = this.configurationService.findAll();
		for (final Configuration tt : configurations2)
			if (configurations.size() == 1)
				confignew = tt;

		Assert.isTrue(message.equals(confignew.getWelcomMessageEs()));

	}

}
