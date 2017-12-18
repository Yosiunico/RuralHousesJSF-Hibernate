package principal;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import beans.Facade;
import dominio.HibernateDataAccess;
import dominio.Offer;
import dominio.RuralHouse;

public class principal {

	public static void main(String[] args) {
		HibernateDataAccess hda = new HibernateDataAccess();
		//hda.getRuralHouse((RuralHouse) lista.get(1));
		Vector<RuralHouse> rhs = hda.getAllRuralHouses();
		hda.createOffer(rhs.get(2), new Date(2017 + 1900, 12, 3), new Date(2017 + 1900, 12, 7), 200);
		System.out.println("Oferta creada");
		
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
