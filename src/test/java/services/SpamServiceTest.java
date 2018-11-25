
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
import domain.Spam;

// NOTA: ES NECESARIO POPULAR ANTES DE EJECUTAR EL TEST, PARA PREVENIR POSIBLES FALLOS

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class SpamServiceTest extends AbstractTest {

	@Autowired
	private SpamService	spamService;


	@Test
	public void testSaveSpam() {

		Spam spam, saved;
		Collection<Spam> spams;

		spam = this.spamService.create();
		spam.setSpamWords("SPAM");

		saved = this.spamService.save(spam);
		spams = this.spamService.findAll();
		Assert.isTrue(spams.contains(saved));

	}

	@Test
	public void testDeleteSpam() {
		Spam deleted;
		Collection<Spam> spams;

		deleted = this.spamService.findOne(1365);
		this.spamService.delete(deleted);
		spams = this.spamService.findAll();
		Assert.isTrue(!spams.contains(deleted));
	}

}
