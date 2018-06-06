package com.RaPizz.modele.gui;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class ManageIngredient
{
	private SimpleLongProperty ID_IngredientProperty;
	private SimpleStringProperty DesignationProperty;

	public ManageIngredient()
	{
		DesignationProperty = new SimpleStringProperty("");
		ID_IngredientProperty = new SimpleLongProperty();
	}

	public void modeleInit()
	{
		DesignationProperty.setValue("");
		ID_IngredientProperty.setValue(null);
	}

	public SimpleLongProperty getID_IngredientProperty()
	{
		return ID_IngredientProperty;
	}

	public SimpleStringProperty getDesignationProperty()
	{
		return DesignationProperty;
	}
}
