package com.RaPizz.controleur;

import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;


import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.LoginModele;
import com.RaPizz.modele.gui.MenuModele;
import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Personne;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LoginControleur extends AbstractControleur{
	
	@FXML
	private ImageView logo_ImageView;
	@FXML
	private TextField Username_TextField;
	@FXML
	private TextField Password_TextField;
	@FXML
	private Button Connect_Button;
	@FXML
	private Button Signup_Button;
	@FXML
	private AnchorPane login_AnchorPane;
	
	private LoginModele modele;
	
	public LoginControleur() {
		super(Contr.LOGIN);		
	}

	@FXML
	private void initialize() {
		modele = (LoginModele) this.getModele(Contr.LOGIN);		
		
	    logo_ImageView.setImage(new Image("com/RaPizz/images/logo.png"));	
	    login_AnchorPane.getStyleClass().add("LoginBG");
	    Username_TextField.textProperty().bindBidirectional(modele.getUserNameProperty());
	    Password_TextField.textProperty().bindBidirectional(modele.getPasswordProperty());
	    login_AnchorPane.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            	connect();
	            }
	        }
	    });
		Signup_Button.setOnAction(x-> showSignUp());
		Signup_Button.getStyleClass().add("UpdateButton");
		Connect_Button.setOnAction(x-> connect());
		Connect_Button.getStyleClass().add("ValidButton");
		update();
    }
	
	private void connect()
	{
		Personne pers = this.getService().GetLoginFor(modele.getUserNameProperty().getValue(), modele.getPasswordProperty().getValue());
		if(pers==null)
		{
			modele.getUserNameProperty().setValue("");
	 		modele.getPasswordProperty().setValue("");
		}else
		{
			MenuModele menu = (MenuModele) this.getModele(Contr.MENU);
			menu.getFirstNameProperty().setValue(pers.getPrenom());
			menu.getLastNameProperty().setValue(pers.getNom());
			menu.getProfilImageProperty().setValue(pers.getPhoto());
			
			Object result = this.getService().GetRole(pers);
			Client c = null;
			
			if(result.getClass().equals(Client.class))
			{
				c = (Client)result;
			}
			
			menu.getClientProperty().setValue(c);
			
			if(c!=null)
				this.showMenu();
			
			this.showHeader();
			this.showHome();
		}
	}
	
	@Override
	public void update() {

	}

	@Override
	public Pane getPane() {
		return login_AnchorPane;
	}

	
}
