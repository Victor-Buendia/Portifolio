package validacao;

import dados.Pessoa;
import dados.Populacao;

public class Validacao {
	public static boolean isNomeValido (String nome, int qtdLetras) {
		return (nome.length() > qtdLetras && !isNomeComNumeros(nome));
	}
	
	public static boolean isIdadeValida (String entrada) {
		final int MIN = 0, MAX = 130;
		try {
			int valor = Integer.parseInt(entrada);
			return (valor >= MIN && valor <= MAX);
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean isCodigoValido (String entrada, Populacao populacao) {
		try {
			int valor = Integer.parseInt(entrada);
			return (valor >= 1 && valor <= populacao.getPopulacao().size() && isEntradaNaColecao(valor, populacao));
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private static boolean isEntradaNaColecao (int valor, Populacao populacao) {
		for (Pessoa pessoa : populacao.getPopulacao()) {
			if (pessoa.getIdentificacao().equals(valor)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isNomeComNumeros (String nome) {
		for (char c : nome.toCharArray()) {
			if (Character.isDigit(c)) {
				return true;
			}
		}
		return false;
	}
}
