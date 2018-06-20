package com.RaPizz.modele.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.RaPizz.modele.dao.HomeDao;
import com.RaPizz.modele.dao.LoginDao;
import com.RaPizz.modele.dao.ManageClientDao;
import com.RaPizz.modele.dao.ManageCommandeDao;
import com.RaPizz.modele.dao.ManageIngredientDao;
import com.RaPizz.modele.dao.ManageLivreurDao;
import com.RaPizz.modele.dao.ManagePizzaDao;
import com.RaPizz.modele.dao.ManageVehiculeDao;
import com.RaPizz.modele.dao.OrderPizzaDao;
import com.RaPizz.modele.dao.StatsDAO;
import com.RaPizz.modele.gui.OderItemTemplate;
import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Commande;
import com.RaPizz.modele.metier.Ingredient;
import com.RaPizz.modele.metier.Livreur;
import com.RaPizz.modele.metier.Personne;
import com.RaPizz.modele.metier.Pizza;
import com.RaPizz.modele.metier.Taille;
import com.RaPizz.modele.metier.Vehicule;


public class Service {
	private Connection connection;
	private LoginDao loginDao;
	private ManagePizzaDao managePizzaDao;
	private OrderPizzaDao orderPizzaDao;
	private HomeDao homeDao;
	private ManageClientDao manageClientDao;
	private ManageLivreurDao manageLivreurDao;
	private ManageCommandeDao manageCommandeDao;
	private ManageVehiculeDao manageVehiculeDao;
	private ManageIngredientDao manageIngredientDao;
	private StatsDAO statsDAO;
	
	public Service() {
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/RaPizzBDD","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginDao = new LoginDao(connection);
		managePizzaDao = new ManagePizzaDao(connection);
		orderPizzaDao  = new OrderPizzaDao(connection);
		homeDao= new HomeDao(connection);
		manageClientDao = new ManageClientDao(connection);
		manageLivreurDao = new ManageLivreurDao(connection);
		manageCommandeDao = new ManageCommandeDao(connection);
		manageVehiculeDao = new ManageVehiculeDao(connection);
		manageIngredientDao = new ManageIngredientDao(connection);
		statsDAO = new StatsDAO(connection);
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

	public List<Vehicule> getAllVehiculeManage()
	{
		return manageVehiculeDao.getAllVehiculeManage();
	}
	
	public List<Client> getAllClientManage()
	{
		return manageClientDao.getAllClientManage();
	}	

	public List<Livreur> getAllLivreurManage()
	{
		return manageLivreurDao.getAllLivreurManage();
	}
	public List<Commande> getAllOrderDelivery()
	{
		return manageCommandeDao.getAllOrderDelivery();
	}
	public List<Commande> getAllCommandeManage()
	{
		return manageCommandeDao.getAllCommandeManage();
	}
	public void UpdateCommande(Commande c)
	{
		manageCommandeDao.UpdateCommande(c);
	}
	public List<Taille> getAllTaille()
	{
		return orderPizzaDao.getAllTaille();
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

	public void addOrder(List<OderItemTemplate> orders, float orderTotal, Client client)
	{
		 orderPizzaDao.addOrder(orders, orderTotal, client);
	}

	public void AddClient(Client c)
	{
		manageClientDao.AddClient(c);
	}

	public void DelClient(Client c)
	{
		manageClientDao.DelClient(c);
	}

	public void UpdateClient(Client c)
	{
		manageClientDao.UpdateClient(c);
	}
	public List<Pizza> getTopPizza()
	{
		return homeDao.getTopPizza();
	}

	public void AddLivreur(Livreur l)
	{
		manageLivreurDao.AddLivreur(l);
	}

	public void DelLivreur(Livreur l)
	{
		manageLivreurDao.DelLivreur(l);
	}

	public void UpdateLivreur(Livreur l)
	{
		manageLivreurDao.UpdateLivreur(l);
	}
	
	public void DelCommande(Commande c)
	{
		manageCommandeDao.DelCommande(c);
	}
	

	public void UpdateIngredient(Ingredient i)
	{
		manageIngredientDao.UpdateIngredient(i);
	}

	public void DelIngredient(Ingredient i)
	{
		manageIngredientDao.DelIngredient(i);
	}

	public void AddIngredient(Ingredient i)
	{
		manageIngredientDao.AddIngredient(i);
	}

	public void UpdateVehicule(Vehicule v)
	{
		manageVehiculeDao.UpdateVehicule(v);
	}

	public void DelVehicule(Vehicule v)
	{
		manageVehiculeDao.DelVehicule(v);
	}

	public void AddVehicule(Vehicule v)
	{
		manageVehiculeDao.AddVehicule(v);
	}
	

	public String getCaPeriode(Timestamp debut, Timestamp fin)
	{
		return statsDAO.getCaPeriode(debut, fin);		
	}

	public String getBestClient()
	{
		return statsDAO.getBestClient();
	}

	public String getIngredientFav()
	{
		return statsDAO.getIngredientFav();	
	}

	public String getPizzaPlusDMD()
	{
		return statsDAO.getPizzaPlusDMD();
		
	}

	public String getPizzaMoinsDMD()
	{
		return statsDAO.getPizzaMoinsDMD();
	}
	

	public String getPireLivreur()
	{
		return statsDAO.getPireLivreur();
	}
	

	public String getCommandeMoyenne()
	{
		return statsDAO.getCommandeMoyenne();		
	}

	public List<Vehicule> getVehiculeUtilise()
	{
		return statsDAO.getVehiculeUtilise();		
	}

	public List<Client> getClientAboveAverage()
	{
		return statsDAO.getClientAboveAverage();
	}

	public List<Commande> getCommandeByIdClient(long Id)
	{
		return statsDAO.getCommandeByIdClient(Id);
	}
}
