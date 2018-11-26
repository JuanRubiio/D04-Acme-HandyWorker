
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Finder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FinderServiceTest extends AbstractTest {

	@Autowired
	private FinderService	finderService;


	@Test
	public void testSaveFinder() {
		super.authenticate("handyWorker1");
		final Finder finder = this.finderService.create();
		this.finderService.save(finder);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteFinder() {
		super.authenticate("handyWorker1");
		final Finder finder = this.finderService.findOne(1399);

		this.finderService.delete(finder);
		this.finderService.findOne(1399);
		super.authenticate(null);
	}
}
