package com.RaPizz.modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Commande;
import com.RaPizz.modele.metier.Personne;
import com.RaPizz.modele.metier.Vehicule;

public class StatsDAO extends DAO {

	public StatsDAO(Connection con) {
		super(con);
	}
	
	public String getCaPeriode(Timestamp debut, Timestamp fin)
	{
		String ca = "";

		try
		{
			String queryPrepared = "select sum(PrixTotal) from commande where DateCommande BETWEEN ? and ?";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			pr_stmt.setTimestamp(1, debut);

			pr_stmt.setTimestamp(2, fin);
			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())
				ca = rs.getString(1);

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return ca;
	}

	public String getBestClient()
	{
		String c = "";

		try
		{
			String queryPrepared = "Select p.Nom, p.Prenom from commande co LEFT JOIN client c on c.ID_Client = co.ID_Client LEFT JOIN personne p on c.ID_Client = p.ID order by prixtotal DESC LIMIT 0,1";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())
				c = rs.getString(1)+ " " +rs.getString(2) ;

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return c;
	}
	
	public String getIngredientFav()
	{
		String i = "";

		try
		{
			String queryPrepared = "SELECT i.Designation, COUNT(i.ID_Ingredient) as countIngredient FROM ingredient i LEFT JOIN composer comp on comp.ID_Ingredient = i.ID_Ingredient LEFT JOIN pizza p on comp.ID_Pizza = p.ID_Pizza LEFT JOIN realiser r on r.ID_Pizza = p.ID_Pizza GROUP BY i.Id_Ingredient ORDER by countIngredient DESC LIMIT 0,1;";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())
				i = rs.getString(1);

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return i;
	}
	
	
	public String getPizzaPlusDMD()
	{
		String p = "";

		try
		{
			String queryPrepared = "SELECT p.Designation FROM realiser r, pizza p WHERE p.ID_Pizza = r.ID_Pizza GROUP BY p.ID_Pizza ORDER BY SUM(r.quantite) DESC LIMIT 0,1;";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())
				p = rs.getString(1);

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return p;
	}
	
	public String getPizzaMoinsDMD()
	{
		String p = "";

		try
		{
			String queryPrepared = "SELECT p.Designation FROM realiser r, pizza p WHERE p.ID_Pizza = r.ID_Pizza GROUP BY p.ID_Pizza ORDER BY SUM(r.quantite) ASC LIMIT 0,1;";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())
				p = rs.getString(1);

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return p;
	}
	
	
	public String getPireLivreur()
	{
		String p = "";

		try
		{
			String queryPrepared = "SELECT Nom,prenom FROM livreur l LEFT JOIN personne p on l.ID_Livreur = p.ID ORDER by retard DESC LIMIT 0,1;";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())
				p = rs.getString(1) + " " +rs.getString(2) ;

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return p;
	}
	
	public String getCommandeMoyenne()
	{
		String p = "";

		try
		{
			String queryPrepared = "SELECT AVG(PrixTotal) FROM commande;";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())
				p = rs.getString(1);

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return p;
	}
	
	public List<Vehicule> getVehiculeUtilise()
	{
		List<Vehicule> p = new ArrayList<Vehicule>();

		try
		{
			String queryPrepared = "SELECT v.Immat, v.Type, v.Marque,v.Modele, count(v.Immat) as tauxUtil from commande c left JOIN vehicules v on v.Immat = c.Immat where c.Immat is not null GROUP by c.Immat order by tauxUtil asc;";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())	
			{
				Vehicule v = new Vehicule(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				v.setNbUtilisation(rs.getString(5));
				p.add(v);
			}

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return p;
	}

	public List<Client> getClientAboveAverage()
	{
		List<Client> p = new ArrayList<Client>();

		try
		{
			String queryPrepared = "SELECT DISTINCT c.ID_Client, p.Nom, p.Prenom  FROM commande c LEFT JOIN CLIENT cli on cli.ID_Client = c.ID_Client LEFT JOIN Personne p on p.id=cli.id_client where PrixTotal >= (SELECT AVG(PrixTotal) FROM commande);";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())	
			{
				Personne pers = new Personne(rs.getLong(1),rs.getString(2),rs.getString(3));
				Client c  = new Client(pers, null, 0,0);
				p.add(c);
			}
			

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return p;
	}
	
	
	public List<Commande> getCommandeByIdClient(long Id)
	{
		List<Commande> p = new ArrayList<Commande>();

		try
		{
			String queryPrepared = "select Id_Commande, DateCommande, DateLivraison, PrixTotal From commande where id_client = ? and DateLivraison is not null;";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			pr_stmt.setLong(1, Id);
			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())	
			{
				p.add(new Commande(rs.getLong(1),rs.getFloat(4),rs.getTimestamp(2),rs.getTimestamp(3)));
			}
			

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return p;
	}
	
}
