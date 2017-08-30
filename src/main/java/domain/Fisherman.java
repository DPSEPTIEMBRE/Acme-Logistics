
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Fisherman extends Actor {

	private List<DailyCatch>	dailyCatchs;
	private List<OfferMarket>	offerMarkets;


	//Getters
	@NotNull
	@OneToMany
	public List<DailyCatch> getDailyCatchs() {
		return dailyCatchs;
	}

	@NotNull
	@OneToMany
	public List<OfferMarket> getOfferMarkets() {
		return offerMarkets;
	}

	//Setters
	public void setDailyCatchs(List<DailyCatch> dailyCatchs) {
		this.dailyCatchs = dailyCatchs;
	}

	public void setOfferMarkets(List<OfferMarket> offerMarkets) {
		this.offerMarkets = offerMarkets;
	}

}
