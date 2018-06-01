package com.RaPizz.controleur;

import com.RaPizz.controleur.Mediateur.Contr;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class HeaderControleur extends AbstractControleur {
	@FXML
	private AnchorPane Header_AnchorPane;
	@FXML
	private Button Ingredients_button;
	@FXML
	private Button Pizza_button;
	@FXML
	private Button Client_button;
	@FXML
	private Button Livreur_button;
	@FXML
	private Button Liraison_button;
	@FXML
	private Button Vehicule_button;
	@FXML
	private ImageView logo_ImageView;
	@FXML
	private HBox header_HBox;
	
	public HeaderControleur() {
		super(Contr.HEADER);		
	}

	@FXML
	private void initialize() {
		Pizza_button.setOnAction(x->this.showManagePizza());
		logo_ImageView.setImage(new Image("com/RaPizz/images/logo.png"));	
		
		header_HBox.getStyleClass().add("MenuBG");
		Ingredients_button.getStyleClass().add("MenuButton");
		Pizza_button.getStyleClass().add("MenuButton");
		Client_button.getStyleClass().add("MenuButton");
		Livreur_button.getStyleClass().add("MenuButton");
		Liraison_button.getStyleClass().add("MenuButton");
		Vehicule_button.getStyleClass().add("MenuButton");
		update();
    }
	
	@Override
	public void update() {

	}

	@Override
	public Pane getPane() {
		return Header_AnchorPane;
	}

}
