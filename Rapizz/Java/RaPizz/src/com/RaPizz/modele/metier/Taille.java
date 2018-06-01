package com.RaPizz.modele.metier;

public class Taille {
	private long id;
	private String Designation;
	private float PrixPonderation;
	
	public Taille(long id,String Designation, float PrixPonderation)
	{
		this.id = id;
		this.Designation=Designation;
		this.PrixPonderation = PrixPonderation;			
	}
	public Taille(String Designation, float PrixPonderation)
	{
		this.id = 0;
		this.Designation=Designation;
		this.PrixPonderation = PrixPonderation;			
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	public float getPrixPonderation() {
		return PrixPonderation;
	}
	public void setPrixPonderation(float prixPonderation) {
		PrixPonderation = prixPonderation;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Designation == null) ? 0 : Designation.hashCode());
		result = prime * result + Float.floatToIntBits(PrixPonderation);
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Taille other = (Taille) obj;
		if (Designation == null) {
			if (other.Designation != null)
				return false;
		} else if (!Designation.equals(other.Designation))
			return false;
		if (Float.floatToIntBits(PrixPonderation) != Float.floatToIntBits(other.PrixPonderation))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return Designation ;
	}
	
	
}
