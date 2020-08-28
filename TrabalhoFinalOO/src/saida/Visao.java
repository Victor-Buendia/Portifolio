package saida;

import javax.swing.JOptionPane;

public class Visao {
	public static boolean mostraMensagemSimNao (String titulo, String mensagem) {
		return (JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == 0 ? true : false);
	}
}