package com.example.ekonos.logica;

public class carta {
	// Variables
    public empresa empresa = null;
	public String color1 = null;
	public String color2 = null;
	//Constructor
	carta(empresa empresa, String color1, String color2) {
		this.empresa = empresa;
		this.color1 = color1;
		this.color2 = color2;
	}
	
	public String toString() {
		return "["+empresa.nom+","+color1+","+color2+"]";
	}
}
