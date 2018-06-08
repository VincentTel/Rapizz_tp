package com.RaPizz.modele.gui;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleListProperty;

public class OrderPizzaModele {
	
	private SimpleListProperty<OderItemTemplate> ListPizza;
	private SimpleFloatProperty Total;
	
	public OrderPizzaModele()
	{
		ListPizza = new SimpleListProperty<OderItemTemplate>();
		Total = new SimpleFloatProperty();		
	}
	public void modeleInit()
	{
		ListPizza.setValue(null);
		Total.setValue(0);
	
	}

	public SimpleListProperty<OderItemTemplate> getListPizza() {
		return ListPizza;
	}

	public SimpleFloatProperty getTotal() {
		return Total;
	}
	
}
