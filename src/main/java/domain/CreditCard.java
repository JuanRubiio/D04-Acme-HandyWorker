
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity {

	private String	holderName;
	private String	brandName;
	@Column(unique = true)
	private String	number;
	private int		expiryMonth;
	private int		expiryYear;
	private int		cvv;


	@NotBlank
	public String getHolderName() {
		return this.holderName;
	}

	public void setHolderName(final String holderName) {
		this.holderName = holderName;
	}
	@NotBlank
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}
	@CreditCardNumber
	public String getNumber() {
		return this.number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}
	@Range(min = 1, max = 12)
	public int getExpiryMonth() {
		return this.expiryMonth;
	}

	public void setExpiryMonth(final int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	@Min(2018)
	public int getExpiryYear() {
		return this.expiryYear;
	}

	public void setExpiryYear(final int expiryYear) {
		this.expiryYear = expiryYear;
	}
	@Range(min = 100, max = 999)
	public int getCvv() {
		return this.cvv;
	}

	public void setCvv(final Integer cvv) {
		this.cvv = cvv;
	}

}
