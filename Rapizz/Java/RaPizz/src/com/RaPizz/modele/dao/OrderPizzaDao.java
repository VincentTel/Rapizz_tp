package com.RaPizz.modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.RaPizz.modele.metier.Taille;

public class OrderPizzaDao extends DAO{


	public OrderPizzaDao(Connection con) {
		super(con);
	}	
	
	public List<Taille> getAllTaille()
	{
		List<Taille> result = new ArrayList<Taille>();
		try {
			
			String queryPrepared= "Select ID_Taille, Designation,PrixPonderation from taille ";
			PreparedStatement pr_stmt=connection.prepareStatement(queryPrepared);
			
			ResultSet rs=pr_stmt.executeQuery();
			
			while(rs.next())				
				result.add(new Taille(rs.getLong(1),rs.getString(2),rs.getFloat(3)));
		
		}catch(Exception err)
		{
			System.err.println(err);
		}
		return result;
	}
	
}
