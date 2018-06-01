package com.RaPizz.controleur;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.MenuModele;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
	private ListView Order_Listview;
	@FXML
	private HBox home_HBox;
	@FXML
	private HBox pizza_HBox;
	@FXML
	private HBox basket_HBox;
	@FXML
	private ImageView profil_ImageView;
	@FXML
	private ImageView home_ImageView;
	@FXML
	private ImageView pizza_ImageView;
	@FXML
	private ImageView basket_ImageView;
	
	private MenuModele modele;
	
	public MenuControleur() {
		super(Contr.MENU);		
	}

	@FXML
	private void initialize() {
		modele = (MenuModele)this.getModele(Contr.MENU);
		
		profil_ImageView.imageProperty().bind(modele.getProfilImageProperty());
		
		home_HBox.getStyleClass().add("MenuButton");
		home_HBox.setOnMouseClicked(x -> this.showHome());
		home_ImageView.setImage(new Image("com/RaPizz/images/home-icon.png"));	

		pizza_HBox.getStyleClass().add("MenuButton");
		pizza_HBox.setOnMouseClicked(x -> this.showOrderPizza());
		pizza_ImageView.setImage(new Image("com/RaPizz/images/pizza-icon.png"));	

		basket_HBox.getStyleClass().add("MenuButton");
		basket_ImageView.setImage(new Image("com/RaPizz/images/basket-icon.png"));	
	    
		
		menu_AnchorPane.getStyleClass().add("MenuBG");
		
		Firstname_Label.textProperty().bind(modele.getFirstNameProperty());
		Lastname_Label.textProperty().bind(modele.getLastNameProperty());
		
		update();
    }
	
	
	
	@Override
	public void update() {

	}

	@Override
	public Pane getPane() {
		return menu_AnchorPane;
	}
}
