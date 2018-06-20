package com.RaPizz.controleur;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.MenuModele;
import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Commande;
import com.RaPizz.modele.metier.Livreur;
import com.RaPizz.modele.metier.Pizza;
import com.RaPizz.modele.metier.Taille;
import com.RaPizz.modele.metier.Vehicule;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

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
	private TableView<Commande> Dashbord_TableView;
	@FXML
	private TableColumn<Commande, Long> ID_TableColumn;
	@FXML
	private TableColumn<Commande, Timestamp> Date_TableColumn;
	@FXML
	private TableColumn<Commande, Float> Prix_TableColumn;
	@FXML
	private TableColumn<Commande, String> Nom_TableColumn;
	@FXML
	private TableColumn<Commande, String> Prenom_TableColumn;
	@FXML
	private TableColumn<Commande, String>  NumRue_TableColumn;
	@FXML
	private TableColumn<Commande, String>  Rue_TableColumn;
	@FXML
	private TableColumn<Commande, String>  Ville_TableColumn;
	@FXML
	private TableColumn<Commande, String>  CodePostal_TableColumn;
	@FXML
	private TableColumn<Commande, ChoiceBox<Livreur>>  Livreur_TableColumn;
	@FXML
	private TableColumn<Commande, ChoiceBox<Vehicule>> Vehicule_TableColumn;
	@FXML
	private TableColumn<Commande, HBox> Valider_TableColumn;
	@FXML
	private ImageView Line_ImageView;	
	
	private Client client ;
	public HomeControleur() {
		super(Contr.HOME);		
		
	}

	@FXML
	private void initialize() {
		//modele = (HomeModele) this.getModele(Contr.HOME);	
		Home_Livreur_AnchorPane.getStyleClass().add("MenuBG");
		Home_Client_AnchorPane.getStyleClass().add("RedBG");
		Line_ImageView.setImage(new Image("com/RaPizz/images/Line.png"));
		Client_qualite_Label.getStyleClass().add("Icons");
		Client_qualite_Label.getStyleClass().add("Icons");
		Client_rapidite_Label.getStyleClass().add("Icons");
		Client_cuisiniers_Label.getStyleClass().add("Icons");
		Client_gout_Label.getStyleClass().add("Icons");
		
		
		/*Grid part*/
		ID_TableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Long>(cellData.getValue().getID_Commande()) );
		Date_TableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Timestamp>(cellData.getValue().getDateCommande()) );
		Prix_TableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Float>(cellData.getValue().getPrixTotal()) );
		Nom_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getNom()));
		Prenom_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getPrenom()));
		NumRue_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getAdr().getNumRue()));
		Rue_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getAdr().getRue()));
		Ville_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getAdr().getVille()));
		CodePostal_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClient().getAdr().getVille()));
		
	
		update();
    }
	
	@Override
	public void update() {
		client = ( (MenuModele) this.getModele(Contr.MENU) ).getClientProperty().getValue();
		if(client != null)
		{
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
					add.getStyleClass().add("ValidButton");
					add.setOnAction(x-> getOderPizzaControleur().AddPanier(new Pizza(p)));
					
					hb.getChildren().add(titre);
					hb.getChildren().add(img);	
					hb.getChildren().add(composition);
					hb.getChildren().add(ch);
					hb.getChildren().add(add);

					Client_popularPizza_HBox.getChildren().add(hb);					
				}	
				
			}
		
		}else
		{
			ObservableList<Commande> ol_Commande = FXCollections.observableArrayList(this.getService().getAllOrderDelivery());
			Dashbord_TableView.getItems().setAll(ol_Commande);
			
			List<Livreur> listLivreur = this.getService().getAllLivreurManage();
			List<Vehicule> listVehicule = this.getService().getAllVehiculeManage();
			
			/*Livreu**/
			Callback<TableColumn<Commande,  ChoiceBox<Livreur>>,TableCell<Commande,  ChoiceBox<Livreur>>> actionLivreur_TableColumn = new Callback<TableColumn<Commande,  ChoiceBox<Livreur>>,TableCell<Commande,  ChoiceBox<Livreur>>>()
			{
				public TableCell<Commande,  ChoiceBox<Livreur>> call(TableColumn<Commande,  ChoiceBox<Livreur>> c) {
					ObservableList<Livreur> ol_listLivreur = FXCollections.observableArrayList(listLivreur);
					ChoiceBox<Livreur> newCb = new ChoiceBox<Livreur>();
					newCb.setItems(ol_listLivreur);
					
					TableCell<Commande, ChoiceBox<Livreur>> cell = new TableCell<Commande, ChoiceBox<Livreur>>() {
							                				@Override
							                				public void updateItem(ChoiceBox<Livreur> cb, boolean empty) {
									                            super.updateItem(cb, empty);
									                            if(this.getTableRow().getItem() != null)
									                            	setGraphic(newCb);
							                				}
					};			
					
					newCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Livreur>() {
					      @Override
					      public void changed(ObservableValue<? extends Livreur> observableValue, Livreur oldLivreur, Livreur newLivreur) {
					    	  if(cell.getTableRow() != null)
					    	  ((Commande)cell.getTableRow().getItem()).setLivreur(newLivreur);
					      }
					    });
					
					
					return cell ;							                
				}
			};
		

			Livreur_TableColumn.setCellFactory(actionLivreur_TableColumn);
			
			/*Vehicule*/
			Callback<TableColumn<Commande,  ChoiceBox<Vehicule>>,TableCell<Commande,  ChoiceBox<Vehicule>>> actionVehicule_TableColumn = new Callback<TableColumn<Commande,  ChoiceBox<Vehicule>>,TableCell<Commande,  ChoiceBox<Vehicule>>>()
			{
				public TableCell<Commande,  ChoiceBox<Vehicule>> call(TableColumn<Commande,  ChoiceBox<Vehicule>> c) {
					
					ObservableList<Vehicule> ol_lisrVehicule = FXCollections.observableArrayList(listVehicule);
					ChoiceBox<Vehicule> newCb = new ChoiceBox<Vehicule>();
					newCb.setItems(ol_lisrVehicule);
					
					TableCell<Commande, ChoiceBox<Vehicule>> cell = new TableCell<Commande, ChoiceBox<Vehicule>>() {
							                				@Override
							                				public void updateItem(ChoiceBox<Vehicule> cb, boolean empty) {
									                            super.updateItem(cb, empty);
									                            if(this.getTableRow().getItem() != null)
									                            	setGraphic(newCb);
							                				}
					};	
					
					newCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Vehicule>() {
					      @Override
					      public void changed(ObservableValue<? extends Vehicule> observableValue, Vehicule oldVehicule, Vehicule newVehicule) {
					    	  if(cell.getTableRow() != null)
					    	  ((Commande)cell.getTableRow().getItem()).setVehicule(newVehicule);
					      }
					    });
				
					
					return cell ;							                
				}
			};
			Vehicule_TableColumn.setCellFactory(actionVehicule_TableColumn);

			/*Valide*/
			Callback<TableColumn<Commande, HBox>,TableCell<Commande, HBox>> actionValideCellFactory = new Callback<TableColumn<Commande, HBox>,TableCell<Commande, HBox>>()
			{
				public TableCell<Commande, HBox> call(TableColumn<Commande, HBox> p) {
					Label upImg = new Label("i");
					upImg.setTextFill(Color.GREEN);
					upImg.getStyleClass().add("Icons");	
					TableCell<Commande, HBox> cell = new TableCell<Commande, HBox>() {
							                				@Override
							                				public void updateItem(HBox hb, boolean empty) {
									                            super.updateItem(hb, empty);	
									                            if(this.getTableRow().getItem() != null)
									                            	setGraphic(upImg);
							                				}
					};	
					cell.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	                    @Override
	                    public void handle(MouseEvent event) {
	                    	if(event.getClickCount()>1)
	                    		ValideCommande(Dashbord_TableView.getSelectionModel().getSelectedItem());
	                    }
					});
					return cell ;
							                
				}
			};
			Valider_TableColumn.setCellFactory(actionValideCellFactory);
			
		}
		
	}
	
	private void ValideCommande(Commande c)
	{
		if(Dashbord_TableView.getSelectionModel().getSelectedItem().getLivreur() != null&&Dashbord_TableView.getSelectionModel().getSelectedItem().getVehicule() !=null)
		{
			Dashbord_TableView.getSelectionModel().getSelectedItem().setDateLivraison(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))));
			this.getService().UpdateCommande(Dashbord_TableView.getSelectionModel().getSelectedItem());
			update();
		}
	}

	@Override
	public Pane getPane() {
		
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
