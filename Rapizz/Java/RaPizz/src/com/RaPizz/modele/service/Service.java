package com.RaPizz.modele.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.RaPizz.modele.dao.LoginDao;
import com.RaPizz.modele.dao.ManagePizzaDao;
import com.RaPizz.modele.dao.OrderPizzaDao;
import com.RaPizz.modele.metier.Ingredient;
import com.RaPizz.modele.metier.Personne;
import com.RaPizz.modele.metier.Pizza;
import com.RaPizz.modele.metier.Taille;


public class Service {
	private Connection connection;
	private LoginDao loginDao;
	private ManagePizzaDao managePizzaDao;
	private OrderPizzaDao orderPizzaDao;
	public Service() {
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/RaPizzBDD","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginDao = new LoginDao(connection);
		managePizzaDao = new ManagePizzaDao(connection);
		orderPizzaDao = new OrderPizzaDao(connection);
		
	}		
	
	public void close() throws SQLException {
			// TODO Auto-generated method stub
			if(connection != null){
				connection.close();
			}
	}
		
	public Personne GetLoginFor(String Username, String Password)
	{
		return loginDao.GetLogin(Username, Password);
	}
	
	public Object GetRole(Personne p)
	{
		return loginDao.GetRole(p);
	}
	
	public List<Pizza> getAllPizzaManage()
	{
		return managePizzaDao.getAllPizzaManage();
	}

	public List<Ingredient> getAllIngredient()
	{
		return managePizzaDao.getAllIngredient();
	}
	
	public void AddPizza(Pizza p)
	{
		managePizzaDao.AddPizza(p);
	}

	public void DelPizza(Pizza p)
	{
		managePizzaDao.DelPizza(p);
	}
	
	public void UpdatePizza(Pizza p)
	{
		managePizzaDao.UpdatePizza(p);
	}
	
	public List<Taille> getAllTaille()
	{
		return orderPizzaDao.getAllTaille();
	}
}
