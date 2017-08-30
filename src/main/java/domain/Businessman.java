
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Businessman extends Actor {

	private List<BusinessOrder>	orders;
	private Store				store;


	//Getters

	@NotNull
	@OneToMany
	public List<BusinessOrder> getOrders() {
		return orders;
	}

	@OneToOne(optional = true)
	public Store getStore() {
		return store;
	}

	//Setters
	public void setOrders(List<BusinessOrder> orders) {
		this.orders = orders;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
