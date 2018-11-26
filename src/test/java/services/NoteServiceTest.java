
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Note;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class NoteServiceTest extends AbstractTest {

	@Autowired
	private NoteService	noteService;


	@Test
	public void testSaveNote() {
		super.authenticate("referee2");
		final Note note = this.noteService.create(1402);
		this.noteService.save(note);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteNote() {
		super.authenticate("referee2");
		final Note note = this.noteService.findOne(1406);

		this.noteService.delete(note);
		this.noteService.findOne(1406);
		super.authenticate(null);
	}

}
