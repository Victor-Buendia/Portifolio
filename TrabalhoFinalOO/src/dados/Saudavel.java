package dados;

public class Saudavel extends Pessoa {
	private Integer idadeAnos;
	
	public Saudavel(String nomeCompleto, char genero, int identificacao, int idadeAnos) {
		super(nomeCompleto, genero, identificacao);
		setIdadeAnos(idadeAnos);
	}

	public Integer getIdadeAnos () {
		return idadeAnos;
	}

	public void setIdadeAnos (Integer idadeAnos) {
		this.idadeAnos = idadeAnos;
	}
	
	public String toString() {
		return getNomeCompleto() + "/" + getGenero() + "/" + getIdentificacao() + "/" + getIdadeAnos() + "/ ";
	}
}
