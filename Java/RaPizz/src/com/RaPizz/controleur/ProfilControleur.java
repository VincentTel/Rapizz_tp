package com.RaPizz.controleur;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.MenuModele;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class ProfilControleur extends AbstractControleur{

	@FXML
	private AnchorPane Profil_AnchorPane;
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
	private TextField Solde_TextField;
	@FXML
	private TextField PizzaGratuite_TextField;
	@FXML
	private Button Register_Button;
	private MenuModele modele;
	
	public ProfilControleur() {
		super(Contr.PROFIL);		
	}
	

	@FXML
	private void initialize() {
		modele = (MenuModele)this.getModele(Contr.MENU);
		Profil_AnchorPane.getStyleClass().add("RedBG");
		update();
    }
	

	@Override
	public void update() {
		if(modele.getClientProperty().getValue() != null)
		{
		Profil_ImageView.setImage(modele.getClientProperty().getValue().getPhoto());
		Username_TextField.setText(modele.getClientProperty().getValue().getUserName());
		Password_TextField.setText(modele.getClientProperty().getValue().getPassword());
		LastName_TextField.setText(modele.getClientProperty().getValue().getNom());
		FirstName_TextField.setText(modele.getClientProperty().getValue().getPrenom());
		Email_TextField.setText(modele.getClientProperty().getValue().getEmail());
		Number_TextField.setText(modele.getClientProperty().getValue().getAdr().getNumRue());
		Street_TextField.setText(modele.getClientProperty().getValue().getAdr().getRue());
		City_TextField.setText(modele.getClientProperty().getValue().getAdr().getVille());
		Zip_TextField.setText(modele.getClientProperty().getValue().getAdr().getCp());
		Solde_TextField.setText(String.format("%f", modele.getClientProperty().getValue().getSolde().getValue()));
		PizzaGratuite_TextField.setText(String.format("Pizza commandé: %d",modele.getClientProperty().getValue().getPizzaGratuite().getValue()));
		}
		
		Register_Button.setOnAction(x->save());
		Register_Button.getStyleClass().add("ValidButton");
	}
	private boolean isNumeric(String str) {
	    if (str == null) {
	        return false;
	    }
	    int sz = str.length();
	    for (int i = 0; i < sz; i++) {
	        if (Character.isDigit(str.charAt(i)) == false) {
	            return false;
	        }
	    }
	    return true;
	}
	private void save()
	{
		if(Profil_ImageView.getImage() != null &&
				!Username_TextField.getText().equals("")&&
				!Password_TextField.getText().equals("")&&
				!LastName_TextField.getText().equals("")&&
				!FirstName_TextField.getText().equals("")&&
				!Email_TextField.getText().equals("")&&
				!Email_TextField.getText().equals("")&&
				!Number_TextField.getText().equals("")&&
				!Street_TextField.getText().equals("")&&
				!City_TextField.getText().equals("")&&
				!Zip_TextField.getText().equals("")&&
				!Solde_TextField.getText().equals("")&&
				!PizzaGratuite_TextField.getText().equals("")&&				
				isNumeric(Solde_TextField.getText()))
		{
			modele.getProfilImageProperty().setValue(Profil_ImageView.getImage());
			modele.getClientProperty().getValue().setPhoto(Profil_ImageView.getImage());
			modele.getClientProperty().getValue().setUserName(Username_TextField.getText());
			modele.getClientProperty().getValue().setPassword(Password_TextField.getText());
			modele.getLastNameProperty().setValue(LastName_TextField.getText());
			modele.getClientProperty().getValue().setNom(LastName_TextField.getText());
			modele.getLastNameProperty().setValue(FirstName_TextField.getText());
			modele.getClientProperty().getValue().setPrenom(FirstName_TextField.getText());
			modele.getClientProperty().getValue().setEmail(Email_TextField.getText());
			modele.getClientProperty().getValue().getAdr().setNumRue(Number_TextField.getText());
			modele.getClientProperty().getValue().getAdr().setRue(Street_TextField.getText());
			modele.getClientProperty().getValue().getAdr().setVille(City_TextField.getText());
			modele.getClientProperty().getValue().getAdr().setVille(Zip_TextField.getText());
			modele.getClientProperty().getValue().setSolde(Float.parseFloat(Solde_TextField.getText()));
			this.getService().UpdateClient(modele.getClientProperty().getValue());
		}
		
		
	}

	@Override
	public Pane getPane() {
		return Profil_AnchorPane;
	}

}
