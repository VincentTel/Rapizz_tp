package com.RaPizz.modele.gui;

import com.RaPizz.modele.metier.Pizza;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class OderItemTemplate extends HBox {
	private Label nom_label = new Label();
	private Label  size_Label = new Label();
	private Label  qt_Label = new Label();
	private Label  prix_Label = new Label();
    private Button button = new Button();
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
         size_Label.setText(p.getSize().getDesignation());
         
         HBox.setHgrow(nom_label, Priority.ALWAYS);
         vb.getChildren().addAll(nom_label,size_Label,qt_Label,prix_Label);
         
         button.setText("X");

         this.getChildren().addAll( vb, button);
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
	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
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
