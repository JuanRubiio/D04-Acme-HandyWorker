
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class HandyWorker extends Endorser {

	//------Atributos---------
	private String	mark;


	//--------Getter & Setter------------
	@NotBlank
	@PersistenceContext
	public String getMark() {
		String res;
		if (this.getName() != null && this.getMiddleName() != null && this.getSurname() != null)
			res = this.getName() + " " + this.getMiddleName() + " " + this.getSurname();
		else if (this.getName() != null && this.getMiddleName() == null && this.getSurname() != null)
			res = this.getName() + " " + this.getSurname();
		else
			res = this.mark;
		return res;
	}

	public void setMark(final String mark) {
		this.mark = mark;
	}


	//---------Relationships--------------

	private Curriculum				curriculum;
	private Collection<Application>	applications;


	@Valid
	@OneToMany(mappedBy = "handyWorker")
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}

	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

}
