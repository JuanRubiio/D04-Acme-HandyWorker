
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Administrator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class AdministratorServiceTest extends AbstractTest {

	@Autowired
	private AdministratorService	administratorService;


	@Test
	public void testSaveAdministrator() {
		super.authenticate("administrator1");
		final Administrator administrator = this.administratorService.create();
		this.administratorService.save(administrator);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteAdministrator() {
		super.authenticate("administrator2");
		final Administrator administrator = this.administratorService.findOne(1281);

		this.administratorService.delete(administrator);
		this.administratorService.findOne(1281);
		super.authenticate(null);
	}
}
