package com.RaPizz.controleur;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;

import java.util.List;
import java.util.stream.Collectors;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.HomeModele;
import com.RaPizz.modele.gui.MenuModele;
import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Pizza;
import com.RaPizz.modele.metier.Taille;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class HomeControleur extends AbstractControleur{	
	
	@FXML
	private AnchorPane Home_Client_AnchorPane;
	@FXML
	private HBox Client_popularPizza_HBox;
	@FXML
	private Label Client_qualite_Label;
	@FXML
	private Label Client_rapidite_Label;
	@FXML
	private Label Client_cuisiniers_Label;
	@FXML
	private Label Client_gout_Label;
	@FXML
	private AnchorPane Home_Livreur_AnchorPane;
	@FXML
	private AnchorPane Home_AnchorPane;
	@FXML
	private TableView Dashbord_TableView;
	@FXML
	private TableColumn ID_TableColumn;
	@FXML
	private TableColumn Date_TableColumn;
	@FXML
	private TableColumn Prix_TableColumn;
	@FXML
	private TableColumn Nom_TableColumn;
	@FXML
	private TableColumn Prenom_TableColumn;
	@FXML
	private TableColumn NumRue_TableColumn;
	@FXML
	private TableColumn Rue_TableColumn;
	@FXML
	private TableColumn Ville_TableColumn;
	@FXML
	private TableColumn CodePostal_TableColumn;
	@FXML
	private TableColumn Livreur_TableColumn;
	@FXML
	private TableColumn Vehicule_TableColumn;
	@FXML
	private TableColumn Valider_TableColumn;
	@FXML
	private ImageView Line_ImageView;	
	
	private HomeModele modele;
	
	public HomeControleur() {
		super(Contr.HOME);		
		
	}

	@FXML
	private void initialize() {
		//modele = (HomeModele) this.getModele(Contr.HOME);	
		
		Home_Client_AnchorPane.getStyleClass().add("RedBG");
		Line_ImageView.setImage(new Image("com/RaPizz/images/Line.png"));
		Client_qualite_Label.getStyleClass().add("Icons");
		Client_qualite_Label.getStyleClass().add("Icons");
		Client_rapidite_Label.getStyleClass().add("Icons");
		Client_cuisiniers_Label.getStyleClass().add("Icons");
		Client_gout_Label.getStyleClass().add("Icons");
		
		update();
    }
	
	@Override
	public void update() {
		List<Pizza> listPizz = this.getService().getTopPizza();
		
		Client_popularPizza_HBox.getChildren().clear();
			
		if(listPizz.size()>0) 
		{
			for(Pizza p : listPizz)
			{
				VBox hb = new VBox();
				hb.setPrefWidth(150);
				hb.setAlignment(Pos.CENTER);
				hb.getStyleClass().add("PizzaBox");
				hb.setSpacing(5);
				ImageView img = new ImageView(p.getPhoto());
				Label titre = new Label(p.getDesignation());
				titre.setTextFill(Color.WHITE);
				Label composition = new Label(p.getComposition()
				                   .stream()
				                   .map( x -> x.getDesignation())
				                   .collect(Collectors.joining(", ")));
				composition.setTextFill(Color.WHITE);
				composition.setWrapText(true);
				composition.setMaxWidth(Double.MAX_VALUE);
				composition.setAlignment(Pos.CENTER);
				composition.setTextAlignment(TextAlignment.CENTER);
				
				ChoiceBox<Taille>  ch = new ChoiceBox<Taille> ();
				ObservableList<Taille> ol_listTaille = FXCollections.observableArrayList(this.getService().getAllTaille());
				ch.setItems(ol_listTaille);
				ch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Taille>() {
				      @Override
				      public void changed(ObservableValue<? extends Taille> observableValue, Taille oldTaille, Taille newTaille) {
				    	  p.setSize(newTaille);

				      }
				    });

				ch.getSelectionModel().select(0);

				Button add = new Button();
				add.textProperty().bind(Bindings.createStringBinding(() -> {
					final String value = "Add " + p.getPrix().getValue() +"€";
				    return value ;
				}, p.getPrix()));
				
				add.setOnAction(x-> getOderPizzaControleur().AddPanier(new Pizza(p)));
				
				hb.getChildren().add(titre);
				hb.getChildren().add(img);	
				hb.getChildren().add(composition);
				hb.getChildren().add(ch);
				hb.getChildren().add(add);
				Client_popularPizza_HBox.getChildren().add(hb);					
			}	
			
		}
		
		
		
	}

	@Override
	public Pane getPane() {
		Client client = ( (MenuModele) this.getModele(Contr.MENU) ).getClientProperty().getValue();
		if(client!=null)
		{

			Home_Client_AnchorPane.setVisible(true);
			Home_Livreur_AnchorPane.setVisible(false);
		}
		else
		{

			Home_Livreur_AnchorPane.setVisible(true);
			Home_Client_AnchorPane.setVisible(false);
		}
			
		return Home_AnchorPane;
	}

	
}
