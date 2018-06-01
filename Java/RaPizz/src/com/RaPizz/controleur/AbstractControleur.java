package com.RaPizz.controleur;

import com.RaPizz.controleur.Mediateur.Contr;
import com.RaPizz.modele.service.Service;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class AbstractControleur {
	
	private Mediateur mediateur;
	private Contr viewEnum;
	private Stage primaryScene;
	
	
	protected AbstractControleur(Contr contr){
		viewEnum= contr;
		mediateur = Mediateur.getInstance(viewEnum,this);
		System.out.println(viewEnum);
	}
	
	public Stage getPrimaryScene() {
		return primaryScene;
	}

	public void setPrimaryScene(Stage primaryScene) {
		this.primaryScene = primaryScene;
	}
	
	public void close(){
		if(mediateur != null){
			mediateur.close();
		}
		mediateur=null;
	}
	
	protected Object getModele(Contr contr){
		return mediateur.getModele(contr);
	}
	
	protected Service getService(){
		return mediateur.getService();
	}
	
	
	private MainControleur getMainControleur(){
		return (MainControleur)mediateur.getControleur(Contr.MAIN);
	}
	
	public abstract void update();
	
	public abstract Pane getPane();
	
	protected void showLogin(){
		mediateur.getControleur(Contr.LOGIN).update();
		getMainControleur().getMainBorderPane().setCenter(mediateur.getControleur(Contr.LOGIN).getPane());
	}	
	
	protected void showSignUp(){
		mediateur.getControleur(Contr.SIGNUP).update();
		getMainControleur().getMainBorderPane().setCenter(mediateur.getControleur(Contr.SIGNUP).getPane());
	}	
	protected void showMenu(){
		mediateur.getControleur(Contr.MENU).update();
		getMainControleur().getMainBorderPane().setLeft(mediateur.getControleur(Contr.MENU).getPane());
	}
	protected void showHome(){
		mediateur.getControleur(Contr.HOME).update();
		getMainControleur().getMainBorderPane().setCenter(mediateur.getControleur(Contr.HOME).getPane());
	}
	protected void showHeader(){
		mediateur.getControleur(Contr.HEADER).update();
		getMainControleur().getMainBorderPane().setTop(mediateur.getControleur(Contr.HEADER).getPane());
	}	
	protected void showManagePizza(){
		mediateur.getControleur(Contr.MANAGEPIZZA).update();
		getMainControleur().getMainBorderPane().setCenter(mediateur.getControleur(Contr.MANAGEPIZZA).getPane());
	}
	protected void showOrderPizza(){
		mediateur.getControleur(Contr.ORDERPIZZA).update();
		getMainControleur().getMainBorderPane().setCenter(mediateur.getControleur(Contr.ORDERPIZZA).getPane());
	}		
}
