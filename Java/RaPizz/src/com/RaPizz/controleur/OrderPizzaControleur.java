package com.RaPizz.controleur;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.OderItemTemplate;
import com.RaPizz.modele.gui.OrderPizzaModele;
import com.RaPizz.modele.metier.Pizza;
import com.RaPizz.modele.metier.Taille;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class OrderPizzaControleur extends AbstractControleur{
	@FXML
	private AnchorPane OrderClient_AnchorPane;
	@FXML
	private ScrollPane Pizza_ScrollPane;
	@FXML
	private Label Total_Label;
	@FXML
	private ListView<OderItemTemplate> Commande_ListView;
	private List<OderItemTemplate> listOrder;
	private GridPane Pizza_GridPane;
	private OrderPizzaModele modele;
	public OrderPizzaControleur() {
		super(Contr.ORDERPIZZA);
		
	}

	@FXML
	private void initialize() {
		modele = (OrderPizzaModele)this.getModele(Contr.ORDERPIZZA);
		
		Commande_ListView.itemsProperty().bind(modele.getListPizza());
		listOrder = new ArrayList<OderItemTemplate>();
		
		update();
    }
	
	
	
	@Override
	public void update() {
		Pizza_GridPane = new GridPane();
		List<Pizza> listPizz = this.getService().getAllPizzaManage();
		if(listPizz.size()>0)
		{
			int columnIndex = 0;
			int rowIndex = 0;
			for(Pizza p : listPizz)
			{
				AnchorPane item = new AnchorPane();
				VBox hb = new VBox();
				hb.setPrefWidth(50);
				hb.setAlignment(Pos.CENTER);
				ImageView img = new ImageView(p.getPhoto());
				Label titre = new Label(p.getDesignation());
				Label composition = new Label(p.getComposition()
				                   .stream()
				                   .map( x -> x.getDesignation())
				                   .collect(Collectors.joining(", ")));
				composition.setWrapText(true);
				ChoiceBox<Taille>  ch = new ChoiceBox<Taille> ();
				ObservableList<Taille> ol_listTaille = FXCollections.observableArrayList(this.getService().getAllTaille());
				ch.setItems(ol_listTaille);

				ch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Taille>() {
				      @Override
				      public void changed(ObservableValue<? extends Taille> observableValue, Taille oldTaille, Taille newTaille) {
				       p.getPrix().setValue(p.getPrixBase()*newTaille.getPrixPonderation());
				      }
				    });

				ch.getSelectionModel().select(0);

				Button add = new Button();
				add.textProperty().bind(Bindings.createStringBinding(() -> {
				    final String value = "Add " + p.getPrix().getValue() +"€";
				    return value ;
				}, p.getPrix()));
				add.setOnAction(x-> AddPanier(p));
				
				hb.getChildren().add(titre);
				hb.getChildren().add(img);	
				hb.getChildren().add(composition);
				hb.getChildren().add(ch);
				hb.getChildren().add(add);
				
				item.getChildren().add(hb);		
				
				Pizza_GridPane.add(item,columnIndex,rowIndex);		
				
				if(columnIndex == 4)
				{
					columnIndex = 0;
					rowIndex++;					
				}
				else 
				{
					columnIndex++;
				}
			}	
			
		}
		
		Pizza_ScrollPane.setContent(Pizza_GridPane);

		Pizza_GridPane.setHgap(10); 
		Pizza_GridPane.setVgap(10);
		Pizza_GridPane.setPadding(new Insets(10, 10,10, 10));
	}
	
	private void AddPanier(Pizza p)
	{	
		boolean Addable = true;
		OderItemTemplate doublon = null;
		for(OderItemTemplate o : listOrder)
			if(o.getPizza().equals(p))
			{
				Addable = false;
				doublon=o;
			}
		
		if(Addable)
		{
			OderItemTemplate item = new OderItemTemplate(p);
			item.getButton().setOnAction(x->DelPanier(item));		
			listOrder.add(item);
		}else
		{
			
			
			
		}
		
		modele.getListPizza().setValue(FXCollections.observableArrayList(listOrder));
		
	}

	private void DelPanier(OderItemTemplate item)
	{
		listOrder.remove(item);
	}
	@Override
	public Pane getPane() {
		return OrderClient_AnchorPane;
	}

}
