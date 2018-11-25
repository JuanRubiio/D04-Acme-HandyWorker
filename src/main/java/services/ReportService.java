
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import repositories.ReportRepository;
import domain.Complaint;
import domain.Referee;
import domain.Report;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepository;

	@Autowired
	private RefereeRepository	refereeService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ComplaintRepository	complaintService;


	@Autowired
	public Report create(final Complaint c) {
		Report res;
		res = new Report();
		final Referee referee = this.actorService.getPrincipal();
		res.setReferee(referee);
		res.setMoment(new Date());
		res.setComplaint(this.complaintService.findOne(c));
		res.setDraft(false);

		return res;
	}
	public Collection<Report> findAll() {
		Collection<Report> res;
		res = this.reportRepository.findAll();
		Assert.notNull(res);
		//solo mostrar los que no son borradores
		for (final Report r : res)
			Assert.isTrue(!r.getDraft());

		return res;
	}
	public Report findOne(final Integer reportId) {
		Report res;
		Assert.notNull(reportId);
		res = this.reportRepository.findOne(reportId);
		//solo se puedan buscar los que no son borradores
		Assert.isTrue(!res.getDraft());
		return res;
	}

	public void delete(final Report report) {
		Assert.notNull(report);
		//se puede borrar solo cuando sea borrador
		Assert.isTrue(report.getDraft());
		this.reportRepository.delete(report);
	}

	public void save(final Report report) {
		Assert.notNull(report);
		//se puede actualizar solo cuando sea borrador
		Assert.isTrue(report.getDraft());
		this.reportRepository.save(report);
	}
}
