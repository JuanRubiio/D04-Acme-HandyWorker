
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import domain.Note;

@Service
@Transactional
public class NoteService {

	//pueda escribirla el cliente el handyworker o el referee
	@Autowired
	private NoteRepository	noteRepository;

	@Autowired
	private ReportService	reportService;


	public Note create(final Integer reportId) {
		Note res;
		res = new Note();
		res.setMoment(new Date());
		res.setReport(this.reportService.findOne(reportId));
		return res;
	}
	public Collection<Note> findAll() {
		Collection<Note> res;
		res = this.noteRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Note findOne(final Integer noteId) {
		Note res;
		Assert.notNull(noteId);
		res = this.noteRepository.findOne(noteId);
		return res;
	}
	public void delete(final Note note) {
		Assert.notNull(note);
		this.noteRepository.delete(note);
	}

	public void save(final Note note) {
		Assert.notNull(note);
		this.noteRepository.save(note);
	}
}
