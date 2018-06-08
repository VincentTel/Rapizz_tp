package com.RaPizz.modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Commande;

public class ManageCommandeDao extends DAO
{
	public ManageCommandeDao(Connection con)
	{
		super(con);
	}
/*
	public void AddCommande(Commande c)
	{
		try
		{
			String queryPrepared = "INSERT INTO `commande`(`PrixTotal`, `DateCommande`, `DateLivraison`, `ID_Livreur`, `ID_Client`, `Immat`) VALUES (?,?,?,?,?,?)";
			PreparedStatement pr_stmt = connection.prepareStatement(
					queryPrepared, Statement.RETURN_GENERATED_KEYS);
			pr_stmt.setFloat(1, c.getPrixTotal());
			pr_stmt.setDate(2, c.getDateCommande());
			pr_stmt.setDate(3, c.getDateLivraison());
			pr_stmt.setLong(4, c.getLivreur().getID());
			pr_stmt.setLong(5, c.getClient().getID());
			pr_stmt.setString(6, c.getVehicule().getImmat());
			pr_stmt.executeUpdate();
		}
		catch (Exception err)
		{
			System.err.println(err);
		}
	}
*/
	public void DelCommande(Commande c)
	{
		try
		{
			String queryPrepared = "DELETE FROM Commande where ID_Commande = ?";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);
			pr_stmt.setLong(1, c.getID_Commande());
			pr_stmt.executeUpdate();
		}
		catch (Exception err)
		{
			System.err.println(err);
		}
	}

	public void UpdateCommande(Commande c)
	{
		try
		{
			String queryPrepared = "UPDATE commande set PrixTotal = ?, DateCommande = ? , DateLivraison = ?, ID_Livreur = ?, ID_Client = ?, Immat = ? where ID_Commande = ?";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			pr_stmt.setFloat(1, c.getPrixTotal());
			pr_stmt.setTimestamp(2, c.getDateCommande());
			pr_stmt.setTimestamp(3, c.getDateLivraison());
			pr_stmt.setLong(4, c.getLivreur().getID());
			pr_stmt.setLong(5, c.getClient().getID());
			pr_stmt.setString(6, c.getVehicule().getImmat());
			pr_stmt.setLong(7, c.getID_Commande());
			pr_stmt.executeUpdate();
		}
		catch (Exception err)
		{
			System.err.println(err);
		}
	}
	
	public List<Commande> getAllOrderDelivery()
	{
		List<Commande> result = new ArrayList<>();

		try
		{
			String queryPrepared = "SELECT ID_Commande, PrixTotal,DateCommande,Id,Prenom,Nom, NumRue, Rue,Ville,CodePostal FROM commande LEFT JOIN client on commande.ID_Client = client.ID_Client LEFT JOIN personne on client.ID_Client=personne.ID WHERE DateLivraison is null";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			ResultSet rs = pr_stmt.executeQuery();
			while (rs.next())
			{
				Commande c = new Commande(rs.getLong(1), rs.getFloat(2),rs.getTimestamp(3), null,null, new Client(rs.getLong(4),rs.getString(5),rs.getString(6),"","","",null,rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),0,0),null);
				result.add(c);
			}
		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return result;
	}
	
	public List<Commande> getAllCommandeManage()
	{
		List<Commande> result = new ArrayList<>();

		try
		{
			String queryPrepared = "SELECT ID_Commande, PrixTotal,DateCommande, DateLivraison FROM commande ";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			ResultSet rs = pr_stmt.executeQuery();
			while (rs.next())
			{
				Commande c = new Commande(rs.getLong(1), rs.getFloat(2),rs.getTimestamp(3), rs.getTimestamp(4));
				result.add(c);
			}
		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return result;
	}
}