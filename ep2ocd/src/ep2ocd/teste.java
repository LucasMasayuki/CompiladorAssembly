package ep2ocd;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class teste extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teste frame = new teste();
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
	public teste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(
				"<html><center>Aperte enter a cada linha.</center> <br><br>"
				+ "<center>Veja na tabela os valores mudarem</center><br><br>\"\n" 
				+ "<center><b>Atencao nao pule as linhas.</b></center><br></html>");
		lblNewLabel.setBounds(93, 222, 250, 150);
		contentPane.add(lblNewLabel);
		String[][] dados = {
				{"pc" , "0"},
				{"ir" , "0"},
				{"mbr" , "0"},
				{"mar" , "0"},
				{"ax" , "0"},
				{"bx" , "0"},
				{"cx" , "0"},
				{"dx" , "0"},
				{"x" , "0"},
				{"y" , "0"},
			};
			
			String[] coluna = {"Componente", "valor"};
		
		table = new JTable(dados, coluna);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"pc", "0"},
				{"ir", "0"},
				{"mbr", "0"},
				{"mar", "0"},
				{"ax", "0"},
				{"bx", "0"},
				{"cx", "0"},
				{"dx", "0"},
				{"x", "0"},
				{"y", "0"},
			},
			new String[] {
				"Componente", "valor"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(319);
		table.getColumnModel().getColumn(1).setPreferredWidth(228);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setEnabled(false);
		table.setBounds(12, 497, 424, 160);
		contentPane.add(table);
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				}
			}
		});
		textField.setEditable(false);
		textField.setBounds(622, 38, 890, 798);
		contentPane.add(textField);
	}
}
