package com.RaPizz.modele.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.RaPizz.modele.metier.Client;
import com.mysql.jdbc.Statement;

import javafx.scene.image.Image;

public class ManageClientDao extends DAO
{

	public ManageClientDao(Connection con)
	{
		super(con);
	}

	public void AddClient(Client c)
	{
		try
		{
			String queryPrepared = "INSERT INTO `personne`(`Prenom`, `Nom`, `Username`, `Password`, `Email`, `Photo`) VALUES (?,?,?,?,?,?)";
			PreparedStatement pr_stmt = connection.prepareStatement(
					queryPrepared, Statement.RETURN_GENERATED_KEYS);
			pr_stmt.setString(1, c.getPrenom());
			pr_stmt.setString(2, c.getNom());
			pr_stmt.setString(3, c.getUserName());
			pr_stmt.setString(4, c.getPassword());
			pr_stmt.setString(5, c.getEmail());
			pr_stmt.setBlob(6, DAO.ConvertImage(c.getPhoto()));

			if (pr_stmt.executeUpdate() > 0)
			{
				ResultSet generatedKeys = pr_stmt.getGeneratedKeys();
				generatedKeys.next();

				queryPrepared = "INSERT INTO `client`(`NumRue`, `Rue`, `Ville`, `CodePostal`, `Solde`, `pizzaGratuite`, `ID_Client`) VALUES (?,?,?,?,?,?,?)";
				pr_stmt = connection.prepareStatement(queryPrepared);
				pr_stmt.setString(1, c.getAdr().getNumRue());
				pr_stmt.setString(2, c.getAdr().getRue());
				pr_stmt.setString(3, c.getAdr().getVille());
				pr_stmt.setString(4, c.getAdr().getCp());
				pr_stmt.setFloat(5, c.getSolde().getValue());
				pr_stmt.setInt(6, c.getPizzaGratuite().getValue());
				pr_stmt.setLong(7, generatedKeys.getLong(1));
				pr_stmt.executeUpdate();
			}
		}
		catch (Exception err)
		{
			System.err.println(err);
		}
	}

	public void DelClient(Client c)
	{
		try
		{
			String queryPrepared = "DELETE FROM Personne where ID = ?";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);
			pr_stmt.setLong(1, c.getID());
			pr_stmt.executeUpdate();
		}
		catch (Exception err)
		{
			System.err.println(err);
		}
	}

	public void UpdateClient(Client c)
	{
		try
		{
			String queryPrepared = "UPDATE Personne set Prenom = ?, Nom = ? , Username = ?, Password = ?, Email = ?, Photo = ? where ID = ?";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);
			pr_stmt.setString(1, c.getPrenom());
			pr_stmt.setString(2, c.getNom());
			pr_stmt.setString(3, c.getUserName());
			pr_stmt.setString(4, c.getPassword());
			pr_stmt.setString(5, c.getEmail());
			pr_stmt.setBlob(6, ConvertImage(c.getPhoto()));
			pr_stmt.setLong(7, c.getID());

			if (pr_stmt.executeUpdate() > 0)
			{
				queryPrepared = "UPDATE Client set NumRue = ?, Rue = ? , Ville = ?, CodePostal = ?, Solde = ?, PizzaGratuite = ? where ID_Client = ?";
				pr_stmt = connection.prepareStatement(queryPrepared);
				pr_stmt.setString(1, c.getAdr().getNumRue());
				pr_stmt.setString(2, c.getAdr().getRue());
				pr_stmt.setString(3, c.getAdr().getVille());
				pr_stmt.setString(4, c.getAdr().getCp());
				pr_stmt.setFloat(5, c.getSolde().getValue());
				pr_stmt.setInt(6, c.getPizzaGratuite().getValue());
				pr_stmt.setLong(7, c.getID());
				pr_stmt.executeUpdate();
			}
		}
		catch (Exception err)
		{
			System.err.println(err);
		}
	}

	public List<Client> getAllClientManage()
	{
		List<Client> result = new ArrayList<>();

		try
		{
			String queryPrepared = "SELECT NumRue, Rue, Ville, CodePostal, Photo, Solde, pizzaGratuite, ID, "
					+ "Prenom, Nom, Username, Password, Email FROM `client` JOIN `personne`"
					+ " where ID_Client = personne.ID";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);

			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())
			{
				Image img;
				if (rs.getBlob(5) != null)
					img = new Image(new ByteArrayInputStream(rs.getBlob(5)
							.getBytes(1, (int) rs.getBlob(5).length())));
				else
					img = new Image("com/RaPizz/images/profile-icon.png");
				Client c = new Client(rs.getLong(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), img, rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getFloat(6), rs.getInt(7));
				result.add(c);
			}
		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return result;
	}

	public Client getClient(long ID)
	{
		Client c = null;

		try
		{
			String queryPrepared = "SELECT NumRue, Rue, Ville, CodePostal, Photo, Solde, pizzaGratuite, ID, "
					+ "Prenom, Nom, Username, Password, Email FROM `client` JOIN `personne`"
					+ " where ID_Client = personne.ID AND ID=?";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);
			pr_stmt.setLong(1, ID);
			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())
			{
				Image img;
				if (rs.getBlob(5) != null)
					img = new Image(new ByteArrayInputStream(rs.getBlob(5)
							.getBytes(1, (int) rs.getBlob(5).length())));
				else
					img = new Image("com/RaPizz/images/profile-icon.png");
				c = new Client(rs.getLong(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), img, rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getFloat(6), rs.getInt(7));
			}
		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return c;
	}
}