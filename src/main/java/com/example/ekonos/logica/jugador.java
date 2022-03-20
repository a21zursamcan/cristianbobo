package com.example.ekonos.logica;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class jugador {

//Variables 
	public ArrayList<carta> ma = new ArrayList<carta>();
	public int id;
	public Image peça;
	public casella posicio=null;
	public int unitatMonetaria=0;
	public int accions=18;
	public boolean teAccions;
	public String nom;

	public jugador(Image peça, int id, String nom) {
		this.peça=peça;
		this.id=id;
		this.nom=nom;
	}

	public String getMa() {
		String resposta = "";
		for(int x=0;x<this.ma.size();x++) {
			resposta=resposta+x+".-"+ma.get(x).toString()+"\n";
		}
		return resposta;
	}
	
}
