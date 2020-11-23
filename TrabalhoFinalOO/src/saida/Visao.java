package saida;

import java.text.DecimalFormat;
import dados.Populacao;

public class Visao {
	public static void mostraResultadoFinal (Populacao populacao) {
		imprimeCaracter(50, '\n');
		DecimalFormat mascara = new DecimalFormat("00");
		mostraMensagem(mascara.format(populacao.qtdNaoContaminados()) + " = NÃO CONTAMINADOS");
		mostraMensagem(mascara.format(populacao.qtdContaminadosTratamento()) + " = CONTAMINADOS EM TRATAMENTO");
		mostraMensagem(mascara.format(populacao.qtdContaminadosCurados()) + " = CONTAMINADOS CURADOS");
		mostraMensagem(mascara.format(populacao.qtdMulheresFalecidas()) + " = MULHERES CONTAMINADAS FALECIDAS");
		mostraMensagem(mascara.format(populacao.qtdHomensFalecidos()) + " = HOMENS CONTAMINADOS FALECIDOS");
		mostraMensagemVermelho(mascara.format(populacao.getPopulacao().size()) + " = TOTAL DE PESSOAS CADASTRAS");
	}
	
	private static void mostraMensagem (String mensagem) {
		System.out.format("%-20s%s\n", "", mensagem);
	}
	
	private static void mostraMensagemVermelho (String mensagem) {
		imprimeCaracter(20, ' ');
		System.err.println(mensagem);
	}

	private static void imprimeCaracter (int qtd, char valor) {
		for (int i = 0; i < qtd; i++) {
			System.out.print(valor);
		}
	}
}
