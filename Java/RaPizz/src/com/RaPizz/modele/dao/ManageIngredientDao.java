package com.RaPizz.modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.RaPizz.modele.metier.Ingredient;

public class ManageIngredientDao  extends DAO{


	public ManageIngredientDao(Connection con) {
		super(con);
	}	
	
	
	public void AddIngredient(Ingredient i)
	{
		try {
			
			String queryPrepared= "INSERT INTO `Ingredient`(`Designation`) VALUES (?)";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			pr_stmt.setString(1, i.getDesignation());
			pr_stmt.executeUpdate();
		
		}catch(Exception err)
		{
			System.err.println(err);
		}
	}
	
	public void DelIngredient(Ingredient i)
	{
		try {
			
			String queryPrepared= "DELETE FROM Ingredient where ID_Ingredient = ?";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			pr_stmt.setLong(1, i.getID_Ingredient());	
			pr_stmt.executeUpdate();
		
		}catch(Exception err)
		{
			System.err.println(err);
		}
	}
	
	public void UpdateIngredient(Ingredient i)
	{
		try {
			
			String queryPrepared= "UPDATE Ingredient set Designation = ? where ID_Ingredient = ?";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			pr_stmt.setString(1, i.getDesignation());
			pr_stmt.setLong(2, i.getID_Ingredient());

			pr_stmt.executeUpdate();
		
		}catch(Exception err)
		{
			System.err.println(err);
		}
		
	}
	
	public List<Ingredient> getAllIngredientManage()
	{
		List<Ingredient> result = new ArrayList<Ingredient>();

		try {
			
			String queryPrepared= "Select ID_Ingredient, Designation from ingredient ";
			PreparedStatement pr_stmt=connection.prepareStatement(queryPrepared);
			
			ResultSet rs=pr_stmt.executeQuery();
			
			while(rs.next())				
				result.add(new Ingredient(rs.getLong(1),rs.getString(2)));
		
		}catch(Exception err)
		{
			System.err.println(err);
		}
		
		return result;
	}
	
}
