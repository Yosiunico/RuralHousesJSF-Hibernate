package beans;

import java.util.ArrayList;
import java.util.List;

import businessLogic.FacadeImplementationWS;
import domain.Offer;
import domain.RuralHouse;

public class Facade {

	private static FacadeImplementationWS instance = new FacadeImplementationWS();

	private Facade() {}

	public static FacadeImplementationWS getInstance() {
		return instance;
	}
}
