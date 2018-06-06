package com.RaPizz.modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.RaPizz.modele.metier.Vehicule;

public class ManageVehiculeDao extends DAO
{

	public ManageVehiculeDao(Connection con)
	{
		super(con);
	}

	public void AddVehicule(Vehicule v)
	{
		try
		{

			String queryPrepared = "INSERT INTO `vehicules`(`Immat`, `Marque`, `Modele`, `Type`) VALUES (?,?,?,?)";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);
			pr_stmt.setString(1, v.getImmat());
			pr_stmt.setString(2, v.getMarque());
			pr_stmt.setString(3, v.getModele());
			pr_stmt.setString(4, v.getType());
			pr_stmt.executeUpdate();

		}
		catch (Exception err)
		{
			System.err.println(err);
		}
	}

	public void DelVehicule(Vehicule v)
	{
		try
		{

			String queryPrepared = "DELETE FROM Vehicules where Immat = ?";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);
			pr_stmt.setString(1, v.getImmat());
			pr_stmt.executeUpdate();

		}
		catch (Exception err)
		{
			System.err.println(err);
		}
	}

	public void UpdateVehicule(Vehicule v)
	{
		try
		{

			String queryPrepared = "UPDATE Vehicules set Marque = ?, Modele = ? , Type = ?  where Immat = ?";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);
			pr_stmt.setString(4, v.getImmat());
			pr_stmt.setString(1, v.getMarque());
			pr_stmt.setString(2, v.getModele());
			pr_stmt.setString(3, v.getType());
			pr_stmt.executeUpdate();

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

	}

	public List<Vehicule> getAllVehiculeManage()
	{
		List<Vehicule> result = new ArrayList<Vehicule>();

		try
		{

			String queryPrepared = "Select Immat, Marque, Modele, Type from Vehicules";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);

			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())
				result.add(new Vehicule(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getString(4)));

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return result;
	}

	public Vehicule getVehicule(String ID)
	{
		Vehicule v = null;

		try
		{
			String queryPrepared = "Select Immat, Marque, Modele, Type from Vehicules where ID = ?";
			PreparedStatement pr_stmt = connection
					.prepareStatement(queryPrepared);
			pr_stmt.setString(1, ID);
			ResultSet rs = pr_stmt.executeQuery();

			while (rs.next())
				v = new Vehicule(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4));

		}
		catch (Exception err)
		{
			System.err.println(err);
		}

		return v;
	}
}
