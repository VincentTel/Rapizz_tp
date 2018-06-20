package com.RaPizz.controleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.MenuModele;
import com.RaPizz.modele.gui.OderItemTemplate;
import com.RaPizz.modele.gui.OrderPizzaModele;
import com.RaPizz.modele.metier.Pizza;
import com.RaPizz.modele.metier.Taille;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.util.converter.NumberStringConverter;

public class OrderPizzaControleur extends AbstractControleur{
	@FXML
	private AnchorPane OrderClient_AnchorPane;
	@FXML
	private ScrollPane Pizza_ScrollPane;
	@FXML
	private Label Total_Label;
	@FXML
	private ListView<OderItemTemplate> Commande_ListView;
	@FXML
	private Button Valide_Button;
	@FXML
	private HBox Order_HBox;
	@FXML
	private VBox Order_VBox;
	private List<OderItemTemplate> listOrder;
	private GridPane Pizza_GridPane;
	private OrderPizzaModele modele;
	public OrderPizzaControleur() {
		super(Contr.ORDERPIZZA);
		
	}
	
	public void ReInitializeData()
	{
		listOrder = new ArrayList<OderItemTemplate>();
		modele.getListPizza().setValue(FXCollections.observableArrayList(listOrder));
		modele.getTotal().setValue( CalcTotal()); 
		Valide_Button.setDisable(true);
	}
	
	@FXML
	private void initialize() {
		modele = (OrderPizzaModele)this.getModele(Contr.ORDERPIZZA);
		Order_VBox.getStyleClass().add("MenuBG");
		Order_HBox.getStyleClass().add("RedBG");
		OrderClient_AnchorPane.getStyleClass().add("RedBG");
		Commande_ListView.getStyleClass().add("RedBG");
		
		Commande_ListView.itemsProperty().bind(modele.getListPizza());		
		Total_Label.textProperty().bindBidirectional(modele.getTotal(),new NumberStringConverter());
		Valide_Button.setOnAction(x->ValidePanier());
		Valide_Button.getStyleClass().add("ValidButton");
		Valide_Button.setDisable(true);
		listOrder = new ArrayList<OderItemTemplate>();
		InitGridPane();
		update();
    }
	
	private void InitGridPane()
	{
		Pizza_GridPane = new GridPane();
		Pizza_GridPane.getStyleClass().add("RedBG");
		Pizza_GridPane.setHgap(10); 
		Pizza_GridPane.setVgap(10);
		Pizza_GridPane.setPadding(new Insets(10,80,10,0));
		Pizza_ScrollPane.setContent(Pizza_GridPane);
	}
	
	@Override
	public void update() {
		List<Pizza> listPizz = this.getService().getAllPizzaManage();
	
		int columnNumber = 6;
		if(listPizz.size()>0) 
		{   
			InitGridPane();
			int columnIndex = 0;
			int rowIndex = 0;
			for(Pizza p : listPizz)
			{
				AnchorPane item = new AnchorPane();
				
				VBox hb = new VBox();
				hb.setPrefWidth(150);
				hb.setAlignment(Pos.CENTER);
				hb.setSpacing(5);
				hb.getStyleClass().add("PizzaBox");
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
				add.setOnAction(x-> AddPanier(new Pizza(p)));
				
				hb.getChildren().add(titre);
				hb.getChildren().add(img);	
				hb.getChildren().add(composition);
				hb.getChildren().add(ch);
				
				item.getChildren().addAll(hb,add);	

				AnchorPane.setBottomAnchor(hb, 50.0);
				AnchorPane.setBottomAnchor(add, 0.0);
				AnchorPane.setLeftAnchor(add, 50.0);
				
				Pizza_GridPane.add(item,columnIndex,rowIndex);	

				if(columnIndex == columnNumber)
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
	}
	
	public void AddPanier(Pizza p)
	{	
		boolean Addable = true;
		OderItemTemplate doublon = null;
		for(OderItemTemplate o : listOrder)
		{
			if(o.getPizza().equals(p))
			{
				Addable = false;
				doublon=o;
			}
		}
		
		if(Addable)
		{
			OderItemTemplate item = new OderItemTemplate(p);
			item.getButton().setOnMouseClicked(x->DelPanier(item));		
			listOrder.add(item);
		}else
		{			
			doublon.setQt(doublon.getQt() + 1);			
		}
		
		modele.getListPizza().setValue(FXCollections.observableArrayList(listOrder));
		modele.getTotal().setValue( CalcTotal());
		Valide_Button.setDisable(false);
	}
	
	private float CalcTotal()
	{
		if(modele.getClient().getValue()!=null)
		{
		float result = 0;		
		int nbpizza=0;
		int nbPizzaClient = 0;
		if(modele.getClient().getValue().getPizzaGratuite().getValue() !=null)
			nbPizzaClient = modele.getClient().getValue().getPizzaGratuite().getValue();
		
		for(OderItemTemplate o : listOrder)
			nbpizza += o.getQt();
		
		if(nbpizza + nbPizzaClient >= 10)
		{
			for(OderItemTemplate o : listOrder) {
				for(int i=0 ; i<o.getQt();i++)
				{
					if(nbPizzaClient + 1 >=10)
						nbPizzaClient = 0;
					else
					{
						nbPizzaClient++;
						result += o.getPizza().getPrix().getValue();
					}	
				}
			}
		}
		else
			for(OderItemTemplate o : listOrder)
				result += o.getPrix();
			
		return result;
		}
		return 0;
	}

	private void DelPanier(OderItemTemplate item)
	{
				
		listOrder.remove(item);
		if(listOrder.size()>0)
			Valide_Button.setDisable(false);
		else
			Valide_Button.setDisable(true);
		
		modele.getListPizza().setValue(FXCollections.observableArrayList(listOrder));
		modele.getTotal().setValue( CalcTotal());
		
	}
	
	private void ValidePanier()
	{
		if(listOrder.size()>0)
		{
			MenuModele menumodele = (MenuModele) this.getModele(Contr.MENU);
			float total = CalcTotal();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Valide Order");
			alert.setHeaderText("The order "+ total +"€ will be dilvered at  "+ menumodele.getClientProperty().getValue().getAdr().toString());
			alert.setContentText("Are you ok with this?");
	
			Optional<ButtonType> result = alert.showAndWait();
			if( result.get() == ButtonType.OK)
			{ 
				if(  menumodele.getClientProperty().getValue().getPizzaGratuite().getValue() == 10)
				{
					this.getService().addOrder(listOrder, total , menumodele.getClientProperty().getValue());
				}
				else
				{
					if(total <= menumodele.getClientProperty().getValue().getSolde().getValue())
						this.getService().addOrder(listOrder, total , menumodele.getClientProperty().getValue());
					else
					{
							Alert info = new Alert(AlertType.INFORMATION);
							info.setTitle("More money please");
							info.setHeaderText(null);
							info.setContentText("You dont hava enough money ++");
							info.showAndWait();
					}
				}		
			}
		}
	}
	
	@Override
	public Pane getPane() {
		return OrderClient_AnchorPane;
	}

}
