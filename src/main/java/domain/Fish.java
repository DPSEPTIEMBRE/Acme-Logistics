
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Fish extends DomainEntity {

	private String			name;
	private List<Property>	listProperties;


	//Getters
	@NotBlank
	public String getName() {
		return name;
	}

	@NotNull
	@OneToMany
	public List<Property> getListProperties() {
		return listProperties;
	}

	//Setters

	public void setName(String name) {
		this.name = name;
	}

	public void setListProperties(List<Property> listProperties) {
		this.listProperties = listProperties;
	}

}
