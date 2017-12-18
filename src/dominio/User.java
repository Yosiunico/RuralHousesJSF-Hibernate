package dominio;

import java.util.Set;

public class User {
	public String phoneNumber;
	public Set<Offer> offers;
	
	public User() {}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	
	

}
