
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Transporter extends Actor {

	private List<OfferTransport> offerTransport;


	//Getters
	@NotNull
	@OneToMany
	public List<OfferTransport> getOfferTransport() {
		return offerTransport;
	}

	public void setOfferTransport(List<OfferTransport> offerTransport) {
		this.offerTransport = offerTransport;
	}

}
