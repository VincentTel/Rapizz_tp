package com.RaPizz.modele.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.RaPizz.modele.metier.Livreur;
import com.mysql.jdbc.Statement;

import javafx.scene.image.Image;

public class ManageLivreurDao extends DAO
{

	public ManageLivreurDao(Connection con)
	{
		super(con);
	}

	public void AddLivreur(Livreur l)
	{
		try
		{
			String queryPrepared = "INSERT INTO `personne`(`Prenom`, `Nom`, `Username`, `Password`, `Email`, `Photo`) VALUES (?,?,?,?,?,?)";
			PreparedStatement pr_stmt = connection.prepareStatement(
					queryPrepared, Statement.RETURN_GENERATED_KEYS);
			pr_stmt.setString(1, l.getPrenom());
			pr_stmt.setString(2, l.getNom());
			pr_stmt.setString(3, l.getUserName());
			pr_stmt.setString(4, l.getPassword());
			pr_stmt.setString(5, l.getEmail());
			pr_stmt.setBlob(6, DAO.ConvertImage(l.getPhoto()));

			if (pr_stmt.executeUpdate() > 0)
			{
				ResultSet generatedKeys = pr_stmt.getGeneratedKeys();
				generatedKeys.next();

				queryPrepared = "INSERT INTO `livreur`(`DateEmbauche`, `Salaire`, `ID_Livreur`) VALUES (?,?,?)";
				pr_stmt = connection.prepareStatement(queryPrepared);
				pr_stmt.setDate(1, l.getLocalDate());
				pr_stmt.setFloat(2, l.getSalaire());
				pr_stmt.setLong(3, generatedKeys.getLong(1));
				pr_stmt.executeUpdate();
			}
		}
		catch (Exception err)
		{
			System.err.println(err);
		}
	}

	public void DelLivreur(Livreur l)
	{
		try
		{
			String queryPrepared = "DELETE FROM Personne where ID = ?";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);
			pr_stmt.setLong(1, l.getID());
			pr_stmt.executeUpdate();
		}
		catch (Exception err)
		{
			System.err.println(err);
		}
	}

	public void UpdateLivreur(Livreur l)
	{
		try
		{
			String queryPrepared = "UPDATE Personne set Prenom = ?, Nom = ? , Username = ?, Password = ?, Email = ?, Photo = ? where ID = ?";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);
			pr_stmt.setString(1, l.getPrenom());
			pr_stmt.setString(2, l.getNom());
			pr_stmt.setString(3, l.getUserName());
			pr_stmt.setString(4, l.getPassword());
			pr_stmt.setString(5, l.getEmail());
			pr_stmt.setBlob(6, ConvertImage(l.getPhoto()));
			pr_stmt.setLong(7, l.getID());

			if (pr_stmt.executeUpdate() > 0)
			{
				queryPrepared = "UPDATE Livreur set DateEmbauche = ?, Salaire = ?, Retard = ? where ID_Livreur = ?";
				pr_stmt = connection.prepareStatement(queryPrepared);
				pr_stmt.setDate(1, l.getLocalDate());
				pr_stmt.setFloat(2, l.getSalaire());
				pr_stmt.setLong(3, l.getRetard());
				pr_stmt.setLong(4, l.getID());
				pr_stmt.executeUpdate();
			}
		}
		catch (Exception err)
		{
			System.err.println(err);
		}
	}

	public List<Livreur> getAllLivreurManage()
	{
		List<Livreur> result = new ArrayList<>();

		try
		{
			String queryPrepared = "SELECT DateEmbauche, Salaire, Photo, ID, "
					+ "Prenom, Nom, Username, Password, Email, Retard FROM `livreur` JOIN `personne`"
					+ " where ID_Livreur = personne.ID";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);

			ResultSet rs = pr_stmt.executeQuery();
			while (rs.next())
			{
				Image img;
				if (rs.getBlob(3) != null)
					img = new Image(new ByteArrayInputStream(rs.getBlob(3)
							.getBytes(1, (int) rs.getBlob(3).length())));
				else
					img = new Image("com/RaPizz/images/profile-icon.png");
				Livreur l = new Livreur(rs.getLong(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), img, rs.getFloat(2), rs.getDate(1));
				l.setRetard(rs.getInt(10));
				result.add(l);
			}
		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return result;
	}

	public Livreur getLivreur(long ID)
	{
		Livreur l = null;
		try
		{
			String queryPrepared = "SELECT DateEmbauche, Salaire, Photo, ID, "
					+ "Prenom, Nom, Username, Password, Email FROM `livreur` JOIN `personne`"
					+ " where ID_Livreur = personne.ID AND ID=?";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);
			pr_stmt.setLong(1, ID);
			ResultSet rs = pr_stmt.executeQuery();
			while (rs.next())
			{
				Image img;
				if (rs.getBlob(3) != null)
					img = new Image(new ByteArrayInputStream(rs.getBlob(3)
							.getBytes(1, (int) rs.getBlob(3).length())));
				else
					img = new Image("com/RaPizz/images/profile-icon.png");
				l = new Livreur(rs.getLong(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), img, rs.getFloat(2), rs.getDate(1));
			}
		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return l;
	}
}