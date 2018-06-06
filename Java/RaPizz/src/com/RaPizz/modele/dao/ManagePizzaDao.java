package com.RaPizz.modele.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.RaPizz.modele.metier.Ingredient;
import com.RaPizz.modele.metier.Pizza;
import com.mysql.jdbc.Statement;
import javafx.scene.image.Image;

public class ManagePizzaDao  extends DAO{


	public ManagePizzaDao(Connection con) {
		super(con);
	}	
	
	
	public void AddPizza(Pizza p)
	{
		try {
			
			String queryPrepared= "INSERT INTO `pizza`(`Designation`, `PrixBase`, `Photo`) VALUES (?,?,?)";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared, Statement.RETURN_GENERATED_KEYS);
			pr_stmt.setString(1, p.getDesignation());
			pr_stmt.setFloat(2, p.getPrixBase());
			pr_stmt.setBlob(3, DAO.ConvertImage(p.getPhoto()));
			
			if(pr_stmt.executeUpdate()>0)
			{
				ResultSet generatedKeys = pr_stmt.getGeneratedKeys();
				while(generatedKeys.next())
				{					
					queryPrepared= "INSERT INTO `composer`(`ID_Ingredient`, `ID_Pizza`) VALUES (?, ?)";
					pr_stmt = connection.prepareStatement(queryPrepared);			
					for(Ingredient i : p.getComposition())
					{
						pr_stmt.setLong(1,i.getID_Ingredient());
						pr_stmt.setLong(2, generatedKeys.getLong(1));
						pr_stmt.executeUpdate();
					}
					
				}
			}
		
		}catch(Exception err)
		{
			System.err.println(err);
		}
	}
	
	public void DelPizza(Pizza p)
	{
		try {
			
			String queryPrepared= "DELETE FROM Pizza where ID_Pizza = ?";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			pr_stmt.setLong(1,p.getID_Pizza());	
			pr_stmt.executeUpdate();
		
		}catch(Exception err)
		{
			System.err.println(err);
		}
	}
	
	public void UpdatePizza(Pizza p)
	{
		try {
			
			String queryPrepared= "UPDATE Pizza set Designation = ?, PrixBase = ? , Photo = ?  where ID_Pizza = ?";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared);
			pr_stmt.setString(1,p.getDesignation());
			pr_stmt.setFloat(2, p.getPrixBase());
			pr_stmt.setBlob(3, ConvertImage(p.getPhoto()));
			pr_stmt.setLong(4,p.getID_Pizza());	
			
			if(pr_stmt.executeUpdate()>0)
			{
				queryPrepared= "DELETE From composer where ID_Pizza = ?";
				pr_stmt = connection.prepareStatement(queryPrepared);		
				pr_stmt.setLong(1,p.getID_Pizza());				
				pr_stmt.executeUpdate();
				
				queryPrepared= "INSERT INTO `composer`(`ID_Ingredient`, `ID_Pizza`) VALUES (?, ?)";
				pr_stmt = connection.prepareStatement(queryPrepared);	
				for(Ingredient i : p.getComposition())
				{
					pr_stmt.setLong(1, i.getID_Ingredient());
					pr_stmt.setLong(2, p.getID_Pizza());
					pr_stmt.executeUpdate();
				}
				
				
			}
		
		}catch(Exception err)
		{
			System.err.println(err);
		}
		
	}
	
	public List<Ingredient> getAllIngredient()
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
	
	public List<Pizza> getAllPizzaManage()
	{
		List<Pizza> result = new ArrayList<>();
		
		try {
			
			String queryPrepared= "Select ID_Pizza, Designation, PrixBase,photo from pizza ";
			PreparedStatement pr_stmt=connection.prepareStatement(queryPrepared);
			
			ResultSet rs=pr_stmt.executeQuery();
			
			while(rs.next()) 
			{
				Image img ;
					if(rs.getBlob(4) != null)
						img = new Image(new ByteArrayInputStream(rs.getBlob(4).getBytes(1,(int)rs.getBlob(4).length())));
					else
						img = new Image("com/RaPizz/images/profile-icon.png");
					
				Pizza piz = new Pizza(rs.getLong(1),rs.getString(2),rs.getLong(3),img);
				result.add(piz);
			}
			
			if(!result.isEmpty())
			{
				for(Pizza p : result)
				{
					queryPrepared= "Select ID_Ingredient, designation from ingredient where ID_Ingredient in (select ID_Ingredient from composer where ID_Pizza = ? )";
					pr_stmt=connection.prepareStatement(queryPrepared);
					pr_stmt.setLong(1,p.getID_Pizza());						
					rs=pr_stmt.executeQuery();
					
					while(rs.next())
						p.getComposition().add(new Ingredient(rs.getInt(1),rs.getString(2)));
				}
			}
		
		}catch(Exception err)
		{
			System.err.println(err);
		}
		
		return result;
	}
}
