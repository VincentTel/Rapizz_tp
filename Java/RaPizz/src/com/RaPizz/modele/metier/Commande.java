package com.RaPizz.modele.metier;

import java.sql.Timestamp;

public class Commande
{
	private long ID_Commande;
	private float prixTotal;
	private Timestamp dateCommande;
	private Timestamp dateLivraison;
	private Livreur livreur;
	private Client client;
	private Vehicule vehicule;

	public long getID_Commande()
	{
		return ID_Commande;
	}

	public void setID_Commande(long iD_Commande)
	{
		this.ID_Commande = iD_Commande;
	}

	public float getPrixTotal()
	{
		return this.prixTotal;
	}

	public void setPrixTotal(float prixTotal)
	{
		this.prixTotal = prixTotal;
	}

	public Timestamp getDateCommande()
	{
		return this.dateCommande;
	}

	public void setDateCommande(Timestamp dateCommande)
	{
		this.dateCommande = dateCommande;
	}

	public Timestamp getDateLivraison()
	{
		return this.dateLivraison;
	}

	public void setDateLivraison(Timestamp dateLivraison)
	{
		this.dateLivraison = dateLivraison;
	}

	public Livreur getLivreur()
	{
		return this.livreur;
	}

	public void setLivreur(Livreur livreur)
	{
		this.livreur = livreur;
	}

	public Client getClient()
	{
		return this.client;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

	public Vehicule getVehicule()
	{
		return this.vehicule;
	}

	public void setVehicule(Vehicule vehicule)
	{
		this.vehicule = vehicule;
	}

	public Commande(long ID, float prix, Timestamp dCommande, Timestamp dLivraison, Livreur l, Client c, Vehicule v)
	{
		this.ID_Commande = ID;
		this.prixTotal = prix;
		this.dateCommande = dCommande;
		this.dateLivraison = dLivraison;
		this.livreur = l;
		this.client = c;
		this.vehicule = v;
	}
	public Commande(long ID, float prix, Timestamp dCommande, Timestamp dLivraison)
	{
		this.ID_Commande = ID;
		this.prixTotal = prix;
		this.dateCommande = dCommande;
		this.dateLivraison = dLivraison;
		this.livreur = null;
		this.client = null;
		this.vehicule = null;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.dateCommande == null) ? 0 : this.dateCommande
						.hashCode());
		result = prime
				* result
				+ ((this.dateLivraison == null) ? 0 : this.dateLivraison
						.hashCode());
		result = prime * result + Float.floatToIntBits(this.prixTotal);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		if (this.dateCommande == null)
		{
			if (other.dateCommande != null)
				return false;
		}
		else if (!this.dateCommande.equals(other.dateCommande))
			return false;
		if (this.dateLivraison == null)
		{
			if (other.dateLivraison != null)
				return false;
		}
		else if (!this.dateLivraison.equals(other.dateLivraison))
			return false;
		if (Float.floatToIntBits(this.prixTotal) != Float
				.floatToIntBits(other.prixTotal))
			return false;
		return true;
	}
}
