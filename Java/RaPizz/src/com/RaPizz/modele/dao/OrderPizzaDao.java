package com.RaPizz.modele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import com.RaPizz.modele.gui.OderItemTemplate;
import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Taille;
import com.mysql.jdbc.Statement;

public class OrderPizzaDao extends DAO{


	public OrderPizzaDao(Connection con) {
		super(con);
	}	 
	
	public void addOrder(List<OderItemTemplate> orders, float orderTotal, Client client)
	{
		
		try {
			
			String queryPrepared= "INSERT INTO `commande`(`PrixTotal`, `DateCommande`, `ID_Client`) VALUES (?, ?, ?)";
			PreparedStatement pr_stmt = connection.prepareStatement(queryPrepared, Statement.RETURN_GENERATED_KEYS);
			pr_stmt.setFloat(1, orderTotal);
			pr_stmt.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
			pr_stmt.setLong(3,client.getID());
			
			if(pr_stmt.executeUpdate()>0)
			{
				ResultSet generatedKeys = pr_stmt.getGeneratedKeys();
				while(generatedKeys.next())
				{					
					queryPrepared= "INSERT INTO `realiser`(`quantite`, `ID_Pizza`,`ID_Taille`,`ID_Commande`) VALUES (?, ?, ?, ?); ";
					String queryPrepared2= "UPDATE CLIENT SET PIZZAGRATUITE = ? , SOLDE = ?  where ID_CLIENT = ? ;";
					pr_stmt = connection.prepareStatement(queryPrepared);						
					PreparedStatement pr_stmt2 = connection.prepareStatement(queryPrepared2);	
					
					for(OderItemTemplate o : orders)
					{
						pr_stmt.setInt(1,o.getQt());
						pr_stmt.setLong(2,o.getPizza().getID_Pizza());
						pr_stmt.setLong(3,o.getPizza().getSize().getId());
						pr_stmt.setLong(4, generatedKeys.getLong(1));	
						pr_stmt.executeUpdate();
						
						for(int i = 0 ; i< o.getQt() ; i++) {
							pr_stmt2.setInt(1,client.getPizzaGratuite().getValue() + 1);
							client.setPizzaGratuite(client.getPizzaGratuite().getValue() + 1);
							if(client.getPizzaGratuite().getValue() == 10)
							{
								pr_stmt2.setFloat(2,client.getSolde().getValue());
								client.setSolde(client.getSolde().getValue());
								client.setPizzaGratuite(0);
							}
							else
							{
								pr_stmt2.setFloat(2,client.getSolde().getValue() - o.getPizza().getPrix().getValue());
								client.setSolde(client.getSolde().getValue() - o.getPizza().getPrix().getValue());
							}
							pr_stmt2.setLong(3,client.getID());
							pr_stmt2.executeUpdate();
						}
					}
					
				}
			}
		}catch(Exception err)
		{
			System.err.println(err);
		}
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
