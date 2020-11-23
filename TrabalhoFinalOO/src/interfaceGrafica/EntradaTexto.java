package interfaceGrafica;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dados.Pessoa;
import dados.Populacao;
import validacao.Validacao;

public class EntradaTexto extends JFrame {
	private static final long serialVersionUID = 1L;
		
	// Atributos
	private Container containerConsultarDados;
	private JLabel etiq;
	private JTextField entradaDado;
	private JButton botaoConfirmar, botaoCancelar;
	private JPanel painelBotoes, painelCampoDeTexto;
	
	public EntradaTexto () {		
		// Configuracoes gerais JFrame
		setVisible(true);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void consultarCodigo (Populacao populacao) {
		// Configuracoes JFrame
		setTitle("Consultar Pessoa");
		setSize(540, 140);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerConsultarDados = getContentPane();
		containerConsultarDados.setLayout(new FlowLayout(1, 20, 20));
		
		// Conficuracoes dos Componentes
		criaPainelCampoDeTexto("Digite o Codigo da Pessoa a ser pesquisada: ");
		criaPainelBotoes(populacao);
		
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (!Validacao.isCodigoValido(entradaDado.getText(), populacao)) {
					new MostrarTexto().mostraMensagem(
						"Erro na consulta", 
						"Nenhuma Pessoa com o codigo [" + entradaDado.getText() + "] encontrada no sistema." +
						"\nCertifique que o codigo inserido esteja entre 1 e " + (populacao.getPopulacao().size()) + "."
					);
					entradaDado.setText("");
				}
				else {
					achaCodigoPlanta(populacao, Integer.parseInt(entradaDado.getText()));
					entradaDado.setText("");
				}
			}
		});
		
		containerConsultarDados.add(painelCampoDeTexto);
		containerConsultarDados.add(painelBotoes);
	}
	
	public void pesquisarNomePessoa (Populacao populacao) {
		// Configuracoes JFrame
		setTitle("Pesquisar Pessoa(s)");
		setSize(540, 140);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerConsultarDados = getContentPane();
		containerConsultarDados.setLayout(new FlowLayout(1, 20, 20));
		
		// Conficuracoes dos Componentes
		criaPainelBotoes(populacao);
		criaPainelCampoDeTexto("Digite o Nome da Pessoa a ser pesquisada: ");
		
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (!Validacao.isNomeValido(entradaDado.getText().trim(), 0)) {
					entradaDado.setText("");
				}
				else {
					new ListarDados().listarDadosOrdenados(
						populacao,
						entradaDado.getText().trim()
					);
					dispose();
				};
			}
		});
		
		containerConsultarDados.add(painelCampoDeTexto);
		containerConsultarDados.add(painelBotoes);
	}
	
	private void achaCodigoPlanta (Populacao populacao, int codigoProcurado) {
		for (Pessoa pessoa : populacao.getPopulacao()) {
			if (pessoa.getIdentificacao().equals(codigoProcurado)) {
				String [] atributos = pessoa.toString().split("/");
				new Menu().menu(populacao);
				new MostrarTexto().mostraMensagem(
					"Pessoa encontrada",
					"\nNome: " + atributos[0] +
					"\nGenero: " + atributos[1] +
					"\nIdentificacao: " + atributos[2] +
					(atributos[3].equals(" ") ? "\nEstado de Saude: " + atributos[4] : "\nIdade: " + atributos[3])
				);
				dispose();
			}
		}
	}
	
	private void criaPainelCampoDeTexto (String mensagem) {
		painelCampoDeTexto = new JPanel(new GridLayout(1, 2, 10, 20));
		etiq = new JLabel(mensagem);
		entradaDado = new JTextField();
		painelCampoDeTexto.add(etiq);
		painelCampoDeTexto.add(entradaDado);
	}
	
	private void criaPainelBotoes (Populacao populacao) {
		painelBotoes = new JPanel(new FlowLayout(1, 80, 0));
		
		botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setPreferredSize(new Dimension(95, 30));

		botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setPreferredSize(new Dimension(95, 30));
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new Menu().menu(populacao);
				dispose();
			}
		});
		
		painelBotoes.add(botaoConfirmar);
		painelBotoes.add(botaoCancelar);
	}
}