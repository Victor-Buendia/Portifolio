package validacao;

import java.util.List;
import dados.Planta;
import interfaceGrafica.MostrarTexto;

public class Validacao {
	public static boolean isNomeValido (String nome) {
		final int TAM_MINIMO = 3;
		if (nome.length() < TAM_MINIMO || isDigitosNome(nome)) {
			new MostrarTexto().mostraMensagem(
				"Error", 
				"Nome deve possuir no minimo " + TAM_MINIMO + " caracteres que nao podem ser numericos."
			);
			return false;
		}
		return true;
	}
	
	public static boolean isCodigoValido (String codigo) {
		final int VALOR_MINIMO = 100;
		try {
			int valor = Integer.parseInt(codigo);
			if (valor <= VALOR_MINIMO) {
				new MostrarTexto().mostraMensagem(
					"Error", 
					"O codigo deve ser maior que " + VALOR_MINIMO + "."
				);
				return false;
			}
		} catch (NumberFormatException e) {
			new MostrarTexto().mostraMensagem(
				"Error", 
				"A entrada deve ser numerica."
			);
			return false;
		}
		return true;
	}
	
	public static boolean isCodigoValido (String codigo, List<Planta> listaPlantas) {
		if (isCodigoValido(codigo)) {
			if (!isCodigoUnico(Integer.parseInt(codigo), listaPlantas)) {
				new MostrarTexto().mostraMensagem(
					"Error", 
					"O codigo deve ser unico."
				);
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean isPesoMedioValido (String pesoMedio) {
		final float VALOR_MINIMO = 0f;
		final float VALOR_MAXIMO = 1f;
		try {
			float valor = Float.parseFloat(pesoMedio);
			if (valor < VALOR_MINIMO || valor > VALOR_MAXIMO) {
				new MostrarTexto().mostraMensagem(
					"Error", 
					"O peso medio deve estar entre " + VALOR_MINIMO + " e " + VALOR_MAXIMO + "."
				);
				return false;
			}
		} catch (NumberFormatException e) {
			new MostrarTexto().mostraMensagem(
				"Error", 
				"A entrada deve ser numerica."
			);
			return false;
		}
		return true;
	}
	
	private static boolean isDigitosNome (String nome) {
		for (char c : nome.toCharArray()) {
			if (Character.isDigit(c)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isCodigoUnico (Integer codigo, List<Planta> listaPlantas) {
		for (Planta planta : listaPlantas) {
			if (planta.getCodigo().equals(codigo)) {
				return false;
			}
		}
		return true;
	}
}
