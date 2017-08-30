
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Store extends DomainEntity {

	private String			name;
	private String			postalAddress;
	private String			city;
	private String			VAT;
	private List<Comment>	comments;
	private List<Product>	products;


	//Getters

	@NotBlank
	public String getName() {
		return name;
	}

	@NotBlank
	public String getPostalAddress() {
		return postalAddress;
	}

	@NotBlank
	public String getCity() {
		return city;
	}

	@NotBlank
	public String getVAT() {
		return VAT;
	}

	@NotNull
	@OneToMany
	public List<Comment> getComments() {
		return comments;
	}

	@NotNull
	@OneToMany
	public List<Product> getProducts() {
		return products;
	}

	//Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setVAT(String vAT) {
		VAT = vAT;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
