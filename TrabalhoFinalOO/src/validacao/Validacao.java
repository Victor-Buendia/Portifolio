package validacao;

import dados.ColecaoPlantas;
import dados.Planta;
import saida.Visao;

public class Validacao {
	public static boolean isNomeValido (String nome) {
		final int TAM_MINIMO = 3;
		if (nome.length() < TAM_MINIMO || isDigitosNome(nome)) {
			Visao.mostraMensagemErro("Error", "Nome deve possuir no minimo " + TAM_MINIMO + " caracteres que nao podem ser numericos.");
			return false;
		}
		return true;
	}
	
	public static boolean isCodigoValido (String codigo, ColecaoPlantas colecaoPlantas) {
		final int VALOR_MINIMO = 100;
		try {
			int valor = Integer.parseInt(codigo);
			if (valor <= VALOR_MINIMO || !isCodigoUnico(valor, colecaoPlantas)) {
				Visao.mostraMensagemErro("Error", "O codigo deve ser unico e maior que " + VALOR_MINIMO + ".");
				return false;
			}
		} catch (NumberFormatException e) {
			Visao.mostraMensagemErro("Error", "A entrada deve ser numerica.");
			return false;
		}
		return true;
	}
	
	public static boolean isPesoMedioValido (String pesoMedio) {
		final float VALOR_MINIMO = 1.5f;
		final float VALOR_MAXIMO = 100f;
		try {
			float valor = Float.parseFloat(pesoMedio);
			if (valor < VALOR_MINIMO || valor > VALOR_MAXIMO) {
				Visao.mostraMensagemErro("Error", "O peso medio deve estar entre [" + VALOR_MINIMO + " e " + VALOR_MAXIMO + "].");
				return false;
			}
		} catch (NumberFormatException e) {
			Visao.mostraMensagemErro("Error", "A entrada deve ser numerica.");
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
	
	private static boolean isCodigoUnico (Integer codigo, ColecaoPlantas colecaoPlantas) {
		for (Planta planta : colecaoPlantas.getColecaoPlantas()) {
			if (planta.getCodigo().equals(codigo)) {
				return false;
			}
		}
		return true;
	}
}
