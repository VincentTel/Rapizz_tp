package com.RaPizz.controleur;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.ManageLivreur;
import com.RaPizz.modele.metier.Livreur;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

public class ManageLivreurControleur extends AbstractControleur
{
	@FXML
	private BorderPane ManageLivreur_BorderPane;
	@FXML
	private ImageView AddPhoto_ImageView;
	@FXML
	private TextField Nom_TextField;
	@FXML
	private TextField Prenom_TextField;
	@FXML
	private TextField Username_TextField;
	@FXML
	private TextField Password_TextField;
	@FXML
	private TextField Email_TextField;
	@FXML
	private DatePicker DateEmbauche_DatePicker;
	@FXML
	private TextField Salaire_TextField;
	@FXML
	private Button Add_Button;
	@FXML
	private Button Update_Button;
	@FXML
	private Button Cancel_Button;
	@FXML
	private TableView<Livreur> Livreur_TableView;
	@FXML
	private TableColumn<Livreur, Livreur> Photo_TableColumn;
	@FXML
	private TableColumn<Livreur, String> Prenom_TableColumn;
	@FXML
	private TableColumn<Livreur, String> Nom_TableColumn;
	@FXML
	private TableColumn<Livreur, String> Username_TableColumn;
	@FXML
	private TableColumn<Livreur, String> Password_TableColumn;
	@FXML
	private TableColumn<Livreur, String> Email_TableColumn;
	@FXML
	private TableColumn<Livreur, Number> Salaire_TableColumn;
	@FXML
	private TableColumn<Livreur, LocalDate> DateEmbauche_TableColumn;
	@FXML
	private TableColumn<Livreur, Livreur> Action_TableColumn;
	@FXML
	private TableColumn<Livreur, Livreur> ActionUpdate_TableColumn;
	@FXML
	private TableColumn<Livreur, Integer> Retard_TableColumn;
	@FXML
	private TextField Retard_TextField;

	private ManageLivreur modele;

	public ManageLivreurControleur()
	{
		super(Contr.MANAGELIVREUR);
	}

	@FXML
	private void initialize()
	{
		modele = (ManageLivreur) this.getModele(Contr.MANAGELIVREUR);
		ManageLivreur_BorderPane.getStyleClass().add("MenuBG");
		/* Grid part */
		Prenom_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getPrenom()));

		Nom_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getNom()));

		Username_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getUserName()));

		Password_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getPassword()));

		Email_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getEmail()));

		Salaire_TableColumn
				.setCellValueFactory(cellData -> new SimpleFloatProperty(
						cellData.getValue().getSalaire()));
		DateEmbauche_TableColumn
				.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(
						cellData.getValue().getLocalDate().toLocalDate()));
		Photo_TableColumn
				.setCellValueFactory(cellData -> new SimpleObjectProperty<Livreur>(
						cellData.getValue()));
		Retard_TableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(
				cellData.getValue().getRetard()));
		
		Action_TableColumn
		.setCellValueFactory(cellData -> new SimpleObjectProperty<Livreur>(
				cellData.getValue()));
		ActionUpdate_TableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Livreur>(
				cellData.getValue()));
		
		Photo_TableColumn
				.setCellFactory(cellData ->
				{
					final ImageView imageview = new ImageView();
			        imageview.setFitHeight(150); 
			        imageview.setFitWidth(150);
					TableCell<Livreur, Livreur> cell = new TableCell<Livreur, Livreur>()
					{
						@Override
						public void updateItem(Livreur item, boolean empty)
						{
							 if(item != null)
							{
								imageview.setImage(item.getPhoto());
							}
							else
							{
								imageview.setImage(null);
							}
						}
					};
					cell.setGraphic(imageview);

					return cell;
				});

		/* Adding part */
		AddPhoto_ImageView.imageProperty().bind(modele.getLivreurImage());

		AddPhoto_ImageView.setOnMouseClicked(x ->
		{
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(this.getPrimaryScene());
			if (file != null)
			{
				Image i = new Image(file.toURI().toString());
				modele.getLivreurImage().setValue(i);
			}
		});

		Nom_TextField.textProperty().bindBidirectional(modele.getNomProperty());
		Prenom_TextField.textProperty().bindBidirectional(
				modele.getPrenomProperty());
		Username_TextField.textProperty().bindBidirectional(
				modele.getUsernameProperty());
		Password_TextField.textProperty().bindBidirectional(
				modele.getPasswordProperty());
		Email_TextField.textProperty().bindBidirectional(
				modele.getEmailProperty());
		DateEmbauche_DatePicker.valueProperty().bindBidirectional(
				modele.getDateEmbaucheProperty());
		Salaire_TextField.textProperty().bindBidirectional(
				modele.getSalaireProperty(), new NumberStringConverter());		
		Retard_TextField.textProperty().bindBidirectional(
				modele.getRetardProperty(), new NumberStringConverter());
		
		Callback<TableColumn<Livreur, Livreur>, TableCell<Livreur, Livreur>> actionDelCellFactory = new Callback<TableColumn<Livreur, Livreur>, TableCell<Livreur, Livreur>>()
		{
			public TableCell<Livreur, Livreur> call(TableColumn<Livreur, Livreur> p)
			{
				Label delImg = new Label("y");
				delImg.setTextFill(Color.RED);
				delImg.getStyleClass().add("Icons");
				TableCell<Livreur, Livreur> cell = new TableCell<Livreur, Livreur>()
				{
					@Override
					public void updateItem(Livreur hb, boolean empty)
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
									dell(Livreur_TableView.getSelectionModel()
											.getSelectedItem());
							}
						});
				return cell;

			}
		};
		Action_TableColumn.setCellFactory(actionDelCellFactory);
		Callback<TableColumn<Livreur, Livreur>, TableCell<Livreur, Livreur>> actionUpdateCellFactory = new Callback<TableColumn<Livreur, Livreur>, TableCell<Livreur, Livreur>>()
		{
			public TableCell<Livreur, Livreur> call(TableColumn<Livreur, Livreur> p)
			{
				Label upImg = new Label("C");
				upImg.setTextFill(Color.GREEN);
				upImg.getStyleClass().add("Icons");	
				TableCell<Livreur, Livreur> cell = new TableCell<Livreur, Livreur>()
				{
					@Override
					public void updateItem(Livreur hb, boolean empty)
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
									ShowUpdateButton(Livreur_TableView
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

	private void ShowUpdateButton(Livreur l)
	{
		Add_Button.setVisible(false);
		Update_Button.setVisible(true);
		Cancel_Button.setVisible(true);

		modele.getLivreurImage().setValue(l.getPhoto());
		modele.getIdLivreurProperty().setValue(l.getID());
		modele.getPrenomProperty().setValue(l.getPrenom());
		modele.getNomProperty().setValue(l.getNom());
		modele.getUsernameProperty().setValue(l.getUserName());
		modele.getPasswordProperty().setValue(l.getPassword());
		modele.getEmailProperty().setValue(l.getEmail());
		modele.getSalaireProperty().setValue(l.getSalaire());
		modele.getDateEmbaucheProperty().set(l.getLocalDate().toLocalDate());
		modele.getRetardProperty().setValue(l.getRetard());
	}

	private void HideUpdateButton()
	{
		Add_Button.setVisible(true);
		Update_Button.setVisible(false);
		Cancel_Button.setVisible(false);
	}

	private void Upd()
	{
		Livreur l = null;
		if (modele.getLivreurImage().getValue() == null
				|| modele.getLivreurImage().getValue().isError())
		{
			l = new Livreur(modele.getIdLivreurProperty().getValue(), modele
					.getPrenomProperty().getValue(), modele.getNomProperty()
					.getValue(), modele.getUsernameProperty().getValue(),
					modele.getPasswordProperty().getValue(), modele
							.getEmailProperty().getValue(), modele
							.getSalaireProperty().getValue(),
					Date.valueOf(modele.getDateEmbaucheProperty().getValue()));
			l.setRetard(modele.getRetardProperty().getValue());
		}
		else
		{
			l = new Livreur(modele.getIdLivreurProperty().getValue(), modele
					.getPrenomProperty().getValue(), modele.getNomProperty()
					.getValue(), modele.getUsernameProperty().getValue(),
					modele.getPasswordProperty().getValue(), modele
							.getEmailProperty().getValue(), modele
							.getLivreurImage().getValue(), modele
							.getSalaireProperty().getValue(),
					Date.valueOf(modele.getDateEmbaucheProperty().getValue()));

			l.setRetard(modele.getRetardProperty().getValue());
		}
		this.getService().UpdateLivreur(l);
		HideUpdateButton();
		modele.modeleInit();
		update();
	}

	private void dell(Livreur l)
	{
		this.getService().DelLivreur(l);
		update();
	}

	private void add()
	{
		Livreur l = null;
		if (modele.getLivreurImage().getValue() == null
				|| modele.getLivreurImage().getValue().isError())
		{
			l = new Livreur(modele.getIdLivreurProperty().getValue(), modele
					.getPrenomProperty().getValue(), modele.getNomProperty()
					.getValue(), modele.getUsernameProperty().getValue(),
					modele.getPasswordProperty().getValue(), modele
							.getEmailProperty().getValue(), modele
							.getSalaireProperty().getValue(),
					Date.valueOf(modele.getDateEmbaucheProperty().getValue()));
		}
		else
		{
			l = new Livreur(modele.getIdLivreurProperty().getValue(), modele
					.getPrenomProperty().getValue(), modele.getNomProperty()
					.getValue(), modele.getUsernameProperty().getValue(),
					modele.getPasswordProperty().getValue(), modele
							.getEmailProperty().getValue(), modele
							.getLivreurImage().getValue(), modele
							.getSalaireProperty().getValue(),
					Date.valueOf(modele.getDateEmbaucheProperty().getValue()));
		}

		this.getService().AddLivreur(l);
		update();
	}

	@Override
	public void update()
	{
		ObservableList<Livreur> ol_Livreur = FXCollections
				.observableArrayList(this.getService().getAllLivreurManage());
		Livreur_TableView.getItems().setAll(ol_Livreur);

		Action_TableColumn.setVisible(false);
		Action_TableColumn.setVisible(true);
	}

	@Override
	public Pane getPane()
	{
		return ManageLivreur_BorderPane;
	}
}
