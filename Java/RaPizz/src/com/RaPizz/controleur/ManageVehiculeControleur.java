package com.RaPizz.controleur;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.ManageVehicule;
import com.RaPizz.modele.metier.Vehicule;

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

public class ManageVehiculeControleur extends AbstractControleur
{
	@FXML
	private BorderPane ManageVehicule_BorderPane;
	@FXML
	private TextField Immat_TextField;
	@FXML
	private TextField Marque_TextField;
	@FXML
	private TextField Modele_TextField;
	@FXML
	private TextField Type_TextField;
	@FXML
	private Button Add_Button;
	@FXML
	private Button Update_Button;
	@FXML
	private Button Cancel_Button;
	@FXML
	private TableView<Vehicule> Vehicule_TableView;
	@FXML
	private TableColumn<Vehicule, String> Immat_TableColumn;
	@FXML
	private TableColumn<Vehicule, String> Marque_TableColumn;
	@FXML
	private TableColumn<Vehicule, String> Modele_TableColumn;
	@FXML
	private TableColumn<Vehicule, String> Type_TableColumn;
	@FXML
	private TableColumn<Vehicule, Vehicule> Action_TableColumn;
	@FXML
	private TableColumn<Vehicule, Vehicule> ActionUpdate_TableColumn;

	private ManageVehicule modele;

	public ManageVehiculeControleur()
	{
		super(Contr.MANAGEVEHICULE);
	}

	@FXML
	private void initialize()
	{
		modele = (ManageVehicule) this.getModele(Contr.MANAGEVEHICULE);
		ManageVehicule_BorderPane.getStyleClass().add("MenuBG");
		/* Grid part */
		Immat_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getImmat()));
		Marque_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getMarque()));
		Modele_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getModele()));
		Type_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getType()));
		
		Action_TableColumn
		.setCellValueFactory(cellData -> new SimpleObjectProperty<Vehicule>(
				cellData.getValue()));
		ActionUpdate_TableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Vehicule>(
				cellData.getValue()));

		/* Adding part */
		Immat_TextField.textProperty().bindBidirectional(
				modele.getImmatProperty());
		Marque_TextField.textProperty().bindBidirectional(
				modele.getMarqueProperty());
		Modele_TextField.textProperty().bindBidirectional(
				modele.getModeleProperty());
		Type_TextField.textProperty().bindBidirectional(
				modele.getTypeProperty());

		Callback<TableColumn<Vehicule, Vehicule>, TableCell<Vehicule, Vehicule>> actionDelCellFactory = new Callback<TableColumn<Vehicule, Vehicule>, TableCell<Vehicule, Vehicule>>()
		{
			public TableCell<Vehicule, Vehicule> call(TableColumn<Vehicule, Vehicule> v)
			{
				Label delImg = new Label("y");
				delImg.setTextFill(Color.RED);
				delImg.getStyleClass().add("Icons");
				TableCell<Vehicule, Vehicule> cell = new TableCell<Vehicule, Vehicule>()
				{
					@Override
					public void updateItem(Vehicule hb, boolean empty)
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
									dell(Vehicule_TableView.getSelectionModel()
											.getSelectedItem());
							}
						});
				return cell;

			}
		};

		Action_TableColumn.setCellFactory(actionDelCellFactory);

		Callback<TableColumn<Vehicule, Vehicule>, TableCell<Vehicule, Vehicule>> actionUpdateCellFactory = new Callback<TableColumn<Vehicule, Vehicule>, TableCell<Vehicule, Vehicule>>()
		{
			public TableCell<Vehicule, Vehicule> call(TableColumn<Vehicule, Vehicule> v)
			{
				Label upImg = new Label("C");
				upImg.setTextFill(Color.GREEN);
				upImg.getStyleClass().add("Icons");	
				TableCell<Vehicule, Vehicule> cell = new TableCell<Vehicule, Vehicule>()
				{
					@Override
					public void updateItem(Vehicule hb, boolean empty)
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
									ShowUpdateButton(Vehicule_TableView
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

	private void ShowUpdateButton(Vehicule v)
	{
		Add_Button.setVisible(false);
		Update_Button.setVisible(true);
		Cancel_Button.setVisible(true);

		modele.getImmatProperty().setValue(v.getImmat());
		modele.getMarqueProperty().setValue(v.getMarque());
		modele.getModeleProperty().setValue(v.getModele());
		modele.getTypeProperty().setValue(v.getType());

	}

	private void HideUpdateButton()
	{
		Add_Button.setVisible(true);
		Update_Button.setVisible(false);
		Cancel_Button.setVisible(false);
	}

	private void Upd()
	{
		Vehicule v = new Vehicule(modele.getImmatProperty().getValue(), modele.getMarqueProperty().getValue(), modele.getModeleProperty().getValue(), modele.getTypeProperty().getValue());
		this.getService().UpdateVehicule(v);
		HideUpdateButton();
		modele.modeleInit();
		update();
	}

	private void dell(Vehicule v)
	{
		this.getService().DelVehicule(v);
		update();
	}

	private void add()
	{
		Vehicule v = new Vehicule(modele.getImmatProperty().getValue(), modele
				.getMarqueProperty().getValue(), modele.getModeleProperty()
				.getValue(), modele.getTypeProperty().getValue());
		this.getService().AddVehicule(v);
		update();

	}

	@Override
	public void update()
	{
		ObservableList<Vehicule> ol_Vehicule = FXCollections
				.observableArrayList(this.getService().getAllVehiculeManage());
		Vehicule_TableView.getItems().setAll(ol_Vehicule);

		Action_TableColumn.setVisible(false);
		Action_TableColumn.setVisible(true);
	}

	@Override
	public Pane getPane()
	{
		return ManageVehicule_BorderPane;
	}
}
