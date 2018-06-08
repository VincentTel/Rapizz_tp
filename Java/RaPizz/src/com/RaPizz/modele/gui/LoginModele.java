package com.RaPizz.modele.gui;

import javafx.beans.property.SimpleStringProperty;

public class LoginModele {
	
	private SimpleStringProperty userNameProperty;
	private SimpleStringProperty passwordProperty;
	
	public LoginModele ()
	{
		userNameProperty = new SimpleStringProperty();
		passwordProperty = new SimpleStringProperty();
	}

	public void modeleInit()
	{
		userNameProperty.setValue("");
		passwordProperty.setValue("");
	}
	public SimpleStringProperty getUserNameProperty() {
		return userNameProperty;
	}

	public void setUserNameProperty(SimpleStringProperty userNameProperty) {
		this.userNameProperty = userNameProperty;
	}

	public SimpleStringProperty getPasswordProperty() {
		return passwordProperty;
	}

	public void setPasswordProperty(SimpleStringProperty passwordProperty) {
		this.passwordProperty = passwordProperty;
	}
	
	
}
