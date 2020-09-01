package interfaceGrafica;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import bancoDeDados.Tabela;
import dados.Planta;
import dados.PlantaDAO;
import saida.Visao;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Container containerMenu;
	private JButton botaoCadastrar, botaoListar, botaoConsultar, botaoPesquisar, botaoSair;
	
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
	
	public void menu () {
		// Configuracoes JFrame
		setTitle("Menu");
		setSize(600, 300);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerMenu = getContentPane();
		containerMenu.setLayout(new FlowLayout(1, 1000, 20));
		
		// Conficuracoes dos Componentes
		botaoCadastrar = criaBotao("Cadastrar");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new RegistraDados().entradaDados();
				dispose();
			}
		});
		containerMenu.add(botaoCadastrar);
		
		botaoListar = criaBotao("Listar");
		botaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				List<Planta> listaPlantas = PlantaDAO.getBancoPlantas(Tabela.getTableName());
				if (!listaPlantas.isEmpty()) {
					new ListarDados().listarDados(listaPlantas);
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
		
		botaoConsultar = criaBotao("Consultar");
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				List<Planta> listaPlantas = PlantaDAO.getBancoPlantas(Tabela.getTableName());
				if (!listaPlantas.isEmpty()) {
					new EntradaTexto().consultarCodigo(listaPlantas);
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
		
		botaoPesquisar = criaBotao("Pesquisar");
		botaoPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				List<Planta> listaPlantas = PlantaDAO.getBancoPlantas(Tabela.getTableName());
				if (!listaPlantas.isEmpty()) {
					new EntradaTexto().pesquisarNomePlanta();
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
		
		botaoSair = criaBotao("Sair");
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				Tabela.deletaTabela(Tabela.getTableName());
				if (Visao.mostraMensagemSimNao("Sair", "Deseja registrar outro Pesquisador e suas respectivas plantas ?")) {
					new EntradaTexto().registrarNomePesquisador();
					dispose();
				}
				else {
					System.exit(0);
				}
			}
		});
		containerMenu.add(botaoSair);
	}
	
	private JButton criaBotao (String mensagem) {
		JButton botao = new JButton(mensagem);
		botao.setPreferredSize(new Dimension(95, 30));
		return botao;
	}
}
