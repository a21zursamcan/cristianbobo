package com.example.ekonos.logica;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class empresa {
	//Variables
	int FactorCreixement;
	String color;
	int marcadorDeCreixement;
	jugador president = null;
	public String nom;
	ArrayList<jugador> accions = new ArrayList<jugador>();
	public Image carta=null;
	public Image casella=null;
	ImageView imgPresident;
	public ArrayList<ImageView> ImatgesAccions;
	public ArrayList<Pane> PaneFactorsDeCreixement=new ArrayList<>();

	empresa(String nom, String color, ImageView imgPresident, ArrayList<ImageView> ImatgesAccions) {
		this.nom = nom;
		this.color = color;
		this.imgPresident=imgPresident;
		this.ImatgesAccions=ImatgesAccions;
		creaCartayCasellaEmpresa();

	}

	public void actualitzaImatgesAccions(){
		for(int x=0; x<this.ImatgesAccions.size(); x++){
			try{
				this.ImatgesAccions.get(x).setImage(accions.get(x).peça);
			}catch (IndexOutOfBoundsException e){
				this.ImatgesAccions.get(x).setImage(null);
			}
		}
	}

	public void actualitzaFactorCreixement() {
		for (int x=0; x<this.PaneFactorsDeCreixement.size();x++){
			if (x>this.FactorCreixement){
				PaneFactorsDeCreixement.get(x).setOpacity(0.5);
			}
		}
	}

	public void borraFactorCreixement(){
		for (int x=0; x<this.PaneFactorsDeCreixement.size();x++){
			PaneFactorsDeCreixement.get(x).setOpacity(0);
		}
	}

	public void creaCartayCasellaEmpresa(){
		carta = new Image("file:src"+File.separator+"main"+File.separator+"resources"+File.separator+"com"+File.separator+"example"+File.separator+"ekonos"+File.separator+"images"+File.separator+nom.toLowerCase(Locale.ROOT)+".png");
		casella = new Image("file:src"+File.separator+"main"+File.separator+"resources"+File.separator+"com"+File.separator+"example"+File.separator+"ekonos"+File.separator+"images"+File.separator+nom.toLowerCase(Locale.ROOT)+"F.png");
	}

	public int numeroAccionsJugador(jugador jugador) {
		int numero = 0;
		for (int x = 0; x < accions.size(); x++) {
			if (accions.get(x) == jugador) {
				numero++;
			}
		}
		return numero;
	}

	public void eliminaUltimaAccio(jugador jugador){
		//Busquem l'ultima accio del jugador
		int posicioAccio=0;
		for(int x=0; x<accions.size();x++){
			if(accions.get(x)==jugador){
				posicioAccio=x;
			}
		}
		//Una vegada que sabem quina és l'última acció del jugador l'eliminem
		accions.remove(posicioAccio);
	}

	public int valorVenta(){
		return this.accions.size();
	}

	public int valorCompra(){
		return this.accions.size()+1;
	}

	public String toString() {
		return this.nom + "<" + this.color + ">{" + marcadorDeCreixement + "}";
	}

	public void actualitzaImatgePresident(){
		if(this.president!=null){
			imgPresident.setImage(president.peça);
		}
	}


}