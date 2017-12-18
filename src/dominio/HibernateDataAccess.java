package dominio;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.hibernate.Session;

import exceptions.OverlappingOfferExists;
import modelo.HibernateUtil;

public class HibernateDataAccess {

	public void initializeBD() {
		
		createRuralHouse("Ezkioko etxea","Ezkio");
		createRuralHouse("Etxetxikia","Iruna");
		createRuralHouse("Udaletxea","Bilbo");
		createRuralHouse("Gaztetxea","Renteria");
		
	}
	private void getRuralHouse(RuralHouse rh) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		RuralHouse result = (RuralHouse) session.get(RuralHouse.class, 1);
		session.getTransaction().commit();
		System.out.println(result);
		
	}
	public Offer createOffer(RuralHouse rh, Date firstDay, Date lastDay, float price) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		RuralHouse result = (RuralHouse) session.get(RuralHouse.class, rh.getHouseNumber());
		
		Offer offer = new Offer();
		offer.setFirstDay(firstDay);
		offer.setLastDay(lastDay);
		offer.setPrice(price);
		offer.setRuralHouse(result);
		
		session.save(offer);
		
		session.getTransaction().commit();
		
		return offer;
		
		

	}
	
	
	public Vector<RuralHouse> getAllRuralHouses() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<RuralHouse> result = session.createQuery("from RuralHouse").list();
		session.getTransaction().commit();
		Vector<RuralHouse> res = new Vector<RuralHouse>();
		for(RuralHouse rh : result) {
			res.add(rh);
		}
		return res;
		
	}
	
	public Vector<Offer> getOffers(RuralHouse rh, Date firstDay,  Date lastDay) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println("Buscando rh");
		RuralHouse result = (RuralHouse) session.get(RuralHouse.class, rh.getHouseNumber());
		System.out.println("rh encontrada");
		System.out.println("Buscando offers");
		List<Offer> res = result.getOffers(firstDay, lastDay);
		System.out.println("Offers encontradas");
		
		session.getTransaction().commit();
		Vector<Offer> offers = new Vector<Offer>();
		for(Offer of : res) {
			offers.add(of);
		}
		
		return offers;
	}
	public boolean existsOverlappingOffer(RuralHouse rh,Date firstDay, Date lastDay) throws  OverlappingOfferExists{
		try{
			//RuralHouse rhn = db.find(RuralHouse.class, rh.getHouseNumber());
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			RuralHouse rhn = (RuralHouse) session.get(RuralHouse.class, rh.getHouseNumber());
			if (rhn.overlapsWith(firstDay,lastDay)!=null) return true;
			session.getTransaction().commit();
		} catch (Exception e){
				System.out.println("Error: "+e.toString());
				return true;
		}
		
		return false;
	}
	
	
	private void createRuralHouse(String description, String city) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		RuralHouse rh = new RuralHouse();
		rh.setDescription(description);
		rh.setCity(city);
		session.save(rh);
		session.getTransaction().commit();
	}
	
	public void close() {
		System.out.println("Cerrando");
	}
	
	public void deleteOffer(int offerNumber) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Offer result = (Offer) session.get(Offer.class, offerNumber);
		
		session.delete(result);
		session.getTransaction().commit();
		
	}
	public User getUser(int phoneNumber) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User result = (User) session.get(User.class, phoneNumber);
		System.out.println("Pillado user");
		if (result == null) {
			System.out.println("User es null");
			result = new User();
			result.setPhoneNumber(phoneNumber);
			session.save(result);
		}else {
			System.out.println("UserNo es null;");
		}
		
		session.getTransaction().commit();
		return result;
	}
	public void userBookOffer(int phoneNumber, int offerNumber) {
		System.out.println("Booking offer ->"+offerNumber+ " with "+ phoneNumber+ " phonenumber");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User result = (User) session.get(User.class, phoneNumber);
		System.out.println("Pillado user");
		if (result == null) {
			System.out.println("User es null");
			result = new User();
			result.setPhoneNumber(phoneNumber);
			result.setOffers(new HashSet<Offer>());
		}else {
			System.out.println("UserNo es null;");
		}
		Offer off = (Offer)session.get(Offer.class, offerNumber);
		
		Set offers = result.getOffers();
		
		offers.add(off);
		
		off.setRuralHouse(null);
		
		session.saveOrUpdate(result);
		session.saveOrUpdate(off);
		
		session.getTransaction().commit();
		
	}
}
