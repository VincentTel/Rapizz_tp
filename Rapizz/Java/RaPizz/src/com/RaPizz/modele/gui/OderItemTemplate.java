package com.RaPizz.modele.gui;

import java.util.List;

import com.RaPizz.modele.metier.Pizza;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class OderItemTemplate extends HBox {
	private Label nom_label = new Label();
	private Label  qt_Label = new Label();
	private Label  prix_Label = new Label();
    private Button button = new Button();
    private int qt;
    private Pizza pizza ;

    public OderItemTemplate(Pizza p) {
         super();
         pizza= p;
         qt = 1;
         
         nom_label.setText(p.getDesignation());
         nom_label.setMaxWidth(Double.MAX_VALUE);
         
         qt_Label.setText(String.format("Qt: %d",qt));
         prix_Label.setText(String.format("Prix: %d",pizza.getPrix().getValue()*qt ));  
         
         HBox.setHgrow(nom_label, Priority.ALWAYS);
         
         button.setText("X");

         this.getChildren().addAll(nom_label, button,qt_Label,prix_Label);
    }

	public int getQt() {
		return qt;
	}

	public void setQt(int qt) {
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

	
	
	
    
}
