package com.RaPizz.modele.dao;


import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.RaPizz.modele.metier.Adresse;
import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Livreur;
import com.RaPizz.modele.metier.Personne;

import javafx.scene.image.Image;

public class LoginDao extends DAO{


	public LoginDao(Connection con) {
		super(con);
	}	
	
	public Object GetRole(Personne pers)
	{
		try {
			
			String queryPrepared= "Select numrue,rue,ville,codepostal,solde,PIZZAGRATUITE FROM client LEFT JOIN personne on client.id_client=personne.id  where id_client = ? ";
			PreparedStatement pr_stmt=connection.prepareStatement(queryPrepared);
			pr_stmt.setString(1,pers.getID().toString());	
				
			ResultSet rs=pr_stmt.executeQuery();
			
			if(rs.isBeforeFirst())
			{
				Client c = null;
				while(rs.next()) 
				{
					Adresse a = new Adresse(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
					c = new Client(pers,a,rs.getFloat(5),rs.getInt(6));
				}
				return c;
			}
			else 
			{
				queryPrepared= "Select salaire,dateembauche FROM livreur where id_livreur = ? ";
				pr_stmt=connection.prepareStatement(queryPrepared);
				pr_stmt.setString(1,pers.getID().toString());	
					
				rs=pr_stmt.executeQuery();
				
				Livreur l = null;
				while(rs.next())
				{
					l = new Livreur(pers,rs.getFloat(1),rs.getDate(2));
				}
				return l;
			}		
		}catch(Exception err)
		{
			System.err.println(err);
		}
		
		return pers;
	}
	
	public Personne GetLogin(String Username, String Password)
	{
		Personne pers = null;
		try {
			
			String queryPrepared= "Select id,prenom,nom,username,password,email,photo from personne where username = ? and password = ? ";
			PreparedStatement pr_stmt=connection.prepareStatement(queryPrepared);
			pr_stmt.setString(1,Username);	
			pr_stmt.setString(2,Password);	
				
			ResultSet rs=pr_stmt.executeQuery();
			
			while(rs.next()) 
			{
					Image img ;
					if(rs.getBlob(7) != null)
						img = new Image(new ByteArrayInputStream(rs.getBlob(7).getBytes(1,(int)rs.getBlob(7).length())));
					else
						img = new Image("com/RaPizz/images/profile-icon.png");
				
					pers = new Personne(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),img);	
			}
		
		}catch(Exception err)
		{
			System.err.println(err);
		}
		
		return pers;			
	}
	
}
