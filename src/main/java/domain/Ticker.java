
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Ticker extends DomainEntity {

	private Double	quantity;
	private Double	price;
	private Measure	measure;
	private List<Fish>	fish;


	//Getters

	@NotNull
	public Double getQuantity() {
		return quantity;
	}

	@NotNull
	public Double getPrice() {
		return price;
	}

	@Valid
	@NotNull
	public Measure getMeasure() {
		return measure;
	}

	@NotNull
	@ManyToMany
	public List<Fish> getFish() {
		return fish;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	public void setFish(List<Fish> fish) {
		this.fish = fish;
	}

}
