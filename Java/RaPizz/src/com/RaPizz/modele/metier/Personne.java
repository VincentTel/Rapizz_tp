package com.RaPizz.modele.metier;

import javafx.scene.image.Image;

public class Personne{
	
	private Long Id;

	private String Prenom;
	
	private String Nom;
	
	private String Username;
	
	private String Password;
	
	private String Email;
	
	private Image Photo;
	
	public Personne(Personne p) {
		this.Id = p.Id;
		this.Prenom = p.Prenom;
		this.Nom = p.Nom;
		this.Username = p.Username;
		this.Password = p.Password;
		this.Email = p.Email;
		this.Photo = p.Photo;
	}
	public Personne(Long Id, String Prenom,String Nom)
	{
		this.Id = Id;
		this.Prenom = Prenom;
		this.Nom = Nom;
		this.Username = "";
		this.Password = "";
		this.Email = "";
		this.Photo = null;
	}
	
	public Personne(Long Id, String Prenom,String Nom, String UserName,String  Password,String Email, Image Photo)
	{
		this.Id = Id;
		this.Prenom = Prenom;
		this.Nom = Nom;
		this.Username = UserName;
		this.Password = Password;
		this.Email = Email;
		this.Photo = Photo;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((Nom == null) ? 0 : Nom.hashCode());
		result = prime * result + ((Password == null) ? 0 : Password.hashCode());
		result = prime * result + ((Prenom == null) ? 0 : Prenom.hashCode());
		result = prime * result + ((Username == null) ? 0 : Username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (Nom == null) {
			if (other.Nom != null)
				return false;
		} else if (!Nom.equals(other.Nom))
			return false;
		if (Password == null) {
			if (other.Password != null)
				return false;
		} else if (!Password.equals(other.Password))
			return false;
		if (Prenom == null) {
			if (other.Prenom != null)
				return false;
		} else if (!Prenom.equals(other.Prenom))
			return false;
		if (Username == null) {
			if (other.Username != null)
				return false;
		} else if (!Username.equals(other.Username))
			return false;
		return true;
	}

	public Long getID() {
		return Id;
	}



	public Image getPhoto() {
		return Photo;
	}

	public void setPhoto(Image photo) {
		Photo = photo;
	}

	public void setID(Long iD) {
		Id = iD;
	}



	public String getPrenom() {
		return Prenom;
	}



	public void setPrenom(String prenom) {
		Prenom = prenom;
	}



	public String getNom() {
		return Nom;
	}



	public void setNom(String nom) {
		Nom = nom;
	}



	public String getUserName() {
		return Username;
	}



	public void setUserName(String userName) {
		Username = userName;
	}



	public String getPassword() {
		return Password;
	}



	public void setPassword(String password) {
		Password = password;
	}



	public String getEmail() {
		return Email;
	}



	public void setEmail(String email) {
		Email = email;
	}



	@Override
	public String toString() {
		return String.format("[%s,%s,%s,%s,%s]", this.Nom, this.Prenom, this.Username, this.Password, this.Email);
	}

	

}
