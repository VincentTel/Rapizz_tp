package com.RaPizz.controleur;

import com.RaPizz.Main;
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
	private HBox header_HBox;
	@FXML
	private HBox menu_HBox;
	@FXML
	private Button Home_button;
	@FXML
	private Button Disconnect_button;
	@FXML
	private ImageView banner_ImageView;
	@FXML
	private Label tel_Label;
	@FXML
	private ImageView facebook_ImageView;
	@FXML
	private ImageView twitter_ImageView;
	@FXML
	private ImageView snap_ImageView;
	@FXML
	private HBox info_HBox;
	
	public HeaderControleur() {
		super(Contr.HEADER);		
	}

	@FXML
	private void initialize() {
		
		Pizza_button.setOnAction(x->this.showManagePizza());
		Home_button.setOnAction(x -> this.showHome());
		Disconnect_button.setOnAction(x->showLogin());
		Client_button.setOnAction(x->showManageClient());
		Livreur_button.setOnAction(x->showManageLivreur());
		Liraison_button.setOnAction(x->showManageCommande());
		Vehicule_button.setOnAction(x->showManageVehicule());
		Ingredients_button.setOnAction(x->showManageIngredient());
		
		facebook_ImageView.setImage(new Image("com/RaPizz/images/fb.png"));
		facebook_ImageView.setOnMouseClicked(x->goToUrl("https://www.facebook.com/rapizz.fr"));
		twitter_ImageView.setImage(new Image("com/RaPizz/images/tw.png"));
		twitter_ImageView.setOnMouseClicked(x->goToUrl("https://twitter.com/Rapizzfr"));
		snap_ImageView.setImage(new Image("com/RaPizz/images/snap.png"));
		snap_ImageView.setOnMouseClicked(x->goToUrl("http://www.rapizz.fr/"));
		Header_AnchorPane.getStyleClass().add("HeaderBG");
		Home_button.getStyleClass().add("MenuButton");
		Ingredients_button.getStyleClass().add("MenuButton");
		Pizza_button.getStyleClass().add("MenuButton");
		Client_button.getStyleClass().add("MenuButton");
		Livreur_button.getStyleClass().add("MenuButton");
		Liraison_button.getStyleClass().add("MenuButton");
		Vehicule_button.getStyleClass().add("MenuButton");
		Disconnect_button.getStyleClass().add("MenuButton");
		update();
    }
	
	public void goToUrl(String url)
	{
		Main.GetHostServices().showDocument(url);
	}
	
	@Override
	public void update() {
		MenuModele menu = (MenuModele) this.getModele(Contr.MENU);
		if(menu.getClientProperty().getValue() != null && header_HBox.getChildren().contains(menu_HBox)  )
		{
			header_HBox.getChildren().remove(menu_HBox);
			if(!header_HBox.getChildren().contains(info_HBox))
				header_HBox.getChildren().add(info_HBox);
		}
		else if (!header_HBox.getChildren().contains(menu_HBox)  )
		{
			header_HBox.getChildren().add(menu_HBox);
			if(header_HBox.getChildren().contains(info_HBox))
				header_HBox.getChildren().remove(info_HBox);

		}
	}

	@Override
	public Pane getPane() {
		return Header_AnchorPane;
	}

}
