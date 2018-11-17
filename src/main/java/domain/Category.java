
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	// Atributos ---- 
	private String	engName;
	private String	espName;


	@NotBlank
	public String getEngName() {
		return this.engName;
	}

	public void setEngName(final String engName) {
		this.engName = engName;
	}

	@NotBlank
	public String getEspName() {
		return this.espName;
	}

	public void setEspName(final String espName) {
		this.espName = espName;
	}


	// Relationships ----------------------------------------------------------

	private Category	father;


	@Valid
	@ManyToOne(optional = true)
	public Category getFather() {
		return this.father;
	}

	public void setFather(final Category fatherCategory) {
		this.father = fatherCategory;
	}

}
