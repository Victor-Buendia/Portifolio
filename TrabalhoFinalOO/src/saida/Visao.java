package saida;

import javax.swing.JOptionPane;
import dados.Planta;

public class Visao {
	public static void mostraDados (Planta planta) {
		
	}
	
	public static void mostraMensagemErro (String titulo, String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
	}
	
	public static boolean mostraMensagemSimNao (String titulo, String mensagem) {
		return (JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == 0 ? true : false);
	}
}