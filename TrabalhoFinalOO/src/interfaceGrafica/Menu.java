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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import dados.ColecaoPlantas;
import dados.Planta;
import saida.Visao;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Container containerMenu;
	private JButton botaoCadastrar, botaoListar, botaoConsultar, botaoPesquisar, botaoSair;
	
	public Menu () {		
		// Configuracoes Padroes da Interface Grafica
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void menu (ColecaoPlantas colecaoPlantas) {
		// Configuracoes JFrame
		setVisible(true);
		setTitle("Menu");
		setSize(600, 300);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerMenu = getContentPane();
		containerMenu.setLayout(new FlowLayout(1, 1000, 20));
		
		// Conficuracoes dos Componentes
		botaoCadastrar = criaBotao("Cadastrar");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EntradaDados().entradaDados(colecaoPlantas);
				dispose();
			}
		});
		containerMenu.add(botaoCadastrar);
		
		botaoListar = criaBotao("Listar");
		botaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ListarDados().listarDados(colecaoPlantas, colecaoPlantas.getColecaoPlantas());
				dispose();
			}
		});
		containerMenu.add(botaoListar);
		
		botaoConsultar = criaBotao("Consultar");
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		containerMenu.add(botaoConsultar);
		
		botaoPesquisar = criaBotao("Pesquisar");
		botaoPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Planta> listaPlantasOrdenada = new ArrayList<Planta>(colecaoPlantas.getColecaoPlantas());
				Collections.sort(listaPlantasOrdenada);
				new ListarDados().listarDadosOrdenados(colecaoPlantas, listaPlantasOrdenada);
				dispose();
			}
		});
		containerMenu.add(botaoPesquisar);
		
		botaoSair = criaBotao("Sair");
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Visao.mostraMensagemSimNao("Sair", "Deseja registrar outro Pesquisador e suas respectivas plantas ?")) {
					new Menu().menu(new ColecaoPlantas(""));
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