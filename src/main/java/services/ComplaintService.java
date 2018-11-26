
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
import domain.Complaint;

@Service
@Transactional
public class ComplaintService {

	//Managed repo
	@Autowired
	private ComplaintRepository	complaintRepository;


	//Supporting services
	public Complaint create() {
		final Complaint res;
		res = new Complaint();
		//res.set falta

		return res;
	}

	public Complaint findOne(final Integer complaintId) {
		Complaint res;
		Assert.notNull(complaintId);
		res = this.complaintRepository.findOne(complaintId);
		Assert.notNull(res);

		return res;
	}

	public Collection<Complaint> findAll() {
		Collection<Complaint> res;
		res = this.complaintRepository.findAll();
		Assert.notNull(res);

		return res;
	}

	public Complaint save(final Complaint complaint) {
		Complaint res;
		Assert.notNull(complaint);
		res = this.complaintRepository.save(complaint);
		Assert.notNull(res);

		return res;
	}

	public void delete(final Complaint complaint) {
		Assert.notNull(complaint);
		this.complaintRepository.delete(complaint);
	}

}
