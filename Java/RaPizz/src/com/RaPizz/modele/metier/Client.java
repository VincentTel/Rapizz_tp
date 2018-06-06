package com.RaPizz.modele.metier;


import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

public class Client extends Personne {
	private Adresse adr ;
	private SimpleFloatProperty solde;
	private SimpleIntegerProperty pizzaGratuite;
	
	public SimpleFloatProperty getSolde() {
		return solde;
	}
	public void setSolde(float solde) {
		this.solde.setValue( solde);
	}
	public SimpleIntegerProperty getPizzaGratuite() {
		return pizzaGratuite;
	}
	public void setPizzaGratuite(int pizzaGratuite) {
		this.pizzaGratuite.setValue(pizzaGratuite);
	}
	public Adresse getAdr() {
		return adr;
	}
	public void setAdr(Adresse adr) { 
		this.adr = adr;
	}
		
	public Client(Personne p,  Adresse adr, float solde, int pizzaGratuite)
	{
		super(p);
		this.adr = adr;
		this.solde = new SimpleFloatProperty( solde);
		this.pizzaGratuite = new SimpleIntegerProperty( pizzaGratuite);
	}
	public Client(Personne p,  String numRue, String rue, String Ville, String Cp, float solde, int pizzaGratuite)
	{
		super(p);
		adr = new Adresse(numRue,rue,Ville,Cp);	
		this.solde = new SimpleFloatProperty( solde);
		this.pizzaGratuite = new SimpleIntegerProperty( pizzaGratuite);
	}
	public Client(Long Id, String Prenom,String Nom, String UserName,String  Password,String Email, Image Photo, Adresse adr, float solde, int pizzaGratuite)
	{
		super(Id, Prenom,Nom,UserName,Password,Email,Photo);
		this.adr = adr;
		this.solde = new SimpleFloatProperty( solde);
		this.pizzaGratuite = new SimpleIntegerProperty( pizzaGratuite);
	}
	public Client(Long Id, String Prenom,String Nom, String UserName,String  Password,String Email, Image Photo, String numRue, String rue, String Ville, String Cp, float solde, int pizzaGratuite)
	{
		super(Id, Prenom,Nom,UserName,Password,Email,Photo);
		adr = new Adresse(numRue,rue,Ville,Cp);		
		this.solde = new SimpleFloatProperty( solde);
		this.pizzaGratuite = new SimpleIntegerProperty( pizzaGratuite);
	}
	public Client(Long Id, String Prenom,String Nom, String UserName,String  Password,String Email, String numRue, String rue, String Ville, String Cp, float solde, int pizzaGratuite)
	{
		super(Id, Prenom,Nom,UserName,Password,Email,null);
		adr = new Adresse(numRue,rue,Ville,Cp);		
		this.solde.setValue(solde);
		this.pizzaGratuite.setValue(pizzaGratuite);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((adr == null) ? 0 : adr.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (adr == null) {
			if (other.adr != null)
				return false;
		} else if (!adr.equals(other.adr))
			return false;
		return true;
	}

	
}
