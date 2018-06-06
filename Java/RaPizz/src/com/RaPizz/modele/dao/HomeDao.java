package com.RaPizz.modele.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.RaPizz.modele.metier.Ingredient;
import com.RaPizz.modele.metier.Pizza;

import javafx.scene.image.Image;

public class HomeDao extends DAO {

	public HomeDao(Connection con) {
		super(con);
		
	}
	
	public List<Pizza> getTopPizza()
	{
		List<Pizza> result = new ArrayList<Pizza>();
		
		try {
			
			String queryPrepared= "Select ID_Pizza, Designation, PrixBase,photo, ( Select GROUP_CONCAT(ID_Ingredient,',',Designation) from ingredient where ID_Ingredient in (select ID_Ingredient from composer where ID_Pizza = p.ID_Pizza) ) as ingredient " + 
					" from pizza p where ID_Pizza in (SELECT ID_Pizza from realiser GROUP by ID_Pizza ORDER BY count(ID_Pizza) DESC) ";
			PreparedStatement pr_stmt=connection.prepareStatement(queryPrepared);
			
			ResultSet rs=pr_stmt.executeQuery();

			int i = 0;
			while(rs.next()) 
			{
				if(i<=2)
				{
					Image img ;
						if(rs.getBlob(4) != null)
							img = new Image(new ByteArrayInputStream(rs.getBlob(4).getBytes(1,(int)rs.getBlob(4).length())));
						else
							img = new Image("com/RaPizz/images/profile-icon.png");
						
					
					List<Ingredient> listingredients = new ArrayList<Ingredient>();
					String[] rawIngredients = rs.getString(5).split(",");					
					for(int x = 0 ; x < rawIngredients.length;x++)
						listingredients.add(new Ingredient(Long.parseLong(rawIngredients[x]),rawIngredients[++x]));
					
					Pizza piz = new Pizza(rs.getLong(1),rs.getString(2),rs.getLong(3),img,listingredients);					
					result.add(piz);
					i++;
				}
			}	
		
		}catch(Exception err)
		{
			System.err.println(err);
		}
		//System.out.println(result.get(0).getDesignation() + "  "+ result.get(1).getDesignation()+ "  "+ result.get(2).getDesignation());
		return result; 
	}

}
