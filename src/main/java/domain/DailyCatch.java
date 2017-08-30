
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class DailyCatch extends DomainEntity {

	private Double		quantity;
	private Measure		measure;
	private Fisherman	fisherman;
	private Fish		fish;


	//Getters
	@NotNull
	public Double getQuantity() {
		return quantity;
	}

	@Valid
	@NotNull
	public Measure getMeasure() {
		return measure;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Fisherman getFisherman() {
		return fisherman;
	}

	@NotNull
	@ManyToOne
	public Fish getFish() {
		return fish;
	}

	//Setters

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	public void setFisherman(Fisherman fisherman) {
		this.fisherman = fisherman;
	}

	public void setFish(Fish fish) {
		this.fish = fish;
	}

}
