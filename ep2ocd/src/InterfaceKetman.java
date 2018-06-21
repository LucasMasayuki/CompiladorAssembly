import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Scrollbar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfaceKetman extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    private Memoria memoria = new Memoria();
    private Comandos comandosAssembly = new Comandos();
    private Uc uc = new Uc();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceKetman frame = new InterfaceKetman();
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
		setBounds(100, 100, 450, 300);
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
					InterpretadorAssembly interpreta = new InterpretadorAssembly();
					InterpretadorAssembly.compila(comando, memoria, uc, comandosAssembly);
				  }
			}
		});
		textField.setBounds(49, 136, 350, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Coloque um comando de cada vez");
		lblNewLabel.setBounds(49, 38, 172, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("Ao final de cada comando aperte enter");
		lblNewLabel1.setBounds(49, 59, 350, 14);
		contentPane.add(lblNewLabel1);
		
		JLabel lblNewLabel11 = new JLabel("Aperte Executar");
		lblNewLabel11.setBounds(49, 84, 350, 14);
		contentPane.add(lblNewLabel11);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.setBounds(176, 196, 89, 23);
		contentPane.add(btnExecutar);
	}
}
