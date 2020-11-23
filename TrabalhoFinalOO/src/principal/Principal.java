package principal;

import dados.Populacao;
import interfaceGrafica.Menu;

public class Principal {
	public static void main (String [] args) {
		// Atributos
		Populacao populacao = new Populacao();
		
		// Metodos
		new Menu().menu(populacao);
	}
}
