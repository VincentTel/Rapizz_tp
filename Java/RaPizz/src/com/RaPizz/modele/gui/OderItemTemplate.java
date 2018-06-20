package com.RaPizz.modele.gui;

import com.RaPizz.modele.metier.Pizza;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class OderItemTemplate extends AnchorPane {
	private Label nom_label = new Label();
	private Label  size_Label = new Label();
	private Label  qt_Label = new Label();
	private Label  prix_Label = new Label();
    private Label button = new Label();
    private VBox vb = new VBox();
    private float prixTT;
    private int qt;
    private Pizza pizza ;
   

    public OderItemTemplate(Pizza p) {
         super();
         pizza= p;
         setQt(1);
         
         nom_label.setText(p.getDesignation());         
         nom_label.setMaxWidth(Double.MAX_VALUE);
         nom_label.setTextFill(Color.WHITE);
         size_Label.setText(p.getSize().getDesignation());

         size_Label.setTextFill(Color.WHITE);
         qt_Label.setTextFill(Color.WHITE);
         prix_Label.setTextFill(Color.WHITE);
         button.setTextFill(Color.WHITE);
         
        // HBox.setHgrow(nom_label, Priority.ALWAYS);
         vb.getChildren().addAll(nom_label,size_Label,qt_Label,prix_Label);
         
         button.setText("y");
         button.getStyleClass().add("UpdateButton");
         button.getStyleClass().add("Icons");
         
         

         this.getChildren().addAll( vb, button);
         AnchorPane.setLeftAnchor(vb, 0.0);
         AnchorPane.setRightAnchor(button, 0.0);
         
    }

	public int getQt() {
		return qt;
	}

	public void setQt(int qt) {
        qt_Label.setText(String.format("Qt: %d",qt));        
        prixTT = pizza.getPrix().getValue()*qt ;
        prix_Label.setText(String.format("Prix: %f", prixTT));          
		this.qt = qt;
	}
	public Label getButton() {
		return button;
	}

	public void setButton(Label button) {
		this.button = button;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	
	public float getPrix() {
		return prixTT;
	}

}
