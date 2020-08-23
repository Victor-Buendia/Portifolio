package principal;

import dados.ColecaoPlantas;
import interfaceGrafica.Menu;

public class Principal {
	public static void main (String [] args) {
		//Atributos
		ColecaoPlantas colecaoPlantas;
		
		//Metodos
		colecaoPlantas = new ColecaoPlantas("");
		new Menu().menu(colecaoPlantas);
	}
}
