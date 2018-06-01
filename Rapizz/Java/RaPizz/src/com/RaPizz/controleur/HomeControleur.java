package com.RaPizz.controleur;

import javafx.fxml.FXML;


import com.RaPizz.controleur.Mediateur.Contr;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class HomeControleur extends AbstractControleur{	
	
	@FXML
	private GridPane Home_GridPane;	
	
	/*@FXML
	private AnchorPane Home_AnchorPane;
	*/
	public HomeControleur() {
		super(Contr.HOME);		
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
		return Home_GridPane;
	}

	
}
