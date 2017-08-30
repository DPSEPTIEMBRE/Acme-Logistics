
package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class OfferMarket extends DomainEntity {

	private Date		date;
	private String		place;
	private Integer		duration;
	private Double		quantity;
	private Measure		measure;
	private Double		price;
	private Fisherman	fisherman;
	private List<Fish>	fishers;
	private Boolean		isEstatic;


	//Getters
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	public Date getDate() {
		return date;
	}

	@NotBlank
	public String getPlace() {
		return place;
	}

	@Range(min = 0)
	public Integer getDuration() {
		return duration;
	}

	@NotNull
	public Double getQuantity() {
		return quantity;
	}

	@NotNull
	public Measure getMeasure() {
		return measure;
	}

	@NotNull
	public Double getPrice() {
		return price;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Fisherman getFisherman() {
		return fisherman;
	}

	@NotNull
	@ManyToMany
	public List<Fish> getFishers() {
		return fishers;
	}

	//Setters

	@NotNull
	public Boolean getIsEstatic() {
		return isEstatic;
	}

	public void setFishers(List<Fish> fishers) {
		this.fishers = fishers;
	}

	public void setFisherman(Fisherman fisherman) {
		this.fisherman = fisherman;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	public void setIsEstatic(Boolean isEstatic) {
		this.isEstatic = isEstatic;
	}

}
