package com.RaPizz.controleur;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.MenuModele;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;

public class MenuControleur extends AbstractControleur {
	@FXML
	private AnchorPane menu_AnchorPane;
	@FXML
	private Label Firstname_Label;
	@FXML
	private Label Lastname_Label;
	@FXML
	private Button Home_Button;
	@FXML
	private Button Order_Button;
	@FXML
	private Button Basket_Button;
	@FXML
	private HBox solde_HBox;
	@FXML
	private HBox home_HBox;
	@FXML
	private HBox pizza_HBox;
	@FXML
	private HBox basket_HBox;
	@FXML
	private ImageView profil_ImageView;
	@FXML
	private Label soldeAmount_Label;
	@FXML
	private Label home_Label;
	@FXML
	private Label pizza_Label;
	@FXML
	private Label basket_Label;
	@FXML
	private ImageView logo_ImageView;
	
	private MenuModele modele;
	
	public MenuControleur() {
		super(Contr.MENU);		
	}

	@FXML
	private void initialize() {
		modele = (MenuModele)this.getModele(Contr.MENU);

		logo_ImageView.setImage(new Image("com/RaPizz/images/logo.png"));
		
		profil_ImageView.imageProperty().bind(modele.getProfilImageProperty());
		profil_ImageView.setOnMouseClicked(x->this.showProfil());
		
		home_HBox.getStyleClass().add("MenuButton");
		home_HBox.setOnMouseClicked(x -> this.showHome());
		home_Label.getStyleClass().add("IconsControl");
		
		pizza_HBox.getStyleClass().add("MenuButton");
		pizza_HBox.setOnMouseClicked(x -> this.showOrderPizza());
		pizza_Label.getStyleClass().add("Icons");
	
		basket_HBox.getStyleClass().add("MenuButton");
		basket_HBox.setOnMouseClicked(x-> disconnect());
		basket_Label.getStyleClass().add("Icons");
		
		menu_AnchorPane.getStyleClass().add("MenuBG");
		
		Firstname_Label.textProperty().bind(modele.getFirstNameProperty());
		Lastname_Label.textProperty().bind(modele.getLastNameProperty());
		
		
		update();
    }
	
	private void disconnect()
	{
		showLogin();
	}
	
	@Override
	public void update() {
		modele = (MenuModele)this.getModele(Contr.MENU);

		if(modele.getClientProperty().getValue() != null)
			soldeAmount_Label.textProperty().bindBidirectional(modele.getClientProperty().getValue().getSolde(), new NumberStringConverter());

	}

	@Override
	public Pane getPane() {
		return menu_AnchorPane;
	}
}
