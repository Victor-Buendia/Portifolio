package interfaceGrafica;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MostrarTexto extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Atributos
	private Container containerMostraTexto;
	private JTextArea caixaMensagem;
	private JButton botaoOk;
	
	public MostrarTexto () {		
		// Configuracoes gerais JFrame
		setVisible(true);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	
	public void mostraMensagem (String titulo, String mensagem) {
		// Configuracoes JFrame
		setTitle(titulo);
		
		int width = (mensagem.length() * 7);
		int height = 1;
		for (char c : mensagem.toCharArray()) {
			if (c == '\n') {
				height++;
			}
		}
		
		setSize(width < 300 ? 300 : width, 120 + height * 17);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerMostraTexto = getContentPane();
		containerMostraTexto.setLayout(new FlowLayout(1, 1000, 20));
		
		// Conficuracoes dos Componentes
		caixaMensagem = new JTextArea(mensagem);
		caixaMensagem.setEditable(false);
		caixaMensagem.setBackground(new Color(238, 238, 238));
		
		botaoOk = new JButton("OK");
		botaoOk.setPreferredSize(new Dimension(95, 30));
		botaoOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});
		
		containerMostraTexto.add(caixaMensagem);
		containerMostraTexto.add(botaoOk);
	}
}