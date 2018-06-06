package com.RaPizz.controleur;

import java.time.LocalDate;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.metier.Commande;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ManageCommandeControleur extends AbstractControleur
{

	@FXML
	private BorderPane ManageCommande_BorderPane;
	@FXML
	private TableView<Commande> Commande_TableView;
	@FXML
	private TableColumn<Commande, Number> PrixTotal_TableColumn;
	@FXML
	private TableColumn<Commande, LocalDate> DateCommande_TableColumn;
	@FXML
	private TableColumn<Commande, LocalDate> DateLivraison_TableColumn;
	@FXML
	private TableColumn<Commande, Long> IdClient_TableColumn;
	@FXML
	private TableColumn<Commande, Long> IdLivreur_TableColumn;
	@FXML
	private TableColumn<Commande, String> ImmatVehicule_TableColumn;
	@FXML
	private TableColumn<Commande, HBox> Action_TableColumn;

	public ManageCommandeControleur()
	{
		super(Contr.MANAGECOMMANDE);
	}

	@FXML
	private void initialize()
	{
		/* Grid part */
		PrixTotal_TableColumn
				.setCellValueFactory(cellData -> new SimpleFloatProperty(
						cellData.getValue().getPrixTotal()));
		DateCommande_TableColumn
				.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(
						cellData.getValue().getDateCommande().toLocalDate()));
		DateLivraison_TableColumn
				.setCellValueFactory(cellData -> cellData.getValue().getDateLivraison() != null? new SimpleObjectProperty<LocalDate>(
						cellData.getValue().getDateLivraison().toLocalDate()) : new SimpleObjectProperty<LocalDate>());


		Callback<TableColumn<Commande, HBox>, TableCell<Commande, HBox>> actionDelCellFactory = new Callback<TableColumn<Commande, HBox>, TableCell<Commande, HBox>>()
		{
			public TableCell<Commande, HBox> call(TableColumn<Commande, HBox> p)
			{
				Label delImg = new Label("y");
				delImg.setTextFill(Color.RED);
				delImg.getStyleClass().add("Icons");
				TableCell<Commande, HBox> cell = new TableCell<Commande, HBox>()
				{
					@Override
					public void updateItem(HBox hb, boolean empty)
					{
						super.updateItem(hb, empty);
						setGraphic(delImg);
					}
				};
				cell.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent event)
							{
								if (event.getClickCount() > 1)
									dell(Commande_TableView.getSelectionModel()
											.getSelectedItem());
							}
						});
				return cell;

			}
		};
		
		Action_TableColumn.setCellFactory(actionDelCellFactory);
	}

	
	private void dell(Commande c)
	{
		this.getService().DelCommande(c);
		update();
	}

	@Override
	public void update()
	{
		ObservableList<Commande> ol_Commande = FXCollections
				.observableArrayList(this.getService().getAllCommandeManage());
		Commande_TableView.getItems().setAll(ol_Commande);
	}

	@Override
	public Pane getPane()
	{
		return ManageCommande_BorderPane;
	}

}
