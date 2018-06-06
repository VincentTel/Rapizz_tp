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

	public SimpleListProperty<OderItemTemplate> getListPizza() {
		return ListPizza;
	}

	public SimpleFloatProperty getTotal() {
		return Total;
	}
	
}
