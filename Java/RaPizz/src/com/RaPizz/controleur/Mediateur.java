package com.RaPizz.controleur;

import java.util.EnumMap;
import java.util.Map;

import com.RaPizz.modele.gui.LoginModele;
import com.RaPizz.modele.gui.ManagePizza;
import com.RaPizz.modele.gui.MenuModele;
import com.RaPizz.modele.gui.OrderPizzaModele;
import com.RaPizz.modele.service.Service;



// pattern Singleton
public class Mediateur {

	public enum Contr {MAIN,LOGIN,SIGNUP,MENU,HOME,HEADER,MANAGEPIZZA,ORDERPIZZA}
	
	private Service service;
	private static Mediateur instance = null;
	private Map<Contr,AbstractControleur> controleurs = new EnumMap<Contr,AbstractControleur>(Contr.class);
	private LoginModele loginModele;
	private MenuModele menuModele;
	private ManagePizza managePizza;
	private OrderPizzaModele orderPizzaModele;
	private Mediateur() {
		service = new Service();	
		loginModele = new LoginModele();
		menuModele = new MenuModele();
		managePizza = new ManagePizza();
		orderPizzaModele = new OrderPizzaModele();
	}
	
	public static Mediateur getInstance(Contr contr, AbstractControleur controleur) {
		if(instance == null){
			instance = new Mediateur();
		}
		instance.controleurs.put(contr,controleur);
		return instance;
	}
	
	public AbstractControleur getControleur(Contr contr){
		return controleurs.get(contr);		
	}
	
	public Service getService() {
		return service;
	}
	
	public Object getModele(Contr contr){
		switch(contr)
		{
		case MAIN:
			return null;
		case LOGIN:
			return loginModele;
		case SIGNUP:
			return null;
		case MENU:
			return menuModele;
		case HOME:
			return null;
		case MANAGEPIZZA:
			return managePizza;
		case ORDERPIZZA:
			return orderPizzaModele;
		default:
			return null;			
		}		
	}	
	
	public void close(){
		
	}
	
}