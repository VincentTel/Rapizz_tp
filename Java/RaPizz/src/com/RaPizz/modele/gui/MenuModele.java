package com.RaPizz.modele.gui;

import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Livreur;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class MenuModele {
	private SimpleStringProperty LastNameProperty;
	private SimpleStringProperty FirstNameProperty;
	private SimpleObjectProperty<Image> ProfilImageProperty;
	private SimpleObjectProperty<Client> ClientProperty;
	private SimpleObjectProperty<Livreur> LivreurProperty;
	
	public MenuModele ()
	{
		LastNameProperty = new SimpleStringProperty();
		FirstNameProperty = new SimpleStringProperty();
		ProfilImageProperty = new SimpleObjectProperty<Image>();
		ClientProperty= new SimpleObjectProperty<Client>();
		LivreurProperty= new SimpleObjectProperty<Livreur>();
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


	public SimpleObjectProperty<Livreur> getLivreurProperty() {
		return LivreurProperty;
	}

	public SimpleStringProperty getFirstNameProperty() {
		return FirstNameProperty;
	}
}
