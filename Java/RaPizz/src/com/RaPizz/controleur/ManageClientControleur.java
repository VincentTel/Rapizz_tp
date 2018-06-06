package com.RaPizz.controleur;

import java.io.File;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.gui.ManageClient;
import com.RaPizz.modele.metier.Client;
import com.RaPizz.modele.metier.Ingredient;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.control.TableView;

public class ManageClientControleur extends AbstractControleur
{
	@FXML
	private BorderPane ManageClient_BorderPane;
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
	private TextField NumRue_TextField;
	@FXML
	private TextField Rue_TextField;
	@FXML
	private TextField Ville_TextField;
	@FXML
	private TextField CodePostal_TextField;
	@FXML
	private TextField PizzaGratuite_TextField;
	@FXML
	private TextField Solde_TextField;
	@FXML
	private ListView<Ingredient> Ingredrients_ListView;
	@FXML
	private Button Add_Button;
	@FXML
	private Button Update_Button;
	@FXML
	private Button Cancel_Button;
	@FXML
	private TableView<Client> Client_TableView;
	@FXML
	private TableColumn<Client, Client> Photo_TableColumn;
	@FXML
	private TableColumn<Client, String> Prenom_TableColumn;
	@FXML
	private TableColumn<Client, String> Nom_TableColumn;
	@FXML
	private TableColumn<Client, String> Username_TableColumn;
	@FXML
	private TableColumn<Client, String> Password_TableColumn;
	@FXML
	private TableColumn<Client, String> Email_TableColumn;
	@FXML
	private TableColumn<Client, String> NumRue_TableColumn;
	@FXML
	private TableColumn<Client, String> Rue_TableColumn;
	@FXML
	private TableColumn<Client, String> Ville_TableColumn;
	@FXML
	private TableColumn<Client, String> CodePostal_TableColumn;
	@FXML
	private TableColumn<Client, Integer> PizzaGratuite_TableColumn;
	@FXML
	private TableColumn<Client, Float> Solde_TableColumn;
	@FXML
	private TableColumn<Client, HBox> Action_TableColumn;
	@FXML
	private TableColumn<Client, HBox> ActionUpdate_TableColumn;

	private ManageClient modele;

	public ManageClientControleur()
	{
		super(Contr.MANAGECLIENT);
	}

	@FXML
	private void initialize()
	{
		modele = (ManageClient) this.getModele(Contr.MANAGECLIENT);

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

		NumRue_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getAdr().getNumRue()));

		Rue_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getAdr().getRue()));

		Ville_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getAdr().getVille()));

		CodePostal_TableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getAdr().getCp()));

		PizzaGratuite_TableColumn
				.setCellValueFactory(cellData -> 
						cellData.getValue().getPizzaGratuite().asObject());

		Solde_TableColumn
				.setCellValueFactory(cellData ->
						cellData.getValue().getSolde().asObject());

		/*Photo_TableColumn
				.setCellValueFactory(cellData -> new SimpleObjectProperty<>(
						cellData.getValue()));*/

		Photo_TableColumn.setCellFactory(cellData ->
		{
			final ImageView imageview = new ImageView();
			TableCell<Client, Client> cell = new TableCell<Client, Client>()
			{
				@Override
				public void updateItem(Client item, boolean empty)
				{
					if (item != null)
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
		AddPhoto_ImageView.imageProperty().bind(modele.getClientImage());

		AddPhoto_ImageView.setOnMouseClicked(x ->
		{
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(this.getPrimaryScene());
			if (file != null)
			{
				Image i = new Image(file.toURI().toString());
				modele.getClientImage().setValue(i);
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
		NumRue_TextField.textProperty().bindBidirectional(
				modele.getNumRueProperty());
		Rue_TextField.textProperty().bindBidirectional(modele.getRueProperty());
		Ville_TextField.textProperty().bindBidirectional(
				modele.getVilleProperty());
		CodePostal_TextField.textProperty().bindBidirectional(
				modele.getCodePostalProperty());
		PizzaGratuite_TextField.textProperty().bindBidirectional(
				modele.getPizzaGratuiteProperty(), new NumberStringConverter());
		Solde_TextField.textProperty().bindBidirectional(
				modele.getSoldeProperty(), new NumberStringConverter());

		Callback<TableColumn<Client, HBox>, TableCell<Client, HBox>> actionDelCellFactory = new Callback<TableColumn<Client, HBox>, TableCell<Client, HBox>>()
		{
			public TableCell<Client, HBox> call(TableColumn<Client, HBox> p)
			{
				Label delImg = new Label("y");
				delImg.setTextFill(Color.RED);
				delImg.getStyleClass().add("Icons");
				TableCell<Client, HBox> cell = new TableCell<Client, HBox>()
				{
					@Override
					public void updateItem(HBox hb, boolean empty)
					{
						super.updateItem(hb, empty);
						if(!empty)
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
									dell(Client_TableView.getSelectionModel()
											.getSelectedItem());
							}
						});
				return cell;

			}
		};
		Action_TableColumn.setCellFactory(actionDelCellFactory);
		Callback<TableColumn<Client, HBox>, TableCell<Client, HBox>> actionUpdateCellFactory = new Callback<TableColumn<Client, HBox>, TableCell<Client, HBox>>()
		{
			public TableCell<Client, HBox> call(TableColumn<Client, HBox> p)
			{
				Label upImg = new Label("C");
				upImg.setTextFill(Color.GREEN);
				upImg.getStyleClass().add("Icons");	
				TableCell<Client, HBox> cell = new TableCell<Client, HBox>()
				{
					@Override
					public void updateItem(HBox hb, boolean empty)
					{
						super.updateItem(hb, empty);
						if(!empty)
							setGraphic(upImg);
					}
				};
				cell.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>()
						{
							@Override
							public void handle(MouseEvent event)
							{
								if (event.getClickCount() > 1)
									ShowUpdateButton(Client_TableView
											.getSelectionModel()
											.getSelectedItem());
							}
						});
				return cell;

			}
		};
		ActionUpdate_TableColumn.setCellFactory(actionUpdateCellFactory);
		Add_Button.setOnAction(x -> add());
		Update_Button.setOnAction(x -> Upd());
		Cancel_Button.setOnAction(x -> HideUpdateButton());
		HideUpdateButton();
	}

	private void ShowUpdateButton(Client c)
	{
		Add_Button.setVisible(false);
		Update_Button.setVisible(true);
		Cancel_Button.setVisible(true);

		modele.getClientImage().setValue(c.getPhoto());
		modele.getIdClientProperty().setValue(c.getID());
		modele.getPrenomProperty().setValue(c.getPrenom());
		modele.getNomProperty().setValue(c.getNom());
		modele.getUsernameProperty().setValue(c.getUserName());
		modele.getPasswordProperty().setValue(c.getPassword());
		modele.getEmailProperty().setValue(c.getEmail());
		modele.getNumRueProperty().setValue(c.getAdr().getNumRue());
		modele.getRueProperty().setValue(c.getAdr().getRue());
		modele.getVilleProperty().setValue(c.getAdr().getVille());
		modele.getCodePostalProperty().setValue(c.getAdr().getCp());
		modele.getPizzaGratuiteProperty().setValue(c.getPizzaGratuite().getValue());
		modele.getSoldeProperty().setValue(c.getSolde().getValue());
	}

	private void HideUpdateButton()
	{
		Add_Button.setVisible(true);
		Update_Button.setVisible(false);
		Cancel_Button.setVisible(false);
	}

	private void Upd()
	{
		Client c = null;
		if (modele.getClientImage().getValue() == null
				|| modele.getClientImage().getValue().isError())
		{
			c = new Client(modele.getIdClientProperty().getValue(), modele
					.getPrenomProperty().getValue(), modele.getNomProperty()
					.getValue(), modele.getUsernameProperty().getValue(),
					modele.getPasswordProperty().getValue(), modele
							.getEmailProperty().getValue(), modele
							.getNumRueProperty().getValue(), modele
							.getRueProperty().getValue(), modele
							.getVilleProperty().getValue(), modele
							.getCodePostalProperty().getValue(), modele
							.getSoldeProperty().getValue(), modele
							.getPizzaGratuiteProperty().getValue());
		}
		else
		{
			c = new Client(modele.getIdClientProperty().getValue(), modele
					.getPrenomProperty().getValue(), modele.getNomProperty()
					.getValue(), modele.getUsernameProperty().getValue(),
					modele.getPasswordProperty().getValue(), modele
							.getEmailProperty().getValue(), modele
							.getClientImage().getValue(), modele
							.getNumRueProperty().getValue(), modele
							.getRueProperty().getValue(), modele
							.getVilleProperty().getValue(), modele
							.getCodePostalProperty().getValue(), modele
							.getSoldeProperty().getValue(), modele
							.getPizzaGratuiteProperty().getValue());
		}
		this.getService().UpdateClient(c);
		HideUpdateButton();
		modele.modeleInit();
		update();
	}

	private void dell(Client c)
	{
		this.getService().DelClient(c);
		update();
	}

	private void add()
	{
		Client c = null;
		if (modele.getClientImage().getValue() == null
				|| modele.getClientImage().getValue().isError())
		{
			c = new Client(modele.getIdClientProperty().getValue(), modele
					.getPrenomProperty().getValue(), modele.getNomProperty()
					.getValue(), modele.getUsernameProperty().getValue(),
					modele.getPasswordProperty().getValue(), modele
							.getEmailProperty().getValue(), modele
							.getNumRueProperty().getValue(), modele
							.getRueProperty().getValue(), modele
							.getVilleProperty().getValue(), modele
							.getCodePostalProperty().getValue(), modele
							.getSoldeProperty().getValue(), modele
							.getPizzaGratuiteProperty().getValue());
		}
		else
		{
			c = new Client(modele.getIdClientProperty().getValue(), modele
					.getPrenomProperty().getValue(), modele.getNomProperty()
					.getValue(), modele.getUsernameProperty().getValue(),
					modele.getPasswordProperty().getValue(), modele
							.getEmailProperty().getValue(), modele
							.getClientImage().getValue(), modele
							.getNumRueProperty().getValue(), modele
							.getRueProperty().getValue(), modele
							.getVilleProperty().getValue(), modele
							.getCodePostalProperty().getValue(), modele
							.getSoldeProperty().getValue(), modele
							.getPizzaGratuiteProperty().getValue());
		}

		this.getService().AddClient(c);
		update();

	}

	@Override
	public void update()
	{
		ObservableList<Client> ol_Client = FXCollections
				.observableArrayList(this.getService().getAllClientManage());
		Client_TableView.getItems().setAll(ol_Client);

		Action_TableColumn.setVisible(false);
		Action_TableColumn.setVisible(true);
	}

	@Override
	public Pane getPane()
	{
		return ManageClient_BorderPane;
	}
}
