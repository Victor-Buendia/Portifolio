package dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColecaoPlantas {
	private StringBuffer nomePesquisador;
	private List<Planta> colecaoPlantas;
	
	public ColecaoPlantas (String nomePesquisador) {
		setNomePesquisador(nomePesquisador);
		colecaoPlantas = new ArrayList<Planta>();
	}
	
	public void setNomePesquisador (String nomePesquisador) {
		this.nomePesquisador = new StringBuffer(nomePesquisador);
	}
	
	public void setColecaoPlantas (Planta planta) {
		this.colecaoPlantas.add(planta);
	}
	
	public String getNomePesquisador () {
		return this.nomePesquisador.toString();
	}
	
	public List<Planta> getColecaoPlantas () {
		return Collections.unmodifiableList(colecaoPlantas);
	}
}
