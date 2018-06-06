package com.RaPizz.modele.gui;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class ManageClient
{
	private SimpleLongProperty IdClientProperty;
	private SimpleObjectProperty<Image> ClientImage;
	private SimpleStringProperty PrenomProperty;
	private SimpleStringProperty NomProperty;
	private SimpleStringProperty UsernameProperty;
	private SimpleStringProperty PasswordProperty;
	private SimpleStringProperty EmailProperty;
	private SimpleStringProperty NumRueProperty;
	private SimpleStringProperty RueProperty;
	private SimpleStringProperty VilleProperty;
	private SimpleStringProperty CodePostalProperty;
	private SimpleIntegerProperty PizzaGratuiteProperty;
	private SimpleFloatProperty SoldeProperty;

	public ManageClient()
	{
		ClientImage = new SimpleObjectProperty<Image>(new Image(
				"com/RaPizz/images/click-icon.png"));
		PrenomProperty = new SimpleStringProperty("");
		NomProperty = new SimpleStringProperty("");
		UsernameProperty = new SimpleStringProperty("");
		PasswordProperty = new SimpleStringProperty("");
		EmailProperty = new SimpleStringProperty("");
		NumRueProperty = new SimpleStringProperty("");
		RueProperty = new SimpleStringProperty("");
		VilleProperty = new SimpleStringProperty("");
		CodePostalProperty = new SimpleStringProperty("");
		PizzaGratuiteProperty = new SimpleIntegerProperty(0);
		SoldeProperty = new SimpleFloatProperty(0);
		IdClientProperty = new SimpleLongProperty();
	}

	public void modeleInit()
	{
		ClientImage.setValue(new Image("com/RaPizz/images/click-icon.png"));
		PrenomProperty.setValue("");
		NomProperty.setValue("");
		UsernameProperty.setValue("");
		PasswordProperty.setValue("");
		EmailProperty.setValue("");
		NumRueProperty.setValue("");
		RueProperty.setValue("");
		VilleProperty.setValue("");
		CodePostalProperty.setValue("");
		PizzaGratuiteProperty.setValue(0);
		SoldeProperty.setValue(0);
		IdClientProperty.setValue(null);
	}

	public SimpleLongProperty getIdClientProperty()
	{
		return IdClientProperty;
	}

	public SimpleObjectProperty<Image> getClientImage()
	{
		return ClientImage;
	}

	public SimpleStringProperty getPrenomProperty()
	{
		return PrenomProperty;
	}

	public SimpleStringProperty getNomProperty()
	{
		return NomProperty;
	}

	public SimpleStringProperty getUsernameProperty()
	{
		return UsernameProperty;
	}

	public SimpleStringProperty getPasswordProperty()
	{
		return PasswordProperty;
	}

	public SimpleStringProperty getEmailProperty()
	{
		return EmailProperty;
	}

	public SimpleStringProperty getNumRueProperty()
	{
		return NumRueProperty;
	}

	public SimpleStringProperty getRueProperty()
	{
		return RueProperty;
	}

	public SimpleStringProperty getVilleProperty()
	{
		return VilleProperty;
	}

	public SimpleStringProperty getCodePostalProperty()
	{
		return CodePostalProperty;
	}

	public SimpleIntegerProperty getPizzaGratuiteProperty()
	{
		return PizzaGratuiteProperty;
	}

	public SimpleFloatProperty getSoldeProperty()
	{
		return SoldeProperty;
	}
}
