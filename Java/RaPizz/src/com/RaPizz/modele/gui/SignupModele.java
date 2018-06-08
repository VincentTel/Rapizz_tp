package com.RaPizz.modele.gui;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class SignupModele {
	
	private SimpleLongProperty ID_ClientProperty;
	private SimpleStringProperty prenomProperty;
	private SimpleStringProperty nomProperty;
	private SimpleStringProperty emailProperty;
	private SimpleStringProperty userNameProperty;
	private SimpleStringProperty passwordProperty;
	private SimpleStringProperty numrueProperty;
	private SimpleStringProperty rueProperty;
	private SimpleStringProperty villeProperty;
	private SimpleStringProperty codePostalProperty;
	private SimpleObjectProperty<Image> clientImageProperty;
	
	public SignupModele ()
	{
		ID_ClientProperty = new SimpleLongProperty();
		prenomProperty = new SimpleStringProperty();
		nomProperty = new SimpleStringProperty();
		emailProperty = new SimpleStringProperty();
		userNameProperty = new SimpleStringProperty();
		passwordProperty = new SimpleStringProperty();
		numrueProperty = new SimpleStringProperty();
		rueProperty = new SimpleStringProperty();
		villeProperty = new SimpleStringProperty();
		codePostalProperty = new SimpleStringProperty();
		clientImageProperty = new SimpleObjectProperty<Image>(new Image("com/RaPizz/images/click-icon.png"));
		
	}
	
	public void modeleInit()
	{
		ID_ClientProperty.setValue(null);
		prenomProperty.setValue("");
		nomProperty.setValue("");
		emailProperty.setValue("");
		userNameProperty.setValue("");
		passwordProperty.setValue("");
		numrueProperty.setValue("");
		rueProperty.setValue("");
		villeProperty.setValue("");
		codePostalProperty.setValue("");
		clientImageProperty.setValue(new Image("com/RaPizz/images/click-icon.png"));
		
	}

	public SimpleStringProperty getNumrueProperty() {
		return numrueProperty;
	}

	public SimpleStringProperty getRueProperty() {
		return rueProperty;
	}

	public SimpleStringProperty getVilleProperty() {
		return villeProperty;
	}

	public SimpleStringProperty getCodePostalProperty() {
		return codePostalProperty;
	}

	public SimpleLongProperty getID_ClientProperty() {
		return ID_ClientProperty;
	}

	public SimpleStringProperty getPrenomProperty() {
		return prenomProperty;
	}

	public SimpleStringProperty getNomProperty() {
		return nomProperty;
	}

	public SimpleStringProperty getEmailProperty() {
		return emailProperty;
	}

	public SimpleStringProperty getUserNameProperty() {
		return userNameProperty;
	}
	public SimpleStringProperty getPasswordProperty() {
		return passwordProperty;
	}

	public SimpleObjectProperty<Image> getClientImageProperty() {
		return clientImageProperty;
	}

	
	
}
