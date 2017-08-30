
package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class BusinessOrder extends DomainEntity {

	private Integer			storeIdentif;
	private String			vat;
	private Double			quantity;
	private Measure			measure;
	private Double			price;
	private Boolean			devilered;
	private List<Fish>		fish;
	private OfferTransport	offerTransport;
	private Date			day;


	//Getters

	@Range(min = 0)
	public Integer getStoreIdentif() {
		return storeIdentif;
	}

	@NotBlank
	public String getVat() {
		return vat;
	}

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
	public Double getPrice() {
		return price;
	}

	@NotNull
	public Boolean getDevilered() {
		return devilered;
	}

	@NotNull
	@ManyToMany
	public List<Fish> getFish() {
		return fish;
	}
	
	@OneToOne
	public OfferTransport getOfferTransport() {
		return offerTransport;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	public Date getDay() {
		return day;
	}

	//Setters

	public void setDay(Date day) {
		this.day = day;
	}

	public void setFish(List<Fish> fish) {
		this.fish = fish;
	}

	public void setOfferTransport(OfferTransport offerTransport) {
		this.offerTransport = offerTransport;
	}

	public void setStoreIdentif(Integer storeIdentif) {
		this.storeIdentif = storeIdentif;
	}

	public void setVat(String vat) {
		this.vat = vat;
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

	public void setDevilered(Boolean devilered) {
		this.devilered = devilered;
	}

}
