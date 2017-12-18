package businessLogic;

import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dominio.HibernateDataAccess;
//import domain.Booking;
import dominio.Offer;
import dominio.RuralHouse;
import dominio.User;
import exceptions.BadDates;
import exceptions.DB4oManagerCreationException;
import exceptions.OverlappingOfferExists;

//Service Implementation
@WebService(endpointInterface = "businessLogic.ApplicationFacadeInterfaceWS")
public class FacadeImplementationWS  implements ApplicationFacadeInterfaceWS {

	/**
	 * 
	 */

	public FacadeImplementationWS()  {
		//ConfigXML c=ConfigXML.getInstance();
		//if (c.getDataBaseOpenMode().equals("initialize")) {
		//	HibernateDataAccess dbManager=new HibernateDataAccess();
		//	dbManager.initializeBD();
		//	dbManager.close();
			//}
		

	}
	

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return the created offer, or null, or an exception
	 */
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws OverlappingOfferExists, BadDates {
		System.out.println(">> FacadeImplementationWS: createOffer=> ruralHouse= "+ruralHouse+" firstDay= "+firstDay+" lastDay="+lastDay+" price="+price);
		
		HibernateDataAccess dbManager=new HibernateDataAccess();
		Offer o=null;
		if (firstDay.compareTo(lastDay)>=0) { dbManager.close(); throw new BadDates();}
		
		System.out.println("Comprobando overlaping");
		boolean b = dbManager.existsOverlappingOffer(ruralHouse,firstDay,lastDay); 
		System.out.println("Comprobado. Creando offerta");
		
		if (!b) o=dbManager.createOffer(ruralHouse,firstDay,lastDay,price);	
		System.out.println("Oferta creada");

		dbManager.close();
		System.out.println("<< FacadeImplementationWS: createOffer=> O= "+o);
		return o;
	}
 

	
		
	public Vector<RuralHouse> getAllRuralHouses()  {
		System.out.println(">> FacadeImplementationWS: getAllRuralHouses");

		HibernateDataAccess dbManager=new HibernateDataAccess();

		Vector<RuralHouse>  ruralHouses=dbManager.getAllRuralHouses();
		dbManager.close();
		System.out.println("<< FacadeImplementationWS:: getAllRuralHouses");

		return ruralHouses;

	}
	
	/**
	 * This method obtains the  offers of a ruralHouse in the provided dates 
	 * 
	 * @param ruralHouse, the ruralHouse to inspect 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there is no overlapping offer
	 */

	@WebMethod public Vector<Offer> getOffers( RuralHouse rh, Date firstDay,  Date lastDay) {
		
		HibernateDataAccess dbManager=new HibernateDataAccess();
		Vector<Offer>  offers=new Vector<Offer>();
		  offers=dbManager.getOffers(rh,firstDay,lastDay);
		  dbManager.close();

		return offers;
	}	
		
		
	
	public void close() {
		HibernateDataAccess dbManager=new HibernateDataAccess();

		dbManager.close();

	}


	 public void initializeBD(){
		
		 HibernateDataAccess dbManager=new HibernateDataAccess();
		dbManager.initializeBD();
		dbManager.close();

	}


	@Override
	public void bookOffer(String phoneNumber, int offerNumber) {
		HibernateDataAccess dbManager=new HibernateDataAccess();
		
		dbManager.userBookOffer( phoneNumber,  offerNumber);
		
	}



	 

	}

