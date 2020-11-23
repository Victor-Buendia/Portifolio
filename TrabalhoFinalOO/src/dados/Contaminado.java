package dados;

public class Contaminado extends Pessoa {
	private Character estadoSaude;
	
	public Contaminado(String nomeCompleto, char genero, int identificacao, char estadoSaude) {
		super(nomeCompleto, genero, identificacao);
		setEstadoSaude(estadoSaude);
	}

	public String getEstadoSaude () {
		return (this.estadoSaude == 'E' ? "EM TRATAMENTO" : this.estadoSaude == 'F' ? "FALECIDO" : "CURADO");
	}

	public void setEstadoSaude (Character estadoSaude) {
		this.estadoSaude = estadoSaude;
	}
	
	@Override
	public String toString() {
		return getNomeCompleto() + "/" + getGenero() + "/" + getIdentificacao() + "/ /" + getEstadoSaude();
	}
}
