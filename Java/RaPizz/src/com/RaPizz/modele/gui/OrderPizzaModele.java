package com.RaPizz.modele.gui;

import java.util.List;

import com.RaPizz.modele.metier.Ingredient;
import com.RaPizz.modele.metier.Pizza;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

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
