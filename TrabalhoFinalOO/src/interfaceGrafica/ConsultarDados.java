package interfaceGrafica;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dados.ColecaoPlantas;
import dados.Planta;
import validacao.Validacao;

public class ConsultarDados extends JFrame {
	private static final long serialVersionUID = 1L;
		
	// Atributos
	private Container containerConsultarDados;
	private JLabel etiq;
	private JTextField entradaDado;
	private JButton botaoConfirmar, botaoCancelar;
	private JPanel painelBotoes, painelCampoDeTexto;
	
	public ConsultarDados () {		
		// Configuracoes gerais JFrame
		setVisible(true);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void consultarCodigo (ColecaoPlantas colecaoPlantas) {
		// Configuracoes JFrame
		setTitle("Consultar Planta");
		setSize(540, 140);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerConsultarDados = getContentPane();
		containerConsultarDados.setLayout(new FlowLayout(1, 20, 20));
		
		// Conficuracoes dos Componentes
		criaPainelBotoes(colecaoPlantas);
		criaPaineCampoDeTexto("Digite o Codigo da Planta a ser pesquisada: ");
		
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (!Validacao.isCodigoValido(entradaDado.getText())) {
					entradaDado.setText("");
				}
				else {
					
					dispose();
				}
			}
		});
		
		containerConsultarDados.add(painelCampoDeTexto);
		containerConsultarDados.add(painelBotoes);
	}
	
	public void pesquisarNome (ColecaoPlantas colecaoPlantas) {
		// Configuracoes JFrame
		setTitle("Pesquisar Planta(s)");
		setSize(540, 140);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerConsultarDados = getContentPane();
		containerConsultarDados.setLayout(new FlowLayout(1, 20, 20));
		
		// Conficuracoes dos Componentes
		criaPainelBotoes(colecaoPlantas);
		criaPaineCampoDeTexto("Digite o Nome da Planta a ser pesquisada: ");
		
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (!Validacao.isNomeValido(entradaDado.getText().trim())) {
					entradaDado.setText("");
				}
				else {
					List<Planta> listaPlantasOrdenada = new ArrayList<Planta>(colecaoPlantas.getColecaoPlantas());
					Collections.sort(listaPlantasOrdenada);
					new ListarDados().listarDadosOrdenados(
						colecaoPlantas, 
						listaPlantasOrdenada,
						entradaDado.getText().trim()
					);
					dispose();
				};
			}
		});
		
		containerConsultarDados.add(painelCampoDeTexto);
		containerConsultarDados.add(painelBotoes);
	}
	
	private void criaPaineCampoDeTexto (String mensagem) {
		painelCampoDeTexto = new JPanel(new GridLayout(1, 2, 10, 20));
		etiq = new JLabel(mensagem);
		entradaDado = new JTextField();
		painelCampoDeTexto.add(etiq);
		painelCampoDeTexto.add(entradaDado);
	}
	
	private void criaPainelBotoes (ColecaoPlantas colecaoPlantas) {
		painelBotoes = new JPanel(new FlowLayout(1, 80, 0));
		
		botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setPreferredSize(new Dimension(95, 30));

		botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setPreferredSize(new Dimension(95, 30));
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Menu().menu(colecaoPlantas);
				dispose();
			}
		});
		
		painelBotoes.add(botaoConfirmar);
		painelBotoes.add(botaoCancelar);
	}
}