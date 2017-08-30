
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity {

	private String	holder;
	private String	brand;
	private String	cardNumber;
	private Integer	expMonth;
	private Integer	expYear;
	private Integer	cvv;


	//Getters

	@NotBlank
	public String getHolder() {
		return holder;
	}

	@NotBlank
	public String getBrand() {
		return brand;
	}

	@NotBlank
	@CreditCardNumber
	public String getCardNumber() {
		return cardNumber;
	}

	@NotNull
	public Integer getExpMonth() {
		return expMonth;
	}

	@NotNull
	public Integer getExpYear() {
		return expYear;
	}

	@NotNull
	@Range(min = 100, max = 999)
	public Integer getCvv() {
		return cvv;
	}

	//Setters

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setExpMonth(Integer expMonth) {
		this.expMonth = expMonth;
	}

	public void setExpYear(Integer expYear) {
		this.expYear = expYear;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

}
