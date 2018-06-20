package com.RaPizz.modele.gui;

import java.util.List;

import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Vehicule;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class StatsModele {	
	
	private SimpleStringProperty caProperty;

	private SimpleStringProperty BestClientProperty;

	private SimpleStringProperty IngredientFavProperty;

	private SimpleStringProperty PizzaPlusDMDProperty;

	private SimpleStringProperty PizzaMoinsDMDProperty;
	
	private SimpleStringProperty PireLivreurProperty;
	
	private SimpleStringProperty CommandeMoyenneProperty;	

	private SimpleObjectProperty<List<Client>> clientListProperty;

	private SimpleObjectProperty<List<Vehicule>> vehiculeListProperty;
	
	public StatsModele()
	{
		caProperty = new SimpleStringProperty();
		
		BestClientProperty = new SimpleStringProperty();
		
		IngredientFavProperty = new SimpleStringProperty();
		
		PizzaPlusDMDProperty = new SimpleStringProperty();
		
		PizzaMoinsDMDProperty = new SimpleStringProperty();

		PireLivreurProperty = new SimpleStringProperty();

		CommandeMoyenneProperty = new SimpleStringProperty();

		clientListProperty = new SimpleObjectProperty<List<Client>>();

		vehiculeListProperty = new SimpleObjectProperty<List<Vehicule>>();		
	}
	
	public void modeleInit()
	{
		caProperty.setValue("");
		BestClientProperty.setValue("");
		IngredientFavProperty.setValue("");
		PizzaPlusDMDProperty.setValue("");
		PizzaMoinsDMDProperty.setValue("");
		PireLivreurProperty.setValue("");
		CommandeMoyenneProperty.setValue(null);
		clientListProperty.setValue(null);
		vehiculeListProperty.setValue(null);
	}

	public SimpleStringProperty getCaProperty() {
		return caProperty;
	}

	public SimpleStringProperty getBestClientProperty() {
		return BestClientProperty;
	}

	public SimpleStringProperty getIngredientFavProperty() {
		return IngredientFavProperty;
	}

	public SimpleStringProperty getPizzaPlusDMDProperty() {
		return PizzaPlusDMDProperty;
	}

	public SimpleStringProperty getPizzaMoinsDMDProperty() {
		return PizzaMoinsDMDProperty;
	}

	public SimpleStringProperty getPireLivreurProperty() {
		return PireLivreurProperty;
	}

	public SimpleStringProperty getCommandeMoyenneProperty() {
		return CommandeMoyenneProperty;
	}

	public SimpleObjectProperty<List<Client>> getClientListProperty() {
		return clientListProperty;
	}

	public SimpleObjectProperty<List<Vehicule>> getVehiculeListProperty() {
		return vehiculeListProperty;
	}
	
}
