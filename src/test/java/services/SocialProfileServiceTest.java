
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.SocialProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SocialProfileServiceTest extends AbstractTest {

	@Autowired
	private SocialProfileService	socialProfileService;


	@Test
	public void testSaveSocialProfile() {

		super.authenticate("customer1");
		final SocialProfile soc = this.socialProfileService.create();

		final SocialProfile prueba = this.socialProfileService.save(soc);

		final SocialProfile tt = this.socialProfileService.findOne(prueba.getId());
		Assert.notNull(tt);

		super.authenticate(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteSocialProfile() {

		super.authenticate("customer1");
		final SocialProfile soc = this.socialProfileService.findOne(1375);

		this.socialProfileService.delete(soc);

		this.socialProfileService.findOne(1375);
		super.authenticate(null);
	}

}
