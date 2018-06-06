package com.RaPizz.modele.metier;

public class Vehicule {

	private String Immat;
	private String Marque;
	private String Modele;
	private String Type;
	
	public Vehicule (String inImmat, String inMarque, String inModele, String inType) {
			this.Immat = inImmat;
			this.Marque = inMarque;
			this.Modele = inModele;
			this.Type = inType;
	}

	public String getMarque() {
		return Marque;
	}

	public void setMarque(String marque) {
		this.Marque = marque;
	}

	public String getModele() {
		return Modele;
	}

	public void setModele(String modele) {
		this.Modele = modele;
	}

	public String getImmat() {
		return Immat;
	}

	public void setImmat(String inImmat) {
		this.Immat = inImmat;
	}

	public String getType() {
		return Type;
	}

	public void setType(String inType) {
		this.Type = inType;
	}

}
