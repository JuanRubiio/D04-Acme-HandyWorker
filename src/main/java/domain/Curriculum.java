
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	// Atributos ---- 
	private String	ticker;


	@NotBlank
	@Pattern(regexp = "\\d{6}-[A-Z]{4}")
	@Column(unique = true)
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}


	// Relationships ----

	private PersonalRecord					personalRecord;
	private Collection<EducationalRecord>	educationalRecords;
	private Collection<ProfessionalRecord>	professionalRecords;
	private Collection<EndorserRecord>		endoserRecords;
	private Collection<MiscellaneousRecord>	miscellaneousRecords;


	@Valid
	@NotNull
	@OneToOne(optional = false)
	public PersonalRecord getPersonalRecord() {
		return this.personalRecord;
	}

	public void setPersonalRecord(final PersonalRecord personalRecord) {
		this.personalRecord = personalRecord;
	}

	@Valid
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<EducationalRecord> getEducationalRecords() {
		return this.educationalRecords;
	}

	public void setEducationalRecords(final Collection<EducationalRecord> edRecord) {
		this.educationalRecords = edRecord;
	}

	@Valid
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<ProfessionalRecord> getProfessionalRecords() {
		return this.professionalRecords;
	}

	public void setProfessionalRecords(final Collection<ProfessionalRecord> profRecord) {
		this.professionalRecords = profRecord;
	}

	@Valid
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<EndorserRecord> getEndoserRecords() {
		return this.endoserRecords;
	}

	public void setEndoserRecords(final Collection<EndorserRecord> endRecords) {
		this.endoserRecords = endRecords;
	}

	@Valid
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<MiscellaneousRecord> getMiscellaneousRecords() {
		return this.miscellaneousRecords;
	}

	public void setMiscellaneousRecords(final Collection<MiscellaneousRecord> misRecords) {
		this.miscellaneousRecords = misRecords;
	}

}
