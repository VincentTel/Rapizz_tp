package com.RaPizz.controleur;

import java.util.EnumMap;
import java.util.Map;

import com.RaPizz.modele.gui.HomeModele;
import com.RaPizz.modele.gui.LoginModele;
import com.RaPizz.modele.gui.ManageClient;
import com.RaPizz.modele.gui.ManageIngredient;
import com.RaPizz.modele.gui.ManageLivreur;
import com.RaPizz.modele.gui.ManagePizza;
import com.RaPizz.modele.gui.ManageVehicule;
import com.RaPizz.modele.gui.MenuModele;
import com.RaPizz.modele.gui.OrderPizzaModele;
import com.RaPizz.modele.gui.SignupModele;
import com.RaPizz.modele.service.Service;



// pattern Singleton
public class Mediateur {

	public enum Contr {MAIN,LOGIN,SIGNUP,MENU,HOME,HEADER,MANAGEPIZZA,MANAGECLIENT,ORDERPIZZA,PROFIL,MANAGELIVREUR,MANAGECOMMANDE,MANAGEVEHICULE,MANAGEINGREDIENT}
	
	private Service service;
	private static Mediateur instance = null;
	private Map<Contr,AbstractControleur> controleurs = new EnumMap<Contr,AbstractControleur>(Contr.class);
	private LoginModele loginModele;
	private MenuModele menuModele;
	private OrderPizzaModele orderPizzaModele;
	private HomeModele homeModele;
	private ManagePizza managePizza;
	private ManageLivreur manageLivreur;
	private ManageClient manageCliente;
	private ManageVehicule manageVehicule;
	private ManageIngredient manageIngredient;
	private SignupModele signupModele;
	private Mediateur() {
		service = new Service();	
		loginModele = new LoginModele();
		signupModele = new SignupModele();
		menuModele = new MenuModele();
		orderPizzaModele = new OrderPizzaModele();
		homeModele = new HomeModele();
		managePizza = new ManagePizza();
		manageCliente = new ManageClient();
		manageLivreur = new ManageLivreur();
		manageVehicule = new ManageVehicule();
		manageIngredient = new ManageIngredient();
		
		
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
			return signupModele;
		case MENU:
			return menuModele;
		case PROFIL:
			return null;
		case HOME:
			return homeModele;
		case MANAGEPIZZA:
			return managePizza;
		case ORDERPIZZA:
			return orderPizzaModele;
		case MANAGECLIENT:
			return manageCliente;	
		case MANAGELIVREUR:
			return manageLivreur;
		case MANAGECOMMANDE:
			return null;
		case MANAGEVEHICULE:
			return manageVehicule;
		case MANAGEINGREDIENT:
			return manageIngredient;
		default:
			return null;			
		}		
	}	
	
	public void close(){
		
	}
	
}