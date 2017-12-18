package dominio;

import java.util.Set;

public class User {
	public int phoneNumber;
	public Set<Offer> offers;
	
	public User() {}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	
	

}
