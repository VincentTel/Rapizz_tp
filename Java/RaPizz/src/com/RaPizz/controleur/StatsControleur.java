package com.RaPizz.controleur;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.StatsModele;
import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Vehicule;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class StatsControleur extends AbstractControleur {

	@FXML
	private Label CA_Label;
	@FXML
	private Label BestClient_Label;
	@FXML
	private Label IngredientFav_Label;
	@FXML
	private Label PizzaPlusDMD_Label;
	@FXML
	private Label PizzaMoinsDMD_Label;
	@FXML
	private Label PireLivreur_Label;
	@FXML
	private Label CommandeMoyenne_Label;
	@FXML
	private ListView<Client> Client_listview;
	@FXML
	private ListView<Vehicule> Vehicule_listview;
	@FXML
	private AnchorPane Stats_AnchorPane;
	@FXML
	private ImageView Line_ImageView;	
	
	private StatsModele modele;
	
	public StatsControleur() {
		super(Contr.STATS);		
	}
	@FXML
	private void initialize() {

		modele = (StatsModele)this.getModele(Contr.STATS);

		Line_ImageView.setImage(new Image("com/RaPizz/images/Line.png"));
		Stats_AnchorPane.getStyleClass().add("MenuBG");
		
		CA_Label.textProperty().bindBidirectional(modele.getCaProperty());

		BestClient_Label.textProperty().bindBidirectional(modele.getBestClientProperty());

		IngredientFav_Label.textProperty().bindBidirectional(modele.getIngredientFavProperty());

		PizzaPlusDMD_Label.textProperty().bindBidirectional(modele.getPizzaPlusDMDProperty());

		PizzaMoinsDMD_Label.textProperty().bindBidirectional(modele.getPizzaMoinsDMDProperty());

		PireLivreur_Label.textProperty().bindBidirectional(modele.getPireLivreurProperty());
		
		CommandeMoyenne_Label.textProperty().bindBidirectional(modele.getCommandeMoyenneProperty());
		
		
		
		Vehicule_listview.setCellFactory(param -> new ListCell<Vehicule>() {
		    @Override
		    protected void updateItem(Vehicule item, boolean empty) {
		        super.updateItem(item, empty);		        
		        if ( !(empty || item == null))  {
		            setText(item.toString() + " Utilisation: " + item.getNbUtilisation());
		        }
		    }
		}); 
		
		Client_listview.setCellFactory(param -> new ListCell<Client>() {
		    @Override
		    protected void updateItem(Client item, boolean empty) {
		        super.updateItem(item, empty);		        
		        if ( !(empty || item == null))  {
		            setText(item.getNom() +" " +item.getPrenom());
		        }
		    }
		}); 
    }


	@Override
	public void update() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		
		try {

			modele.getCaProperty().setValue(this.getService().getCaPeriode( new Timestamp(dateFormat.parse(LocalDate.now().getYear()+"-01-01 00:00:00.000").getTime()),  new Timestamp(dateFormat.parse(LocalDate.now().getYear() + 1+"-01-01 00:00:00.000").getTime())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		modele.getBestClientProperty().setValue(this.getService().getBestClient());
		modele.getIngredientFavProperty().setValue(this.getService().getIngredientFav());
		modele.getPizzaPlusDMDProperty().setValue(this.getService().getPizzaPlusDMD());
		modele.getPizzaMoinsDMDProperty().setValue(this.getService().getPizzaMoinsDMD());
		modele.getPireLivreurProperty().setValue(this.getService().getPireLivreur());
		modele.getCommandeMoyenneProperty().setValue(this.getService().getCommandeMoyenne());
		
		Vehicule_listview.getItems().setAll(FXCollections.observableArrayList(this.getService().getVehiculeUtilise()));
		Vehicule_listview.getStyleClass().add("lv");
		Client_listview.getItems().setAll(FXCollections.observableArrayList(this.getService().getClientAboveAverage()));	
		Client_listview.getStyleClass().add("lv");
		
	}


	@Override
	public Pane getPane() {
		return Stats_AnchorPane;
	}	
	
	
}
