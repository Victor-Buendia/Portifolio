package interfaceGrafica;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import dados.Contaminado;
import dados.Populacao;
import dados.Saudavel;
import validacao.Validacao;

public class RegistraDados extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Atributos
	private Container containerEntradaDados;
	private JTextField entradaNome, entradaIdade;
//	private JTextArea erroNome, erroGenero, erroIdentificacao, erroEstadoSaude, erroIdade;
	private JPanel painelTextos, painelBotoesRadio, painelBotoes;
	private JLabel etiqNome, etiqGenero, etiqIdade;
	private JButton botaoConfirmar, botaoCancelar;
	private JRadioButton masculino, feminino, emTratamento, falecido, curado;
	private ButtonGroup grupoBotoes, grupoBotoesSituacaoSaude;
	
	public RegistraDados () {
		// Configuracoes gerais JFrame
		setVisible(true);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void entradaContaminado (Populacao populacao) {
		// Configuracoes JFrame
		setTitle("Registro de Contaminado");
		setSize(520, 230);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerEntradaDados = getContentPane();
		containerEntradaDados.setLayout(new FlowLayout(1, 20, 20));
		
		// Conficuracoes dos Componentes
		criaPainelDados();
		criaBotaoGenero();
		criaBotaoConfirmarCancelar(populacao);
		containerEntradaDados.add(painelTextos);
		containerEntradaDados.add(painelBotoesRadio);
		containerEntradaDados.add(criaSituacaoSaude());
		containerEntradaDados.add(painelBotoes);
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				acaoBotaoConfirmarContaminado(evt, populacao);
			}
		});
	}
	
	public void entradaSaudavel (Populacao populacao) {
		// Configuracoes JFrame
		setTitle("Registro de Saudavel");
		setSize(520, 230);
		setLocationRelativeTo(null);
		
		// Configuracoes do Container
		containerEntradaDados = getContentPane();
		containerEntradaDados.setLayout(new FlowLayout(1, 20, 20));
		
		// Conficuracoes dos Componentes
		criaPainelDados();
		criaBotaoGenero();
		criaBotaoConfirmarCancelar(populacao);
		containerEntradaDados.add(painelTextos);
		containerEntradaDados.add(painelBotoesRadio);
		containerEntradaDados.add(criaEntradaIdade());
		containerEntradaDados.add(painelBotoes);
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				acaoBotaoConfirmarSaudavel(evt, populacao);
			}
		});
	}
	
	private void criaPainelDados () {
		painelTextos = new JPanel(new GridLayout(1, 2, 10, 20));
		
		etiqNome = new JLabel("Digite o Nome da Pessoa: ");
		entradaNome = new JTextField();
		painelTextos.add(etiqNome);
		painelTextos.add(entradaNome);
	}
	
	private void criaBotaoGenero () {
		painelBotoesRadio = new JPanel(new FlowLayout(1, 30, 0));
		
		etiqGenero = new JLabel("Escolha o Genero: ");
		painelBotoesRadio.add(etiqGenero);
		
		grupoBotoes = new ButtonGroup();
		masculino = new JRadioButton("Masculino");
		feminino = new JRadioButton("Feminino");
		
		grupoBotoes.add(masculino);
		grupoBotoes.add(feminino);
		painelBotoesRadio.add(masculino);
		painelBotoesRadio.add(feminino);
	}
	
	private JPanel criaSituacaoSaude () {
		JPanel painelSituacaoSaude = new JPanel(new FlowLayout(1, 30, 0));
		
		JLabel situacaoSaude = new JLabel("Escolha a Situacao: ");
		painelSituacaoSaude.add(situacaoSaude);
		
		grupoBotoesSituacaoSaude = new ButtonGroup();
		emTratamento = new JRadioButton("Em Tratamento");
		falecido = new JRadioButton("Falecido");
		curado = new JRadioButton("Curado");
		
		grupoBotoesSituacaoSaude.add(emTratamento);
		grupoBotoesSituacaoSaude.add(falecido);
		grupoBotoesSituacaoSaude.add(curado);
		painelSituacaoSaude.add(emTratamento);
		painelSituacaoSaude.add(falecido);
		painelSituacaoSaude.add(curado);
		return painelSituacaoSaude;
	}

	private JPanel criaEntradaIdade () {
		JPanel painelIdade = new JPanel(new GridLayout(1, 2, 10, 20));
		
		etiqIdade = new JLabel("Digite a Idade da Pessoa: ");
		entradaIdade = new JTextField();
		painelIdade.add(etiqIdade);
		painelIdade.add(entradaIdade);
		return painelIdade;
	}

	private void criaBotaoConfirmarCancelar (Populacao populacao) {
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
	
	private void acaoBotaoConfirmarContaminado (ActionEvent evt, Populacao populacao) {
		String erro = "A[s] seguinte[s] entrada[s] possui[em] erro: ";
		if (!Validacao.isNomeValido(entradaNome.getText().trim(), 3)) {
			entradaNome.setText("");
			erro += "\n- Nome deve possuir mais que 3 caracteres e sem digitos numericos.";
		}
		if (!masculino.isSelected() && !feminino.isSelected()) {
			erro += "\n- Nenhum genero foi selecionado.";
		}
		if (!emTratamento.isSelected() && !falecido.isSelected() && !curado.isSelected()) {
			erro += "\n- Nenhum estado de saude foi selecionado.";
		}
		
		if (erro.contains("\n")) {
			new MostrarTexto().mostraMensagem("Erro no Registro", erro);
		}
		else {
			populacao.setPopulacao(new Contaminado(
				entradaNome.getText().trim(), 
				(masculino.isSelected() ? 'M' : 'F'), 
				populacao.getPopulacao().size() + 1, 
				(emTratamento.isSelected() ? 'E' : falecido.isSelected() ? 'F' : 'C')
			));
			new Menu().menu(populacao);
			dispose();
		}
	}
	
	private void acaoBotaoConfirmarSaudavel (ActionEvent evt, Populacao populacao) {
		String erro = "A[s] seguinte[s] entrada[s] possui[em] erro: ";
		if (!Validacao.isNomeValido(entradaNome.getText().trim(), 3)) {
			entradaNome.setText("");
			erro += "\n- Nome deve possuir mais que 3 caracteres e sem digitos numericos.";
		}
		if (!masculino.isSelected() && !feminino.isSelected()) {
			erro += "\n- Nenhum genero foi selecionado.";
		}
		if (!Validacao.isIdadeValida(entradaIdade.getText().trim())) {
			entradaIdade.setText("");
			erro += "\n- A entrada numerica deve estar entre 0 (bebe nao completou 1 ano) e ate no maximo 130 anos.";
		}
		
		if (erro.contains("\n")) {
			new MostrarTexto().mostraMensagem("Erro no Registro", erro);
		}
		else {
			populacao.setPopulacao(new Saudavel(
				entradaNome.getText().trim(), 
				(masculino.isSelected() ? 'M' : 'F'), 
				populacao.getPopulacao().size() + 1, 
				Integer.parseInt(entradaIdade.getText().trim())
			));
			new Menu().menu(populacao);
			dispose();
		}
	}
}
