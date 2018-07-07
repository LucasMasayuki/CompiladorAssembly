package ep2ocd;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JTable;

public class InterfaceKetman extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    private Memoria memoria = new Memoria();
    private Comandos comandosAssembly = new Comandos();
    private Uc uc = new Uc();
	private ArrayList<String> comandos = new ArrayList<String>();
	private JTextPane txtpnA;
	private static InterfaceKetman frame;
	private Firmware firmware= new Firmware();
	private JTable table;
	private JTable memTable;
	private int atual = 0;
	private int theend = 0;
	private boolean busca = true;
	private DefaultTableModel model;
	private int indicaFim = 0;
	private int end = 0;

	/**
	 * Create the frame.
	 */
	public InterfaceKetman() {
		setTitle("Compilador assembly");
		setName("Compilador Assembly");
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

		textField.setBounds(87, 396, 424, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(
					"<html><center>Bem vindo usuario ao compilador Assembly.</center> <br><br> "
					+ "<center> 1 - Comece colocando um comando por vez, apertando enter ao final de cada comando</center><br><br>"
					+ "<center>Voce vera no painel ao lado cada comando que voce digitou. </center><br><br> "
					+ "<center> 2 - Ao finalizar de inserir todos os comandos que voce deseja,</center><br><br>"
					+ "<center>Aperte o botao executar abaixo para que os comandos sejam executados</center></html>"
				);
		lblNewLabel.setBounds(87, 97, 424, 225);
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
		txtpnA.setSize(new Dimension(2000, 2000));
		txtpnA.setMaximumSize(new Dimension(1000, 1000));
		txtpnA.setEditable(false);
		txtpnA.setText("Nao possui comandos");
		txtpnA.setBounds(622, 38, 656, 615);
		contentPane.add(txtpnA);
	}
	
	private void atualizaComandos(String text) {
		txtpnA.setText(text);
	}
	
	private void microperacoes() {
		end = memoria.getLinha();
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

		JLabel lblNewLabel = new JLabel("<html><center>Vá apertando enter no campo ao lado.</center> <br><br>" 
			+ "<center>Veja na tabela os valores mudarem</center><br><br>"
			+ "<center><b>Atencao tempos iguais acontecem ao mesmo tempo.</center><br>"
			+ "<center><b>Quando for t1 sera relacionado com um ciclo novo</center><br></html>"
		);

		lblNewLabel.setBounds(87, 97, 424, 225);
		contentPane.add(lblNewLabel);
		JLabel lbl2 = new JLabel();
		lbl2.setBounds(12, 67, 412, 283);
		contentPane.add(lbl2);
		
		JLabel lblregister = new JLabel("<html><center>Registradores</center> <br><br></html>" 
			);

		lblregister.setBounds(150, 280, 240, 200);
		contentPane.add(lblregister);
		table = new JTable(model);
		contentPane.add(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setEnabled(false);
		table.setBounds(70, 380, 240, 200);
		
		Object[][] mem = memoria.getEstadoDaMemoria();
		
		String[] column = {"endereco", "dados"};
		
		DefaultTableModel modelo = new DefaultTableModel(mem, column);
		
		JLabel lblmemory = new JLabel("<html><center>Memoria</center> <br><br></html>" 
				);
		lblmemory.setBounds(450, 280, 150, 200);
		contentPane.add(lblmemory);
		
		memTable = new JTable(modelo);
		contentPane.add(memTable);
		memTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		memTable.setEnabled(false);
		memTable.setBounds(400, 380, 150, 200);
		
		txtpnA = new JTextPane();

		txtpnA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (indicaFim < end) {
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
								atual++;
							}
						} else {
							int indice = 0;
							indice = Integer.parseInt(uc.getIr().getOpcode(), 2);
		
							theend = firmware.tamanhoSinal(indice);
							
							if (theend != atual) {
								Object resposta[][] = uc.cicloDeExecucao(firmware, indice, atual, memoria);
		
								reset();
								String[] coluna = {"Componente", "valor"};
								model = new DefaultTableModel(resposta, coluna);
								table.setModel(model);
		
								Object[][] mem = memoria.getEstadoDaMemoria();
								String[] column = {"endereco", "dados"};

								DefaultTableModel modelo = new DefaultTableModel(mem, column);
								memTable.setModel(modelo);
								atual++;
							} else {
								atual = 0;
								busca = true;
								indicaFim++;
							}
						}
					} else {
						JFrame warning = new JFrame();
						warning.setSize(300, 300);
				        warning.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				        warning.setVisible(true);
				        contentPane = new JPanel();
						contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
						warning.setContentPane(contentPane);
						contentPane.setLayout(null);
						JLabel lblNewLabel = new JLabel("<html><center>Acabou o programa, reinicie para comecar de novo.</center></html>"
							);
						lblNewLabel.setBounds(100, 70, 70, 100);
						contentPane.add(lblNewLabel);
					}
					e.consume();
				} else {
					e.consume();
				}
			}
		});

		txtpnA.setBounds(622, 38, 656, 615);
		txtpnA.setMaximumSize(new Dimension(1000, 1000));
		contentPane.add(txtpnA);
		
		StringBuilder builder = new StringBuilder();
		StringBuilder textBusca;
		StringBuilder textExec;
		
		Uc temp = new Uc();
		int agora = 0;

		Firmware newfirmware = new Firmware();
		while (end != agora) {
			textBusca = new StringBuilder();
			textBusca = temp.cicloDeBuscaParaMostrarNaTela(newfirmware, memoria);
			builder.append(textBusca);
			builder.append(" \n ");

			textExec = new StringBuilder();
			textExec = temp.cicloDeExecucaoParaMostrarNaTela(newfirmware, memoria);

			builder.append(textExec);
			builder.append(" \n ");
			agora++;
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
