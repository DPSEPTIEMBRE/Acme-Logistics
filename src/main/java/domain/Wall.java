
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Wall extends DomainEntity {

	private String			name;
	private Integer			offer;

	//getters

	@NotBlank
	public String getName() {
		return name;
	}
	
	@NotNull
	public Integer getOffer() {
		return offer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOffer(Integer offer) {
		this.offer = offer;
	}
}
