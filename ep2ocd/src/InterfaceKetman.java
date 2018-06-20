import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;

public class InterfaceKetman extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		setBounds(300, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(40, 120, 367, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.setBounds(162, 227, 117, 25);
		contentPane.add(btnExecutar);
		
		JLabel lblNewLabel = new JLabel("Coloque o comando em Assembly um de cada vez");
		lblNewLabel.setBounds(40, 44, 367, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Aperte enter ao fim de cada entrada");
		lblNewLabel_1.setBounds(40, 68, 367, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Aperte executar para finalizar");
		lblNewLabel_2.setBounds(40, 95, 367, 15);
		contentPane.add(lblNewLabel_2);
	}
}
