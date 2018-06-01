package com.RaPizz.controleur;

import com.RaPizz.controleur.Mediateur.Contr;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
	
	public SignupControleur() {
		super(Contr.SIGNUP);		
	}

	@FXML
	private void initialize() {
		
		update();
    }
	
	@Override
	public void update() {

	}

	@Override
	public Pane getPane() {
		return Signup_AnchorPane;
	}

}
