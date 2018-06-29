package ep2ocd;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class InterfaceKetman extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    private Memoria memoria = new Memoria();
    private Comandos comandosAssembly = new Comandos();
    private Uc uc = new Uc();
	private ArrayList<String> comandos = new ArrayList<String>();

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
					textField.setText("");

					comandos.add(comando);

					StringBuilder text = new StringBuilder();
					for (String input : comandos) {
						text.append(input);
						text.append('\n');
					}
					ListaDeComandos secondFrame = new ListaDeComandos(text.toString());
					secondFrame.setVisible(true);
					InterpretadorAssembly.compila(comando, memoria, uc, comandosAssembly);
				  }
			}
		});
		textField.setBounds(49, 136, 350, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Coloque um comando de cada vez");
		lblNewLabel.setBounds(49, 38, 350, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("Ao final de cada comando aperte enter");
		lblNewLabel1.setBounds(49, 59, 350, 14);
		contentPane.add(lblNewLabel1);
		
		JLabel lblNewLabel11 = new JLabel("Aperte Executar");
		lblNewLabel11.setBounds(49, 84, 350, 14);
		contentPane.add(lblNewLabel11);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.setBounds(148, 196, 117, 23);
		contentPane.add(btnExecutar);
	}
}
