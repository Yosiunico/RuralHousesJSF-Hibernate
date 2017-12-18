package principal;

import java.util.Date;
import java.util.List;

import beans.Facade;
import dominio.HibernateDataAccess;
import dominio.Offer;

public class principal {

	public static void main(String[] args) {
		HibernateDataAccess hda = new HibernateDataAccess();
		//hda.getRuralHouse((RuralHouse) lista.get(1));
		
		hda.userBookOffer(11111111, 3);
		System.out.println("Oferta bookeada");
		
		/*Offer of = hda.createOffer((RuralHouse) lista.get(0), new Date(17,11,9) , new Date(17,11,12), 12);
		
		System.out.println(of);
		System.out.println("Post off");
		*/

		//List ls = hda.getOffers((RuralHouse) lista.get(0), new Date(17,11,1), new Date(17,11,30));;
		//List<RuralHouse> ruralHouses;
		//ruralHouses = Facade.getInstance().getAllRuralHouses();
		//System.out.println(ruralHouses);

	}


}
