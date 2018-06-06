package com.RaPizz.modele.metier;

public class Adresse {
	private String numRue;
	private	String rue;
	private	String Ville;
	private	String Cp;
	
	public String getNumRue() {
		return numRue;
	}

	public void setNumRue(String numRue) {
		this.numRue = numRue;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return Ville;
	}

	public void setVille(String ville) {
		Ville = ville;
	}

	public String getCp() {
		return Cp;
	}

	public void setCp(String cp) {
		Cp = cp;
	}

	public Adresse (String numRue, String rue, String Ville, String Cp)
	{
		this.numRue = numRue;
		this.rue = rue;
		this.Ville = Ville;
		this.Cp = Cp;
		
	}
		
	public Adresse(Adresse a )
	{
		this.numRue = a.getNumRue();
		this.rue = a.getRue();
		this.Ville = a.getVille();
		this.Cp = a.getCp();
	}
	
	

	@Override
	public String toString() {
		return "Adress:" + numRue + ", " + rue + " " + Ville + " " + Cp + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Cp == null) ? 0 : Cp.hashCode());
		result = prime * result + ((Ville == null) ? 0 : Ville.hashCode());
		result = prime * result + ((numRue == null) ? 0 : numRue.hashCode());
		result = prime * result + ((rue == null) ? 0 : rue.hashCode());
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
		Adresse other = (Adresse) obj;
		if (Cp == null) {
			if (other.Cp != null)
				return false;
		} else if (!Cp.equals(other.Cp))
			return false;
		if (Ville == null) {
			if (other.Ville != null)
				return false;
		} else if (!Ville.equals(other.Ville))
			return false;
		if (numRue == null) {
			if (other.numRue != null)
				return false;
		} else if (!numRue.equals(other.numRue))
			return false;
		if (rue == null) {
			if (other.rue != null)
				return false;
		} else if (!rue.equals(other.rue))
			return false;
		return true;
	}
}
