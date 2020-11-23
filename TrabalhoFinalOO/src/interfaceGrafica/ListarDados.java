package interfaceGrafica;

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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import dados.Pessoa;
import dados.Populacao;
import ordenadores.OrdenaNome;

public class ListarDados extends JFrame {
private static final long serialVersionUID = 1L;
	
	private Container containerListaDados;
	private JPanel painelTabela;
	private JTable tabelaConteudos;
	private JScrollPane barraRolagem;
	private JLabel etiq;
	private JButton botaoConfirmar;
	private DefaultTableCellRenderer renderizarCelulas = new DefaultTableCellRenderer();
	private DefaultTableModel modelo = new DefaultTableModel() {
		private static final long serialVersionUID = 1L;
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	
	public ListarDados () {		
		// Configuracoes gerais JFrame
		setVisible(true);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void listarDados (Populacao populacao) {
		// Configuracoes proprias JFrame
		setTitle("Listar Populacao");
		setSize(680, 350);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerListaDados = getContentPane();
		containerListaDados.setLayout(new FlowLayout(1, 20, 20));
			
		// Conficuracoes dos Componentes
		criaTabela();
		criaBotoes(populacao);
		for (Pessoa pessoa : populacao.getPopulacao())
			adicionaLinhas(pessoa);
		
		painelTabela.add(barraRolagem);
		containerListaDados.add(painelTabela);
		containerListaDados.add(botaoConfirmar);
	}
	
	public void listarDadosOrdenados (Populacao populacao, String nomeProcurado) {
		// Atributos
		int qtd;
		
		// Configuracoes proprias JFrame
		setTitle("Listar Pessoa[s]");
		setSize(680, 390);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerListaDados = getContentPane();
		containerListaDados.setLayout(new FlowLayout(1, 1000, 20));
		
		// Conficuracoes dos Componentes
		criaTabela();
		criaBotoes(populacao);
		
		qtd = adicionaLinhas(populacao.getPopulacao(), nomeProcurado);
		etiq = new JLabel(qtd + " Pessoa(s) encontrada(s) pelo nome [" + nomeProcurado + "] no sistema.", JLabel.CENTER);
		
		painelTabela.add(barraRolagem);
		containerListaDados.add(painelTabela);
		containerListaDados.add(etiq);
		containerListaDados.add(botaoConfirmar);
	}

	private void criaBotoes (Populacao populacao) {
		botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setPreferredSize(new Dimension(95, 30));
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new Menu().menu(populacao);
				dispose();
			}
		});
	}
	
	private void criaTabela () {
		painelTabela = new JPanel();
		tabelaConteudos = new JTable(modelo);
		tabelaConteudos.setDefaultRenderer(Object.class, renderizarCelulas);
		tabelaConteudos.setPreferredScrollableViewportSize(new Dimension(640, 200));
		renderizarCelulas.setHorizontalAlignment(JLabel.CENTER);
		barraRolagem = new JScrollPane(tabelaConteudos);
		modelo.addColumn("Codigo");
		modelo.addColumn("Nome");
		modelo.addColumn("Genero");
		modelo.addColumn("Idade");
		modelo.addColumn("Estado de Saude");
		modelo.setNumRows(0);
	}
	
	private void adicionaLinhas (Pessoa pessoa) {
		String [] atributos = pessoa.toString().split("/");
		modelo.addRow(new Object[]{
			atributos[2],
			atributos[0],
			atributos[1],
			atributos[3],
			atributos[4]
		});
	}
	
	private int adicionaLinhas (List<Pessoa> populacao, String nomeProcurado) {
		int qtdPessoas = 0;
		List<Pessoa> populacaoOrdenada = new ArrayList<Pessoa>(populacao);
		Collections.sort(populacaoOrdenada, new OrdenaNome());
		for (Pessoa pessoa : populacao) {
			if (pessoa.getNomeCompleto().toLowerCase().contains(nomeProcurado.toLowerCase())) {
				qtdPessoas++;
				adicionaLinhas(pessoa);
			}
		}
		return qtdPessoas;
	}
}
