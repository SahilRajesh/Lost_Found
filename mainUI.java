package projectMain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JTextPane;

public class mainUI {

	private JFrame frmSimpleLostAnd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainUI window = new mainUI();
					window.frmSimpleLostAnd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSimpleLostAnd = new JFrame();
		frmSimpleLostAnd.setResizable(false);
		frmSimpleLostAnd.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sahil Rajesh\\Downloads\\Lost.jpg"));
		frmSimpleLostAnd.setTitle("Simple Lost and Found GUI using Java Swing");
		frmSimpleLostAnd.setBounds(100, 100, 800, 500);
		frmSimpleLostAnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSimpleLostAnd.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 786, 74);
		panel.setBackground(new Color(128, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Lost and Found");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Jokerman", Font.BOLD, 42));
		panel.add(lblNewLabel);
		frmSimpleLostAnd.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(217, 0, 108));
		panel_1.setBounds(0, 74, 786, 198);
		frmSimpleLostAnd.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane txtpnWelcomeToLost = new JTextPane();
		txtpnWelcomeToLost.setFont(new Font("Lucida Handwriting", Font.BOLD, 20));
		txtpnWelcomeToLost.setForeground(new Color(255, 255, 0));
		txtpnWelcomeToLost.setBackground(new Color(255, 0, 128));
		txtpnWelcomeToLost.setEditable(false);
		txtpnWelcomeToLost.setText("Welcome to Lost and Found Portal");
		txtpnWelcomeToLost.setBounds(207, 22, 347, 77);
		panel_1.add(txtpnWelcomeToLost);
		
		JTextPane txtpnHereYouCan = new JTextPane();
		txtpnHereYouCan.setText("Here, you can report for missing items or found items");
		txtpnHereYouCan.setForeground(Color.YELLOW);
		txtpnHereYouCan.setFont(new Font("Lucida Handwriting", Font.BOLD, 20));
		txtpnHereYouCan.setEditable(false);
		txtpnHereYouCan.setBackground(new Color(255, 0, 128));
		txtpnHereYouCan.setBounds(195, 110, 370, 77);
		panel_1.add(txtpnHereYouCan);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(0, 272, 786, 191);
		frmSimpleLostAnd.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(328, 31, 124, 39);
		panel_2.add(btnLogin);
		btnLogin.setForeground(new Color(255, 0, 128));
		btnLogin.setFont(new Font("Rockwell", Font.BOLD, 25));
		btnLogin.setBackground(Color.BLACK);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(318, 99, 146, 39);
		panel_2.add(btnRegister);
		btnRegister.setBackground(new Color(0, 0, 0));
		btnRegister.setForeground(new Color(255, 0, 128));
		btnRegister.setFont(new Font("Rockwell", Font.BOLD, 25));
	}
}
