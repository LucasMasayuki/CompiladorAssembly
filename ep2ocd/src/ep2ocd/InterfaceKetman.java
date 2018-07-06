package ep2ocd;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Frame;

public class InterfaceKetman extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    private Memoria memoria = new Memoria();
    private Comandos comandosAssembly = new Comandos();
    private Uc uc = new Uc();
	private ArrayList<String> comandos = new ArrayList<String>();
	private JTextPane txtpnA;
	private static InterfaceKetman frame;
	private Firmware firmware;
	private JTable table;
	private boolean first = true;
	private int atual = 0;
	private int theend = 0;
	private boolean busca = true;
	private DefaultTableModel model;

	/**
	 * Create the frame.
	 */
	public InterfaceKetman() {
		setName("Compilador Assembly");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String comando = textField.getText();
					textField.setText("");

					comandos.add(comando);

					StringBuilder text = new StringBuilder();
					for (String input : comandos) {
						text.append(input);
						text.append('\n');
					}

					atualizaComandos(text.toString());
					InterpretadorAssembly.compila(comando, memoria, uc, comandosAssembly);
				  }
			}
		});

		textField.setBounds(87, 432, 424, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(
					"<html><center>Bem vindo usuario ao compilador Assembly.</center> <br><br> "
					+ "<center>Comece colocando um comando por vez, apertando enter ao final de cada comando</center><br><br>"
					+ "<center>voce vera no painel ao lado cada comando que voce digitou. </center><br><br> "
					+ "<center>Ao finalizar de colocar todos os comandos que voce deseja,</center><br><br>"
					+ "<center>aperte o botao executar abaixo para que os comandos sejam executados</center></html>"
				);
		lblNewLabel.setBounds(12, 99, 592, 225);
		contentPane.add(lblNewLabel);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.setBounds(238, 516, 117, 23);
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 frame.getContentPane().removeAll();
				 frame.repaint();
				 microperacoes();
			}
		});
		btnExecutar.setActionCommand("OK");
		contentPane.add(btnExecutar);
		txtpnA = new JTextPane();
		txtpnA.setEditable(false);
		txtpnA.setText("Nao possui comandos");
		txtpnA.setBounds(622, 38, 890, 798);
		contentPane.add(txtpnA);
	}
	
	private void atualizaComandos(String text) {
		txtpnA.setText(text);
	}
	
	private void microperacoes() {
		int end = memoria.getLinha();
		Object[][] dados = {
			{"pc" , 0},
			{"ir" , "0"},
			{"mbr" , "0"},
			{"mar" , 0},
			{"ax" , "0"},
			{"bx" , "0"},
			{"cx" , "0"},
			{"dx" , "0"},
			{"x" , 0},
			{"y" , 0},
			{"flagE" , 0},
			{"flagNe" , 0},
			{"flagG" , 0},
			{"flagGe" , 0},
			{"flagL" , 0},
			{"flagLe" , 0}
		};
		
		String[] coluna = {"Componente", "valor"};

		model = new DefaultTableModel(dados, coluna);

		JLabel lblNewLabel = new JLabel("<html><center>Aperte enter a cada linha.</center> <br><br>" 
			+ "<center>Veja na tabela os valores mudarem</center><br><br>"
			+ "<center><b>Atencao nao pule as linhas.</b></center><br>"
			+ "<center><b>Atencao tempos iguais acontecem ao mesmo tempo,<br> tome cuidado para nao apertar duas vezes em dois tempos iguais.</b></center><br>"
			+ "<center><b>Atencao voce precisara utilizar o mouse para ir pra proxima linha.</b></center><br></html>"
		);

		lblNewLabel.setBounds(93, 222, 250, 150);
		contentPane.add(lblNewLabel);
		JLabel lbl2 = new JLabel();
		lbl2.setBounds(12, 67, 412, 283);
		contentPane.add(lbl2);

		table = new JTable(model);
		contentPane.add(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(319);
		table.getColumnModel().getColumn(1).setPreferredWidth(228);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setEnabled(false);
		table.setBounds(28, 450, 424, 160);
		
		txtpnA = new JTextPane();

		txtpnA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (busca) {
						if (atual > 3) {
							busca = false;
							atual = 0;
						} else {
							Object resposta[][] = uc.cicloDeBusca(firmware, memoria, atual);
							reset();
							String[] coluna = {"Componente", "valor"};
							model = new DefaultTableModel(resposta, coluna);
							table.setModel(model);
						}
					} else {
						int indice = 0;
						indice = Integer.parseInt(uc.getIr().getOpcode(), 2);
						System.out.println(indice);

						theend = firmware.tamanhoSinal(indice);
						
						if (theend != atual) {
							Object resposta[][] = uc.cicloDeExecucao(firmware, indice, atual);

							reset();
							String[] coluna = {"Componente", "valor"};
							model = new DefaultTableModel(resposta, coluna);
							table.setModel(model);
						} else {
							atual = 0;
							busca = true;
						}
					}

					atual++;
					e.consume();
				} else {
					e.consume();
				}
			}
		});

		txtpnA.setBounds(622, 38, 890, 798);
		contentPane.add(txtpnA);
		
		StringBuilder builder = new StringBuilder();
		StringBuilder textBusca;
		StringBuilder textExec;

		for (int i = 0; i < end; i++) {
			firmware = new Firmware();
			textBusca = new StringBuilder();
			textBusca = uc.cicloDeBuscaParaMostrarNaTela(firmware, memoria);
			builder.append(textBusca);
			Palavra palavra = (Palavra)memoria.getProcesso(i).dados;

			int indice = Integer.parseInt(palavra.getOpcode(), 2);

			textExec = new StringBuilder();
			textExec = uc.cicloDeExecucaoParaMostrarNaTela(firmware, indice, memoria);

			builder.append(textExec);
		}

		txtpnA.setText(builder.toString());
	}
	
	public void reset() {
		  model.setRowCount(0);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new InterfaceKetman();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
