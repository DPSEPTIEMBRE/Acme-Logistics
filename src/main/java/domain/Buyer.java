
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Buyer extends Actor {

	private List<Ticker> tickers;


	//Getters
	@NotNull
	@OneToMany
	public List<Ticker> getTickers() {
		return tickers;
	}

	public void setTickers(List<Ticker> tickers) {
		this.tickers = tickers;
	}

}
