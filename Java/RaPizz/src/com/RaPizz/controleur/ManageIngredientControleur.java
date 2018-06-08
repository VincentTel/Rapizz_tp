package com.RaPizz.controleur;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.ManageIngredient;
import com.RaPizz.modele.metier.Ingredient;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.scene.control.TableView;

public class ManageIngredientControleur extends AbstractControleur
{
	@FXML
	private BorderPane ManageIngredients_BorderPane;
	@FXML
	private TextField Designation_TextField;
	@FXML
	private Button Add_Button;
	@FXML
	private Button Update_Button;
	@FXML
	private Button Cancel_Button;
	@FXML
	private TableView<Ingredient> Ingredients_TableView;
	@FXML
	private TableColumn<Ingredient, String> Designation_TableColumn;
	@FXML
	private TableColumn<Ingredient, Ingredient> Action_TableColumn;
	@FXML
	private TableColumn<Ingredient, Ingredient> ActionUpdate_TableColumn;

	private ManageIngredient modele;

	public ManageIngredientControleur()
	{
		super(Contr.MANAGEINGREDIENT);
	}
	
	@FXML
	private void initialize()
	{
		modele = (ManageIngredient) this.getModele(Contr.MANAGEINGREDIENT);
		ManageIngredients_BorderPane.getStyleClass().add("MenuBG");
		/* Grid part */
		Designation_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getDesignation()));

		/* Adding part */
		Designation_TextField.textProperty().bindBidirectional(
				modele.getDesignationProperty());
		Action_TableColumn
		.setCellValueFactory(cellData -> new SimpleObjectProperty<Ingredient>(
				cellData.getValue()));
		ActionUpdate_TableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Ingredient>(
				cellData.getValue()));

		Callback<TableColumn<Ingredient, Ingredient>, TableCell<Ingredient, Ingredient>> actionDelCellFactory = new Callback<TableColumn<Ingredient, Ingredient>, TableCell<Ingredient, Ingredient>>()
		{
			public TableCell<Ingredient, Ingredient> call(
					TableColumn<Ingredient, Ingredient> i)
			{
				Label delImg = new Label("y");
				delImg.setTextFill(Color.RED);
				delImg.getStyleClass().add("Icons");
				TableCell<Ingredient, Ingredient> cell = new TableCell<Ingredient, Ingredient>()
				{
					@Override
					public void updateItem(Ingredient hb, boolean empty)
					{
						super.updateItem(hb, empty);
						 if(hb != null)
							 setGraphic(delImg);
						 else
							 setGraphic(null);
					}
				};
				cell.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent event)
							{
								if (event.getClickCount() > 1)
									dell(Ingredients_TableView
											.getSelectionModel()
											.getSelectedItem());
							}
						});
				return cell;

			}
		};

		Action_TableColumn.setCellFactory(actionDelCellFactory);

		Callback<TableColumn<Ingredient, Ingredient>, TableCell<Ingredient, Ingredient>> actionUpdateCellFactory = new Callback<TableColumn<Ingredient, Ingredient>, TableCell<Ingredient, Ingredient>>()
		{
			public TableCell<Ingredient, Ingredient> call(
					TableColumn<Ingredient, Ingredient> i)
			{
				Label upImg = new Label("C");
				upImg.setTextFill(Color.GREEN);
				upImg.getStyleClass().add("Icons");	
				TableCell<Ingredient, Ingredient> cell = new TableCell<Ingredient, Ingredient>()
				{
					@Override
					public void updateItem(Ingredient hb, boolean empty)
					{
						super.updateItem(hb, empty);
						 if(hb != null)
							 setGraphic(upImg);
						 else
							 setGraphic(null);
					}
				};
				cell.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent event)
							{
								if (event.getClickCount() > 1)
									ShowUpdateButton(Ingredients_TableView
											.getSelectionModel()
											.getSelectedItem());
							}
						});
				return cell;

			}
		};

		ActionUpdate_TableColumn.setCellFactory(actionUpdateCellFactory);
		Add_Button.setOnAction(x -> add());
		Add_Button.getStyleClass().add("ValidButton");
		Update_Button.setOnAction(x -> Upd());
		Update_Button.getStyleClass().add("UpdateButton");
		Cancel_Button.setOnAction(x -> HideUpdateButton());
		Cancel_Button.getStyleClass().add("CancelButton");
		HideUpdateButton();
	}

	private void ShowUpdateButton(Ingredient i)
	{
		Add_Button.setVisible(false);
		Update_Button.setVisible(true);
		Cancel_Button.setVisible(true);

		modele.getID_IngredientProperty().setValue(i.getID_Ingredient());
		modele.getDesignationProperty().setValue(i.getDesignation());
	}

	private void HideUpdateButton()
	{
		Add_Button.setVisible(true);
		Update_Button.setVisible(false);
		Cancel_Button.setVisible(false);
	}

	private void Upd()
	{
		Ingredient i = new Ingredient(modele.getID_IngredientProperty().getValue(),modele.getDesignationProperty().getValue());

		this.getService().UpdateIngredient(i);
		HideUpdateButton();
		modele.modeleInit();
		update();
	}

	private void dell(Ingredient i)
	{
		this.getService().DelIngredient(i);
		update();
	}

	private void add()
	{
		Ingredient i = new Ingredient(modele.getDesignationProperty().getValue());

		this.getService().AddIngredient(i);
		update();

	}

	@Override
	public void update()
	{
		ObservableList<Ingredient> ol_Ingredient = FXCollections.observableArrayList(this.getService().getAllIngredient());
		Ingredients_TableView.getItems().setAll(ol_Ingredient);

		Action_TableColumn.setVisible(false);
		Action_TableColumn.setVisible(true);
	}

	@Override
	public Pane getPane()
	{
		return ManageIngredients_BorderPane;
	}
}
