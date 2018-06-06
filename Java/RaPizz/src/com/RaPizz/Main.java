package com.RaPizz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.RaPizz.Main;
import com.RaPizz.controleur.AbstractControleur;
import com.RaPizz.controleur.Mediateur;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	 private static HostServices hostServices ;
	 public static HostServices GetHostServices() {
	        return hostServices ;
	    }
	   
	private Pane mainParent;
	//private Pane agendaParent;*
	private AbstractControleur mainControleur;
	@SuppressWarnings("unused")
	private Properties prop;
	private Stage primarystage;
	@Override
	public void start(Stage primaryStage) throws IOException {

        hostServices = getHostServices();

        
		Properties prop = new Properties();
		InputStream input = null;
		
		Font.loadFont(getClass().getResource("/com/RaPizz/images/pizzfont.TTF").toExternalForm(), 10);
		
		try {
			input = getClass().getResourceAsStream("/com/RaPizz/vue/listVues.properties");
			prop.load(input);	
			for(Mediateur.Contr enumV : Mediateur.Contr.values())	
				Load(enumV, prop.getProperty(enumV.toString()));
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		primarystage = primaryStage;
		primarystage.setTitle("RaPizz");		
		primarystage.setOnCloseRequest(evt -> System.out.println("Stage is closing")); 
		
		initMain(mainParent);		
	}
	
	@Override
	public void stop() {
		//libération des ressources
		mainControleur.close();
	}
	
	public void initMain(Pane pane) {
		Scene scene = new Scene(pane);
		scene.getStylesheets().add(getClass().getResource("RaPizz.css").toExternalForm());
		primarystage.setScene(scene);
		primarystage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void Load(Mediateur.Contr keyProperty,String view)
	{
		try {
				FXMLLoader loader = new FXMLLoader();
				System.out.println(view);
				loader.setLocation(Main.class.getResource(view));	
				
				Pane p = (Pane)loader.load();
				
				if(keyProperty.equals(Mediateur.Contr.MAIN))
				{
					mainParent = p;
					mainControleur = (AbstractControleur) loader.getController();
					mainControleur.setPrimaryScene(primarystage);
				}
				else if(keyProperty.equals(Mediateur.Contr.LOGIN))
				{
					((BorderPane)mainParent).setCenter(p);	
				}	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
