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

	@Override
	public String toString() {
		return Type+ ": "+ Immat +", "+ Marque + ", " + Modele   ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Immat == null) ? 0 : Immat.hashCode());
		result = prime * result + ((Marque == null) ? 0 : Marque.hashCode());
		result = prime * result + ((Modele == null) ? 0 : Modele.hashCode());
		result = prime * result + ((Type == null) ? 0 : Type.hashCode());
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
		Vehicule other = (Vehicule) obj;
		if (Immat == null) {
			if (other.Immat != null)
				return false;
		} else if (!Immat.equals(other.Immat))
			return false;
		if (Marque == null) {
			if (other.Marque != null)
				return false;
		} else if (!Marque.equals(other.Marque))
			return false;
		if (Modele == null) {
			if (other.Modele != null)
				return false;
		} else if (!Modele.equals(other.Modele))
			return false;
		if (Type == null) {
			if (other.Type != null)
				return false;
		} else if (!Type.equals(other.Type))
			return false;
		return true;
	}

}
