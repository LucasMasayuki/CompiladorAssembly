package ep2ocd;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class InterfaceKetman extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    private Memoria memoria = new Memoria();
    private Comandos comandosAssembly = new Comandos();
    private Uc uc = new Uc();
	private ArrayList<String> comandos = new ArrayList<String>();
	JTextPane txtpnA;
	
	private void atualizaComandos(String text) {
		txtpnA.setText(text);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceKetman frame = new InterfaceKetman();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceKetman() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		textField.setBounds(97, 394, 424, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(
					"<html><center>Bem vindo usuario ao compilador Assembly.</center> <br><br> "
					+ "<center>Comece colocando um comando por vez, apertando enter ao final de cada comando</center><br><br>"
					+ "<center>voce vera no painel ao lado cada comando que voce digitou. </center><br><br> "
					+ "<center>Ao finalizar de colocar todos os comandos que voce deseja,</center><br><br>"
					+ "<center>aperte o botao executar abaixo para que os comandos sejam executados</center></html>"
				);
		lblNewLabel.setBounds(12, 131, 412, 283);
		contentPane.add(lblNewLabel);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.setBounds(218, 487, 117, 23);
		btnExecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExecutar.setActionCommand("OK");
		contentPane.add(btnExecutar);
		txtpnA = new JTextPane();
		txtpnA.setEditable(false);
		txtpnA.setText("Nao possui comandos");
		txtpnA.setBounds(622, 38, 890, 798);
		contentPane.add(txtpnA);}
}
