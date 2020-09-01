package interfaceGrafica;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import dados.Planta;

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
	
	public void listarDados (List<Planta> listaPlanta) {
		// Configuracoes proprias JFrame
		setTitle("Listar Plantas");
		setSize(500, 350);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerListaDados = getContentPane();
		containerListaDados.setLayout(new FlowLayout(1, 20, 20));
			
		// Conficuracoes dos Componentes
		criaTabela();
		criaBotoes();
		adicionaLinhas(listaPlanta);
		
		painelTabela.add(barraRolagem);
		containerListaDados.add(painelTabela);
		containerListaDados.add(botaoConfirmar);
	}
	
	public void listarDadosOrdenados (List<Planta> listaPlanta, String nomeProcurado) {
		// Atributos
		int qtd;
		
		// Configuracoes proprias JFrame
		setTitle("Listar Plantas");
		setSize(500, 390);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerListaDados = getContentPane();
		containerListaDados.setLayout(new FlowLayout(1, 1000, 20));
		
		// Conficuracoes dos Componentes
		criaTabela();
		criaBotoes();
		
		qtd = adicionaLinhas(listaPlanta, nomeProcurado);
		etiq = new JLabel(qtd + " Planta(s) encontrada(s) pelo nome [" + nomeProcurado + "] no sistema.", JLabel.CENTER);
		
		painelTabela.add(barraRolagem);
		containerListaDados.add(painelTabela);
		containerListaDados.add(etiq);
		containerListaDados.add(botaoConfirmar);
	}

	private void criaBotoes () {
		botaoConfirmar = new JButton("Confirmar");
		botaoConfirmar.setPreferredSize(new Dimension(95, 30));
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new Menu().menu();
				dispose();
			}
		});
	}
	
	private void criaTabela () {
		painelTabela = new JPanel();
		
		tabelaConteudos = new JTable(modelo);
		tabelaConteudos.setDefaultRenderer(Object.class, renderizarCelulas);
		tabelaConteudos.setPreferredScrollableViewportSize(new Dimension(440, 200));
		
		renderizarCelulas.setHorizontalAlignment(JLabel.CENTER);
		
		barraRolagem = new JScrollPane(tabelaConteudos);
		
		modelo.addColumn("Nome");
		modelo.addColumn("Codigo");
		modelo.addColumn("Peso Medio (KG)");
		modelo.setNumRows(0);
	}
	
	private int adicionaLinhas (List<Planta> listaPlanta) {
		int qtdPLantas = 0;
		for (Planta planta : listaPlanta) {
			qtdPLantas++;
			modelo.addRow(new Object[]{
				planta.getNome().toUpperCase(), 
				planta.getCodigo(), 
				new DecimalFormat("0.0000").format(planta.getPesoMedio())
			});
		}
		return qtdPLantas;
	}
	
	private int adicionaLinhas (List<Planta> listaPlanta, String nomePlanta) {
		int qtdPLantas = 0;
		for (Planta planta : listaPlanta) {
			if (planta.getNome().toLowerCase().contains(nomePlanta.toLowerCase())) {
				qtdPLantas++;
				modelo.addRow(new Object[]{
					planta.getNome().toUpperCase(), 
					planta.getCodigo(), 
					new DecimalFormat("0.0000").format(planta.getPesoMedio())
				});
			}
		}
		return qtdPLantas;
	}
}
