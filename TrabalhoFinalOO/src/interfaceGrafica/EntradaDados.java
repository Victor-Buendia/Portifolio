package interfaceGrafica;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import validacao.Validacao;
import dados.ColecaoPlantas;
import dados.Planta;

public class EntradaDados extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Atributos
	private Container containerEntradaDados;
	private JTextField nome, codigo, pesoMedio;
	private JPanel painelTextos, painelBotoes;
	private JLabel etiq1, etiq2, etiq3;
	private JButton botaoConfirmar, botaoCancelar;
	
	public EntradaDados () {		
		// Configuracoes Padroes da Interface Grafica
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void entradaDados (ColecaoPlantas colecaoPlantas) {
		// Configuracoes JFrame
		setVisible(true);
		setTitle("Registro de Plantas");
		setSize(500, 220);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerEntradaDados = getContentPane();
		containerEntradaDados.setLayout(new FlowLayout(1, 20, 20));
		
		// Conficuracoes dos Componentes
		painelTextos = new JPanel(new GridLayout(3, 2, 10, 20));
		painelBotoes = new JPanel(new FlowLayout(1, 80, 0));
		
		etiq1 = new JLabel("Digite o Nome da Planta: ");
		nome = new JTextField();
		painelTextos.add(etiq1);
		painelTextos.add(nome);
		
		etiq2 = new JLabel("Digite o Codigo da Planta: ");
		codigo = new JTextField();
		painelTextos.add(etiq2);
		painelTextos.add(codigo);
		
		etiq3 = new JLabel("Digite o Peso Medio da Planta: ");
		pesoMedio = new JTextField();
		painelTextos.add(etiq3);
		painelTextos.add(pesoMedio);
		
		botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setPreferredSize(new Dimension(95, 30));
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				acaoBotaoConfirmar(evt, colecaoPlantas);
			}
		});
		painelBotoes.add(botaoConfirmar);
		
		botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setPreferredSize(new Dimension(95, 30));
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Menu().menu(colecaoPlantas);
				dispose();
			}
		});
		painelBotoes.add(botaoCancelar);
		
		containerEntradaDados.add(painelTextos);
		containerEntradaDados.add(painelBotoes);
	}
	
	private void acaoBotaoConfirmar (ActionEvent evt, ColecaoPlantas colecaoPlantas) {
		if (!Validacao.isNomeValido(nome.getText())) {
			nome.setText("");
		}
		else if (!Validacao.isCodigoValido(codigo.getText(), colecaoPlantas)) {
			codigo.setText("");
		}
		else if (!Validacao.isPesoMedioValido(pesoMedio.getText())) {
			pesoMedio.setText("");
		}
		else {
			colecaoPlantas.setColecaoPlantas(new Planta(
				nome.getText(), 
				Integer.parseInt(codigo.getText()), 
				Float.parseFloat(pesoMedio.getText())
			));
			new Menu().menu(colecaoPlantas);
			dispose();
		}
	}
}