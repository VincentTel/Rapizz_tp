package com.RaPizz.modele.gui;

import java.time.LocalDate;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class ManageLivreur
{
	private SimpleLongProperty IdLivreurProperty;
	private SimpleObjectProperty<Image> LivreurImage;
	private SimpleStringProperty PrenomProperty;
	private SimpleStringProperty NomProperty;
	private SimpleStringProperty UsernameProperty;
	private SimpleStringProperty PasswordProperty;
	private SimpleStringProperty EmailProperty;
	private SimpleObjectProperty<LocalDate> DateEmbaucheProperty;
	private SimpleFloatProperty SalaireProperty;
	private SimpleIntegerProperty retardProperty;
	
	public ManageLivreur()
	{
		LivreurImage = new SimpleObjectProperty<Image>(new Image(
				"com/RaPizz/images/click-icon.png"));
		PrenomProperty = new SimpleStringProperty("");
		NomProperty = new SimpleStringProperty("");
		UsernameProperty = new SimpleStringProperty("");
		PasswordProperty = new SimpleStringProperty("");
		EmailProperty = new SimpleStringProperty("");
		DateEmbaucheProperty = new SimpleObjectProperty<LocalDate>();
		SalaireProperty = new SimpleFloatProperty();
		IdLivreurProperty = new SimpleLongProperty();
		retardProperty= new SimpleIntegerProperty();
	}

	public void modeleInit()
	{
		LivreurImage.setValue(new Image("com/RaPizz/images/click-icon.png"));
		PrenomProperty.setValue("");
		NomProperty.setValue("");
		UsernameProperty.setValue("");
		PasswordProperty.setValue("");
		EmailProperty.setValue("");
		DateEmbaucheProperty.set(null);
		SalaireProperty.setValue(0);
		IdLivreurProperty.setValue(null);
		retardProperty.setValue(0);
	}

	public SimpleIntegerProperty getRetardProperty() {
		return retardProperty;
	}

	public SimpleLongProperty getIdLivreurProperty()
	{
		return IdLivreurProperty;
	}

	public SimpleObjectProperty<Image> getLivreurImage()
	{
		return LivreurImage;
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

	public SimpleObjectProperty<LocalDate> getDateEmbaucheProperty()
	{
		return DateEmbaucheProperty;
	}

	public SimpleFloatProperty getSalaireProperty()
	{
		return SalaireProperty;
	}
}
