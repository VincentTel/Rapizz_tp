package com.RaPizz.controleur;

import java.io.File;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.SignupModele;
import com.RaPizz.modele.metier.Adresse;
import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Personne;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SignupControleur extends AbstractControleur {
	@FXML
	private AnchorPane Signup_AnchorPane;
	@FXML
	private ImageView Profil_ImageView;
	@FXML
	private TextField Username_TextField;
	@FXML
	private TextField Password_TextField;
	@FXML
	private TextField LastName_TextField;
	@FXML
	private TextField FirstName_TextField;
	@FXML
	private TextField Email_TextField;
	@FXML
	private TextField Number_TextField;
	@FXML
	private TextField Street_TextField;
	@FXML
	private TextField City_TextField;
	@FXML
	private TextField Zip_TextField;
	@FXML
	private Button Register_Button;
	@FXML
	private Button Cancel_Button;
	private SignupModele modele;
	
	public SignupControleur() {
		super(Contr.SIGNUP);
	}

	@FXML
	private void initialize() {
		
		modele = (SignupModele)this.getModele(Contr.SIGNUP);
		Profil_ImageView.setOnMouseClicked(x -> {
	          FileChooser fileChooser = new FileChooser();	 
			  File file = fileChooser.showOpenDialog(this.getPrimaryScene());
            if (file != null) {
          	  Image i = new Image(file.toURI().toString());            	  
          	  modele.getClientImageProperty().setValue(i);
            }
		});
		Register_Button.setOnAction(evt -> add());
		Signup_AnchorPane.getStyleClass().add("LoginBG");
		Register_Button.getStyleClass().add("ValidButton");
		Cancel_Button.getStyleClass().add("CancelButton");
		Cancel_Button.setOnAction(x->this.showLogin());
		update();
    }
	
	private void add() {
		
		Adresse adr = new Adresse(Number_TextField.getText(),Street_TextField.getText(),City_TextField.getText(),Zip_TextField.getText());
		Client client = new Client(new Personne(0L,FirstName_TextField.getText(),LastName_TextField.getText(),Username_TextField.getText(),Password_TextField.getText(),Email_TextField.getText(),Profil_ImageView.getImage()),adr,0,1);
		this.getService().AddClient(client);
		this.showLogin();		
	}
	
	@Override
	public void update() {
		if(modele.getClientImageProperty()!=null)
			Profil_ImageView.imageProperty().bindBidirectional(modele.getClientImageProperty());
	}

	@Override
	public Pane getPane() {
		return Signup_AnchorPane;
	}

}
