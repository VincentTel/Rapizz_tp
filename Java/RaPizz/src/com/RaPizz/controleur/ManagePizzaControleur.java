package com.RaPizz.controleur;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.ManagePizza;
import com.RaPizz.modele.metier.Ingredient;
import com.RaPizz.modele.metier.Pizza;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.control.TableView;

public class ManagePizzaControleur extends AbstractControleur {
	@FXML
	private BorderPane ManagePizza_BorderPane;
	@FXML
	private ImageView AddPhoto_ImageView;
	@FXML
	private TextField Designation_TextField;
	@FXML
	private TextField Prix_TextField;
	@FXML
	private ListView<Ingredient> Ingredrients_ListView;
	@FXML
	private Button Add_Button;	
	@FXML
	private Button Update_Button;
	@FXML
	private Button Cancel_Button;
	@FXML
	private TableView<Pizza> Pizza_TableView;
	@FXML
	private TableColumn<Pizza,Pizza> Photo_TableColumn;
	@FXML
	private TableColumn<Pizza,String> Designation_TableColumn;	
	@FXML
	private TableColumn<Pizza,Number> Prix_TableColumn;
	@FXML
	private TableColumn<Pizza,String> Ingredients_TableColumn;
	@FXML
	private TableColumn<Pizza,HBox> Action_TableColumn;
	@FXML
	private TableColumn<Pizza,HBox> ActionUpdate_TableColumn;
	
	private ManagePizza modele;
	
	public ManagePizzaControleur() {
		super(Contr.MANAGEPIZZA);		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
	private void initialize() {
		
		modele =(ManagePizza)this.getModele(Contr.MANAGEPIZZA);
		
		/*Grid part*/
		Designation_TableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDesignation()) );

		Prix_TableColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrixBase()) );
		
		Ingredients_TableColumn.setCellValueFactory(
				  ( TableColumn.CellDataFeatures<Pizza, String> i ) ->
				  {
				      List<Ingredient> ingredients = i.getValue().getComposition();
				      String val = ingredients
				                   .stream()
				                   .map( item -> item.getDesignation())
				                   .collect(Collectors.joining("\n"));
				      return new ReadOnlyStringWrapper( val );
				  });
		
		Photo_TableColumn.setCellValueFactory(cellData ->  new SimpleObjectProperty( cellData.getValue() ));
		Photo_TableColumn.setCellFactory(cellData -> {
	        final ImageView imageview = new ImageView();
	        TableCell<Pizza, Pizza> cell = new TableCell<Pizza, Pizza>() {
	            @Override
	            public void updateItem(Pizza item, boolean empty) {
	                if (item != null) {  
	                    imageview.setImage(item.getPhoto());
	                }else {
	                	imageview.setImage(null);
	                }
	            }
	        };
	        cell.setGraphic(imageview);

	        return cell;
	    });
		
		
		/*Adding part*/
		AddPhoto_ImageView.imageProperty().bind(modele.getPizzaImage());
		
		AddPhoto_ImageView.setOnMouseClicked(x -> {
	          FileChooser fileChooser = new FileChooser();	 
			  File file = fileChooser.showOpenDialog(this.getPrimaryScene());
              if (file != null) {
            	  Image i = new Image(file.toURI().toString());            	  
            	  modele.getPizzaImage().setValue(i);
              }
		});
		
		Designation_TextField.textProperty().bindBidirectional(modele.getDesignationProperty());
		Prix_TextField.textProperty().bindBidirectional(modele.getPrixProperty(),new NumberStringConverter());	
		
		Ingredrients_ListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);	
		Ingredrients_ListView.setCellFactory(param -> new ListCell<Ingredient>() {
		    @Override
		    protected void updateItem(Ingredient item, boolean empty) {
		        super.updateItem(item, empty);		        
		        if ( !(empty || item == null || item.getDesignation() == null))  {
		            setText(item.getDesignation());
		        }
		    }
		});
		
		Callback<TableColumn<Pizza, HBox>,TableCell<Pizza, HBox>> actionDelCellFactory = new Callback<TableColumn<Pizza, HBox>,TableCell<Pizza, HBox>>()
		{
			public TableCell<Pizza, HBox> call(TableColumn<Pizza, HBox> p) {
				ImageView delImg = new ImageView(new Image("com/RaPizz/images/del-icon.png"));	
				TableCell<Pizza, HBox> cell = new TableCell<Pizza, HBox>() {
						                				@Override
						                				public void updateItem(HBox hb, boolean empty) {
								                            super.updateItem(hb, empty);	
								                          	setGraphic(delImg);
						                				}
				};	
				cell.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                    	if(event.getClickCount()>1)
                       	 dell(Pizza_TableView.getSelectionModel().getSelectedItem());
                    }
				});
				return cell ;
						                
			}
		};
		Action_TableColumn.setCellFactory(actionDelCellFactory);
		Callback<TableColumn<Pizza, HBox>,TableCell<Pizza, HBox>> actionUpdateCellFactory = new Callback<TableColumn<Pizza, HBox>,TableCell<Pizza, HBox>>()
		{
			public TableCell<Pizza, HBox> call(TableColumn<Pizza, HBox> p) {
				ImageView delImg = new ImageView(new Image("com/RaPizz/images/update-icon.png"));	
				TableCell<Pizza, HBox> cell = new TableCell<Pizza, HBox>() {
						                				@Override
						                				public void updateItem(HBox hb, boolean empty) {
								                            super.updateItem(hb, empty);	
								                          	setGraphic(delImg);
						                				}
				};	
				cell.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                    	if(event.getClickCount()>1)
                    		ShowUpdateButton(Pizza_TableView.getSelectionModel().getSelectedItem());
                    }
				});
				return cell ;
						                
			}
		};
		ActionUpdate_TableColumn.setCellFactory(actionUpdateCellFactory);
		Add_Button.setOnAction(x-> add());
		Update_Button.setOnAction(x->Upd());
		Cancel_Button.setOnAction(x->HideUpdateButton());
		HideUpdateButton();
    }

	private void ShowUpdateButton(Pizza p)
	{
		Add_Button.setVisible(false);
		Update_Button.setVisible(true);
		Cancel_Button.setVisible(true);
		
		
		modele.getPizzaImage().setValue(p.getPhoto());
		modele.getIdPizzaProperty().setValue(p.getID_Pizza());
		modele.getDesignationProperty().setValue(p.getDesignation());
		modele.getPrixProperty().setValue(p.getPrixBase());
		for(Ingredient i : p.getComposition())
			Ingredrients_ListView.getSelectionModel().select(i);
		
	}	
	
	private void HideUpdateButton()
	{
		Add_Button.setVisible(true);
		Update_Button.setVisible(false);
		Cancel_Button.setVisible(false);
	}
	
	private void Upd()
	{
		Pizza p = null;
		if(modele.getPizzaImage().getValue()  == null || modele.getPizzaImage().getValue().isError())
		{

			if(Ingredrients_ListView.getSelectionModel().getSelectedItems().size()>0)
				p=new Pizza(modele.getIdPizzaProperty().getValue(), modele.getDesignationProperty().getValue(), modele.getPrixProperty().getValue(), Ingredrients_ListView.getSelectionModel().getSelectedItems());
			else
				p = new Pizza(modele.getIdPizzaProperty().getValue(),modele.getDesignationProperty().getValue(),modele.getPrixProperty().getValue());
		
		}else			
		{
			if(Ingredrients_ListView.getSelectionModel().getSelectedItems().size()>0)
				p = new Pizza(modele.getIdPizzaProperty().getValue(),modele.getDesignationProperty().getValue(),modele.getPrixProperty().getValue(),modele.getPizzaImage().getValue(),Ingredrients_ListView.getSelectionModel().getSelectedItems());
			else
				p = new Pizza(modele.getIdPizzaProperty().getValue(),modele.getDesignationProperty().getValue(),modele.getPrixProperty().getValue(),modele.getPizzaImage().getValue());
		}		
		this.getService().UpdatePizza(p);
		HideUpdateButton();
		modele.modeleInit();
		update();				
	}
	
	private void dell(Pizza p)
	{
		this.getService().DelPizza(p);
		update();
	}
	
	private void add()
	{
		Pizza p = null;
		if(modele.getPizzaImage().getValue()  == null || modele.getPizzaImage().getValue().isError())
		{
			if(Ingredrients_ListView.getSelectionModel().getSelectedItems().size()>0)
				p = new Pizza(modele.getDesignationProperty().getValue(),modele.getPrixProperty().getValue(),Ingredrients_ListView.getSelectionModel().getSelectedItems());
			else 
				p = new Pizza(modele.getDesignationProperty().getValue(),modele.getPrixProperty().getValue());
		}
		else
		{
			if(Ingredrients_ListView.getSelectionModel().getSelectedItems().size()>0)
				p = new Pizza(modele.getDesignationProperty().getValue(),modele.getPrixProperty().getValue(),modele.getPizzaImage().getValue(),Ingredrients_ListView.getSelectionModel().getSelectedItems());
			else
				p = new Pizza(modele.getDesignationProperty().getValue(),modele.getPrixProperty().getValue(),modele.getPizzaImage().getValue());
		}
		
		this.getService().AddPizza(p);
		update();
		
	}
	
	@Override
	public void update() {		
		ObservableList<Pizza> ol_Pizza = FXCollections.observableArrayList(this.getService().getAllPizzaManage());
		Pizza_TableView.getItems().setAll(ol_Pizza);
		
		modele.getListIngredientProperty().setValue(this.getService().getAllIngredient());		
		ObservableList<Ingredient> ol_ingredient = FXCollections.observableArrayList(modele.getListIngredientProperty().getValue());
		Ingredrients_ListView.getItems().setAll(ol_ingredient);	

		Action_TableColumn.setVisible(false);
		Action_TableColumn.setVisible(true);
	}

	@Override
	public Pane getPane() {
		return ManagePizza_BorderPane;
	}
}
