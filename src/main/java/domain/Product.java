
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Product extends DomainEntity {

	private Double			quantity;
	private Double			price;
	private Measure			measure;
	private Fish			fish;
	private List<Comment>	comments;


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
	@OneToOne
	public Fish getFish() {
		return fish;
	}

	@NotNull
	@OneToMany
	public List<Comment> getComments() {
		return comments;
	}

	//Setters

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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

	public void setFish(Fish fish) {
		this.fish = fish;
	}

}
