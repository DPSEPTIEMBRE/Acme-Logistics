
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class OfferTransport extends DomainEntity {

	private String	transporterIdentif;
	private String	origin;
	private String	destination;
	private Double	chargeMax;
	private Measure	measure;
	private Double	price;
	private Date	periodeStart;
	private Date	periodeEnd;
	private Boolean available;


	//Getters
	
	@NotNull
	public Boolean getAvailable() {
		return available;
	}

	@NotBlank
	//@Pattern(regexp = "$\\d{8}\\w{1}\\d{2}^")
	public String getTransporterIdentif() {
		return transporterIdentif;
	}

	@NotBlank
	public String getOrigin() {
		return origin;
	}
	@NotBlank
	public String getDestination() {
		return destination;
	}

	@NotNull
	public Double getChargeMax() {
		return chargeMax;
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
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getPeriodeStart() {
		return periodeStart;
	}

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getPeriodeEnd() {
		return periodeEnd;
	}

	public void setTransporterIdentif(String transporterIdentif) {
		this.transporterIdentif = transporterIdentif;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setChargeMax(Double chargeMax) {
		this.chargeMax = chargeMax;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setPeriodeStart(Date periodeStart) {
		this.periodeStart = periodeStart;
	}

	public void setPeriodeEnd(Date periodeEnd) {
		this.periodeEnd = periodeEnd;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
}
