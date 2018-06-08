package com.RaPizz.modele.metier;

import java.sql.Date;

import javafx.scene.image.Image;

public class Livreur extends Personne{

	private float salaire;
	private Date localDate;
	private int retard;
	
	public Livreur(Personne p, float salaire,Date localDate)
	{
		super(p);
		this.salaire=salaire;
		this.localDate = localDate;
		this.retard = 0;
	}
	
	public Livreur(Long Id, String Prenom,String Nom, String UserName,String  Password,String Email, Image Photo)
	{
		super(Id, Prenom,Nom,UserName,Password,Email,Photo);
		this.retard = 0;
	}
	public Livreur(Long Id, String Prenom,String Nom, String UserName,String  Password,String Email, Image Photo, float salaire,Date localDate)
	{
		super(Id, Prenom,Nom,UserName,Password,Email,Photo);
		this.salaire=salaire;
		this.localDate = localDate;

		this.retard = 0;
		
	}
	public Livreur(Long Id, String Prenom, String Nom, String UserName,	String Password, String Email, float salaire, Date localDate)
	{
		super(Id, Prenom, Nom, UserName, Password, Email, null);
		this.salaire = salaire;
		this.localDate = localDate;

		this.retard = 0;
	}
	public float getSalaire() {
		return salaire;
	}
	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}
	public Date getLocalDate() {
		return localDate;
	}
	public void setLocalDate(Date localDate) {
		this.localDate = localDate;
	}

	public int getRetard() {
		return retard;
	}

	public void setRetard(int retard) {
		this.retard = retard;
	}

	@Override
	public String toString() {
		return this.getPrenom()+ " " + this.getNom();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((localDate == null) ? 0 : localDate.hashCode());
		result = prime * result + Float.floatToIntBits(salaire);
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
		Livreur other = (Livreur) obj;
		if (localDate == null) {
			if (other.localDate != null)
				return false;
		} else if (!localDate.equals(other.localDate))
			return false;
		if (Float.floatToIntBits(salaire) != Float.floatToIntBits(other.salaire))
			return false;
		return true;
	}

	
	
}
