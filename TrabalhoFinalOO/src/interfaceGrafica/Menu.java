package interfaceGrafica;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import dados.Populacao;
import saida.Visao;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Atribudos
	private Container containerMenu, containerMenuRegistro;
	private JButton botaoCadastrar, botaoListar, botaoConsultar, botaoPesquisar, botaoSair, botaoCancelar;
	private JButton registrarContamindado, registrarSaudavel;
	private JPanel painelBotoes;
	private JTextArea caixaMensagem;
	
	public Menu () {		
		// Configuracoes gerais JFrame
		setVisible(true);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void menu (Populacao populacao) {
		// Configuracoes JFrame
		setTitle("Menu");
		setSize(600, 300);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerMenu = getContentPane();
		containerMenu.setLayout(new FlowLayout(1, 1000, 20));
		
		// Conficuracoes dos Componentes
		botaoCadastrar = criaBotao("Cadastrar Pessoa", 170, 30);
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				containerMenu.removeAll();
				menuRegistro(populacao);
			}
		});
		containerMenu.add(botaoCadastrar);
		
		botaoListar = criaBotao("Listar Populacao", 170, 30);
		botaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				List<Planta> listaPlantas = PlantaDAO.getBancoPlantas(Tabela.getTableName());
				if (!populacao.getPopulacao().isEmpty()) {
					new ListarDados().listarDados(populacao);
					dispose();
				}
				else {
					new MostrarTexto().mostraMensagem(
						"Error",
						"E necessario registrar no minimo uma planta para acessar esta opcao."
					);
				}
			}
		});
		containerMenu.add(botaoListar);
		
		botaoConsultar = criaBotao("Consultar Populacao", 170, 30);
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
////				List<Planta> listaPlantas = PlantaDAO.getBancoPlantas(Tabela.getTableName());
				if (!populacao.getPopulacao().isEmpty()) {
					new EntradaTexto().consultarCodigo(populacao);
					dispose();
				}
				else {
					new MostrarTexto().mostraMensagem(
						"Error", 
						"E necessario registrar no minimo uma planta para acessar esta opcao."
					);
				}
			}
		});
		containerMenu.add(botaoConsultar);
		
		botaoPesquisar = criaBotao("Pesquisar Nome", 170, 30);
		botaoPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				List<Planta> listaPlantas = PlantaDAO.getBancoPlantas(Tabela.getTableName());
				if (!populacao.getPopulacao().isEmpty()) {
					new EntradaTexto().pesquisarNomePessoa(populacao);
					dispose();
				}
				else {
					new MostrarTexto().mostraMensagem(
						"Error", 
						"E necessario registrar no minimo uma planta para acessar esta opcao."
					);
				}
			}
		});
		containerMenu.add(botaoPesquisar);
		
		botaoSair = criaBotao("Sair", 170, 30);
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				Tabela.deletaTabela(Tabela.getTableName());
				Visao.mostraResultadoFinal(populacao);
				System.exit(0);
			}
		});
		containerMenu.add(botaoSair);
	}
	
	public void menuRegistro (Populacao populacao) {
		// Configuracoes JFrame
		setTitle("Menu Registro");
		setSize(530, 140);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerMenuRegistro = getContentPane();
		containerMenuRegistro.setLayout(new FlowLayout(1, 5, 20));
		
		// Conficuracoes dos Componentes
		caixaMensagem = new JTextArea("Escolha a situacao da Pessoa");
		caixaMensagem.setEditable(false);
		caixaMensagem.setBackground(new Color(238, 238, 238));
		containerMenuRegistro.add(caixaMensagem);
		
		painelBotoes = new JPanel(new FlowLayout(1, 20, 0));
		registrarContamindado = criaBotao("Contamindado", 150, 30);
		registrarContamindado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new RegistraDados().entradaContaminado(populacao);
				dispose();
			}
		});
		painelBotoes.add(registrarContamindado);
		
		registrarSaudavel = criaBotao("Saudavel", 150, 30);
		registrarSaudavel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new RegistraDados().entradaSaudavel(populacao);
				dispose();
			}
		});
		painelBotoes.add(registrarSaudavel);
		
		botaoCancelar = criaBotao("Cancelar", 150, 30);
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new Menu().menu(populacao);
				dispose();
			}
		});
		painelBotoes.add(botaoCancelar);
		containerMenuRegistro.add(painelBotoes);
	}
	
	private JButton criaBotao (String mensagem, int size_x, int size_y) {
		JButton botao = new JButton(mensagem);
		botao.setPreferredSize(new Dimension(size_x, size_y));
		return botao;
	}
}
