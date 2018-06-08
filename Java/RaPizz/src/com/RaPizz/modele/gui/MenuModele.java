package com.RaPizz.modele.gui;

import com.RaPizz.modele.metier.Client;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class MenuModele {
	private SimpleStringProperty LastNameProperty;
	private SimpleStringProperty FirstNameProperty;
	private SimpleObjectProperty<Image> ProfilImageProperty;
	private SimpleObjectProperty<Client> ClientProperty;
	
	public MenuModele ()
	{
		LastNameProperty = new SimpleStringProperty("");
		FirstNameProperty = new SimpleStringProperty("");
		ProfilImageProperty = new SimpleObjectProperty<Image>(null);
		ClientProperty= new SimpleObjectProperty<Client>(null);
	}
	public void modeleInit()
	{
		LastNameProperty.setValue("");
		LastNameProperty.setValue("");
		ProfilImageProperty.setValue(null);
		ClientProperty.setValue(null);
		
	}

	public SimpleStringProperty getLastNameProperty() {
		return LastNameProperty;
	}

	public SimpleObjectProperty<Image> getProfilImageProperty() {
		return ProfilImageProperty;
	}
	public SimpleObjectProperty<Client> getClientProperty() {
		return ClientProperty;
	}
	public SimpleStringProperty getFirstNameProperty() {
		return FirstNameProperty;
	}
	
}
