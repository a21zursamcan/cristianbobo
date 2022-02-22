package com.example.ekonos.logica;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;

public class casella {

	int numero;
	ArrayList<Integer> conexionsNumeriques = new ArrayList<Integer>();
	ArrayList<casella> conexions = new ArrayList<casella>();
	ImageView imgView;
	empresa empresa;
	boolean protejerCasilla=false;

	public casella(ImageView imgView, int... j) {
		this.imgView=imgView;
		this.numero = j[0];
		for (int x = 1; x < j.length; x++) {
			conexionsNumeriques.add(j[x]);
		}
	}

	public void setEmpresa(empresa empresa){
		if(empresa==this.empresa){
			//Si es la mateixa casella es fa protegida
			protejerCasilla();
		}else{
			this.empresa=empresa;
			imgView.setImage(empresa.casella);
		}
	}

	public void protejerCasilla(){
		Image image=imgView.getImage();
		File f= new File(image.getUrl());
		String nom=f.getName();
		nom=nom.substring(0,nom.length()-4);
		imgView.setImage(new Image(f.getParent()+nom+"P.png"));
	}

	public String toString() {
		String sortida = "Casella numero " + numero + " amb conexions amb ";
		for (int x = 0; x < conexions.size(); x++) {
			if (x == conexions.size() - 1) {
				sortida = sortida + " y ";
			} else {
				if (x == 0) {

				} else {
					sortida = sortida + ", ";
				}
			}
			sortida = sortida + conexions.get(x).numero;

		}
		return sortida;
	}

}
