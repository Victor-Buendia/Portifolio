package dados;

public class Planta implements Comparable<Planta> {
	private StringBuffer nome;
	private Integer codigo;
	private Float pesoMedio;
	
	public Planta (String nome, Integer codigo, Float pesoMedio) {
		setNome(nome);
		setCodigo(codigo);
		setPesoMedio(pesoMedio);
	}
	
	public void setNome (String nome) {
		this.nome = new StringBuffer(nome);
	}
	
	public void setCodigo (Integer codigo) {
		this.codigo = codigo;
	}
	
	public void setPesoMedio (Float pesoMedio) {
		this.pesoMedio = pesoMedio;
	}
	
	public String getNome () {
		return this.nome.toString();
	}
	
	public Integer getCodigo () {
		return this.codigo;
	}
	
	public Float getPesoMedio () {
		return this.pesoMedio;
	}

	@Override
	public int compareTo(Planta planta) {
		return this.getNome().compareTo(planta.getNome());
	}
}
