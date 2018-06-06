package com.RaPizz.modele.gui;

import javafx.beans.property.SimpleStringProperty;

public class ManageVehicule {
	private SimpleStringProperty ImmatProperty;
	private SimpleStringProperty MarqueProperty;
	private SimpleStringProperty ModeleProperty;
	private SimpleStringProperty TypeProperty;
	

	public ManageVehicule()
	{
		ImmatProperty = new SimpleStringProperty("");
		MarqueProperty = new SimpleStringProperty("");
		ModeleProperty = new SimpleStringProperty("");
		TypeProperty = new SimpleStringProperty("");
	}
	
	public void modeleInit()
	{
		ImmatProperty.setValue("");
		MarqueProperty.setValue("");
		ModeleProperty.setValue("");
		TypeProperty.setValue("");
	}

	public SimpleStringProperty getImmatProperty() {
		return ImmatProperty;
	}

	public SimpleStringProperty getMarqueProperty() {
		return MarqueProperty;
	}

	public SimpleStringProperty getModeleProperty() {
		return ModeleProperty;
	}

	public SimpleStringProperty getTypeProperty() {
		return TypeProperty;
	}	
}
