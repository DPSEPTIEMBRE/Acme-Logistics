package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Measure {

	private static final String	KG	= "KG", POUND = "POUND";
	private String			value;


	//Getters

	@NotBlank
	@Pattern(regexp = "^" + KG + "|" + POUND + "|" + "$")
	public String getValue() {
		return value;
	}

	//Setters

	public void setValue(String value) {
		this.value = value;
	}

}
