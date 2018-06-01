package com.RaPizz.modele.gui;

import java.util.ArrayList;
import java.util.List;

import com.RaPizz.modele.metier.Ingredient;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class ManagePizza {
	private SimpleLongProperty IdPizzaProperty;
	private SimpleObjectProperty<Image> PizzaImage;
	private SimpleStringProperty DesignationProperty;
	private SimpleFloatProperty PrixProperty;
	private SimpleObjectProperty<List<Ingredient>> ListIngredientProperty;

	public ManagePizza()
	{
		PizzaImage = new SimpleObjectProperty<Image>(new Image("com/RaPizz/images/click-icon.png"));
		DesignationProperty = new SimpleStringProperty("");
		PrixProperty = new SimpleFloatProperty(0);
		ListIngredientProperty = new SimpleObjectProperty<List<Ingredient>>(new ArrayList<Ingredient>());
		IdPizzaProperty = new SimpleLongProperty();
	}
	
	public void modeleInit()
	{
		PizzaImage.setValue(new Image("com/RaPizz/images/click-icon.png"));
		DesignationProperty.setValue("");
		PrixProperty.setValue(0);
		ListIngredientProperty = new SimpleObjectProperty<List<Ingredient>>(new ArrayList<Ingredient>());
		IdPizzaProperty.setValue(null);
	}

	public SimpleLongProperty getIdPizzaProperty() {
		return IdPizzaProperty;
	}

	public SimpleObjectProperty<Image> getPizzaImage() {
		return PizzaImage;
	}
	
	public SimpleStringProperty getDesignationProperty() {
		return DesignationProperty;
	}

	public SimpleFloatProperty getPrixProperty() {
		return PrixProperty;
	}

	public SimpleObjectProperty<List<Ingredient>> getListIngredientProperty() {
		return ListIngredientProperty;
	}
	
}
