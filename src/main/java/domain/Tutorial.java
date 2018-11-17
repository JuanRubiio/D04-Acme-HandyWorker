
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Tutorial extends DomainEntity {

	private String				title;
	private Date				momentUpdate;
	private String				summary;
	private String				pictures;
	private Date				momentCreate;

	//external attributes
	private Collection<Section>	sections;
	private HandyWorker			handyWorker;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getMomentUpdate() {
		return this.momentUpdate;
	}

	public void setMomentUpdate(final Date momentUpdate) {
		this.momentUpdate = momentUpdate;
	}
	@NotBlank
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(final String summary) {
		this.summary = summary;
	}

	public String getPictures() {
		return this.pictures;
	}

	public void setPictures(final String pictures) {
		this.pictures = pictures;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMomentCreate() {
		return this.momentCreate;
	}

	public void setMomentCreate(final Date momentCreate) {
		this.momentCreate = momentCreate;
	}

	@Valid
	@NotEmpty
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Section> getSections() {
		return this.sections;
	}

	public void setSections(final Collection<Section> sections) {
		this.sections = sections;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public HandyWorker getHandyWorker() {
		return this.handyWorker;
	}

	public void setHandyWorker(final HandyWorker handyWorker) {
		this.handyWorker = handyWorker;
	}

}
