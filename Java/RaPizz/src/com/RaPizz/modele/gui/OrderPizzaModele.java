package com.RaPizz.modele.gui;

import com.RaPizz.modele.metier.Client;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;

public class OrderPizzaModele {
	
	private SimpleListProperty<OderItemTemplate> ListPizza;
	private SimpleObjectProperty<Client> client;
	private SimpleFloatProperty Total;
	
	public OrderPizzaModele()
	{
		ListPizza = new SimpleListProperty<OderItemTemplate>();
		Total = new SimpleFloatProperty();		
		client = new  SimpleObjectProperty<Client> ();
	}
	public void modeleInit()
	{
		ListPizza.setValue(null);
		Total.setValue(0);
		client.setValue(null);
	}

	public SimpleObjectProperty<Client> getClient() {
		return client;
	}
	
	public SimpleListProperty<OderItemTemplate> getListPizza() {
		return ListPizza;
	}

	public SimpleFloatProperty getTotal() {
		return Total;
	}
	
}
