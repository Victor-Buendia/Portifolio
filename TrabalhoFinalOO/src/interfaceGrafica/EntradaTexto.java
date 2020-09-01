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
import bancoDeDados.Tabela;
import dados.Planta;
import dados.PlantaDAO;
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
	
	public void registrarNomePesquisador () {
		// Configuracoes JFrame
		setTitle("Registrar Pesquisador");
		setSize(540, 140);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerConsultarDados = getContentPane();
		containerConsultarDados.setLayout(new FlowLayout(1, 1000, 20));
		
		// Conficuracoes dos Componentes
		criaPainelCampoDeTexto("Digite o Nome do Pesquisador: ");
		
		botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setPreferredSize(new Dimension(95, 30));
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (!Validacao.isNomeValido(entradaDado.getText().trim())) {
					entradaDado.setText("");
				}
				else {
					Tabela.criaTabela(entradaDado.getText());
					new Menu().menu();
					dispose();
				}
			}
		});
		
		containerConsultarDados.add(painelCampoDeTexto);
		containerConsultarDados.add(botaoConfirmar);
	}
	
	public void consultarCodigo (List<Planta> bancoPlantas) {
		// Configuracoes JFrame
		setTitle("Consultar Planta");
		setSize(540, 140);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerConsultarDados = getContentPane();
		containerConsultarDados.setLayout(new FlowLayout(1, 20, 20));
		
		// Conficuracoes dos Componentes
		criaPainelCampoDeTexto("Digite o Codigo da Planta a ser pesquisada: ");
		criaPainelBotoes();
		
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (!Validacao.isCodigoValido(entradaDado.getText())) {
					entradaDado.setText("");
				}
				else {
					achaCodigoPlanta(PlantaDAO.getBancoPlantas(Tabela.getTableName()), Integer.parseInt(entradaDado.getText()));
					entradaDado.setText("");
				}
			}
		});
		
		containerConsultarDados.add(painelCampoDeTexto);
		containerConsultarDados.add(painelBotoes);
	}
	
	public void pesquisarNomePlanta () {
		// Configuracoes JFrame
		setTitle("Pesquisar Planta(s)");
		setSize(540, 140);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerConsultarDados = getContentPane();
		containerConsultarDados.setLayout(new FlowLayout(1, 20, 20));
		
		// Conficuracoes dos Componentes
		criaPainelBotoes();
		criaPainelCampoDeTexto("Digite o Nome da Planta a ser pesquisada: ");
		
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (!Validacao.isNomeValido(entradaDado.getText().trim())) {
					entradaDado.setText("");
				}
				else {
					List<Planta> listaPlantasOrdenada = new ArrayList<Planta>(PlantaDAO.getBancoPlantas(Tabela.getTableName()));
					Collections.sort(listaPlantasOrdenada);
					new ListarDados().listarDadosOrdenados(
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
	
	private void achaCodigoPlanta (List<Planta> listaPlantas, Integer codigoProcurado) {
		for (Planta planta : listaPlantas) {
			if (planta.getCodigo().equals(codigoProcurado)) {
				new Menu().menu();
				new MostrarTexto().mostraMensagem(
					Tabela.getTableName().toUpperCase(), 
					"Nome: " + planta.getNome() + "\nCodigo: " + planta.getCodigo() + "\nPeso Medio: " + planta.getPesoMedio()
				);
				dispose();
				return ;
			}
		}
		
		new MostrarTexto().mostraMensagem(
			"Alerta", 
			"Nenhuma Planta com o codigo [" + entradaDado.getText() + "] encontrada no sistema."
		);
	}
	
	private void criaPainelCampoDeTexto (String mensagem) {
		painelCampoDeTexto = new JPanel(new GridLayout(1, 2, 10, 20));
		etiq = new JLabel(mensagem);
		entradaDado = new JTextField();
		painelCampoDeTexto.add(etiq);
		painelCampoDeTexto.add(entradaDado);
	}
	
	private void criaPainelBotoes () {
		painelBotoes = new JPanel(new FlowLayout(1, 80, 0));
		
		botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setPreferredSize(new Dimension(95, 30));

		botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setPreferredSize(new Dimension(95, 30));
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new Menu().menu();
				dispose();
			}
		});
		
		painelBotoes.add(botaoConfirmar);
		painelBotoes.add(botaoCancelar);
	}
}