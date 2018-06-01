package com.RaPizz.controleur;

import com.RaPizz.controleur.Mediateur.Contr;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainControleur extends AbstractControleur {
		
		@FXML private BorderPane main_BorderPane;

		public MainControleur(){
			super(Contr.MAIN);
		}
		
		@FXML private void initialize() {
	    }

		public BorderPane getMainBorderPane() {
			return main_BorderPane;
		}

		public void setMainBorderPane(BorderPane mainBorderPane) {
			this.main_BorderPane = mainBorderPane;
		}
		
		@Override
		public Pane getPane() {
			return main_BorderPane;
		}

		@Override
		public void update() {
			
		}

		
		
}
