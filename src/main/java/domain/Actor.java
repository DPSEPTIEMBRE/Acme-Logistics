
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {

	private String			actorName;
	private String			surname;
	private String			numberidentif;
	private String			city;
	private String			phone;
	private String			postalAddress;
	private String			email;
	private UserAccount		userAccount;
	private CreditCard		creditCard;
	private List<Folder>	folders;
	private List<Actor>		follower;
	private List<Actor>		followed;
	private Wall			wall;


	//Getters

	@NotBlank
	public String getActorName() {
		return actorName;
	}

	@NotBlank
	public String getSurname() {
		return surname;
	}

	@NotBlank
	@Pattern(regexp = "[0-9]{8,8}[A-Z]")
	public String getNumberidentif() {
		return numberidentif;
	}

	@NotBlank
	public String getCity() {
		return city;
	}

	@Pattern(regexp = "^$|^\\+([1-9][0-9]{0,2}) (\\([1-9][0-9]{0,2}\\)) ([a-zA-Z0-9 -]{4,})$")
	public String getPhone() {
		return phone;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	@OneToOne(optional = true)
	public CreditCard getCreditCard() {
		return creditCard;
	}

	@OneToMany
	public List<Folder> getFolders() {
		return folders;
	}

	@ManyToMany(mappedBy = "followed")
	public List<Actor> getFollower() {
		return follower;
	}

	@ManyToMany
	public List<Actor> getFollowed() {
		return followed;
	}

	@OneToOne
	public Wall getWall() {
		return wall;
	}

	//Setters

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setNumberidentif(String numberidentif) {
		this.numberidentif = numberidentif;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	public void setFollower(List<Actor> follower) {
		this.follower = follower;
	}

	public void setFollowed(List<Actor> followed) {
		this.followed = followed;
	}

	public void setWall(Wall wall) {
		this.wall = wall;
	}

}
