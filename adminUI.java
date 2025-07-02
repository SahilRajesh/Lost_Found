package projectMain;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;


public class adminUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JLabel headerLbl = new JLabel("Admin Dashboard");
	/**
	 * @wbp.nonvisual location=157,-31
	 */
	
	private mainPage parentFrame;
	public adminUI(mainPage parent) {
        this.parentFrame = parent;
        
        initializePanel();
    }

	/**
	 * Create the panel.
	 */
	private void initializePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(255, 132, 9));
		add(headerPanel, BorderLayout.NORTH);
		headerLbl.setForeground(new Color(255, 255, 255));
		headerLbl.setFont(new Font("Jokerman", Font.BOLD, 30));
		headerPanel.add(headerLbl);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(64, 0, 64));
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(null);
		
		JButton viewStatusBtn = new JButton("View Status");
		viewStatusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentFrame.showAdminStatus();
			}
		});
//		viewStatusBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				parentFrame.showStatusPanel();
//			}
//		});
		viewStatusBtn.setForeground(Color.WHITE);
		viewStatusBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		viewStatusBtn.setBackground(new Color(0, 0, 255));
		viewStatusBtn.setBounds(173, 154, 208, 60);
		centerPanel.add(viewStatusBtn);
		
		JButton viewAllBtn = new JButton("View all complaints");
		viewAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentFrame.showAdminItemAll();
			}
		});
//		viewAllBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				parentFrame.showItemAllPanel();
//			}
//		});
		viewAllBtn.setForeground(Color.WHITE);
		viewAllBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		viewAllBtn.setBackground(new Color(255, 156, 21));
		viewAllBtn.setBounds(401, 154, 208, 60);
		centerPanel.add(viewAllBtn);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(adminUI.this, "Logout Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
				parentFrame.showHomePanel();
			}
		});
		logoutBtn.setForeground(Color.WHITE);
		logoutBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		logoutBtn.setBackground(new Color(134, 0, 255));
		logoutBtn.setBounds(10, 11, 142, 45);
		centerPanel.add(logoutBtn);
		
		JTextPane welcomePane = new JTextPane();
		welcomePane.setForeground(new Color(255, 255, 255));
		welcomePane.setBackground(new Color(0, 0, 255));
		welcomePane.setEditable(false);
		welcomePane.setFont(new Font("Times New Roman", Font.BOLD, 35));
		welcomePane.setText("WELCOME BACK");
		welcomePane.setBounds(242, 76, 316, 48);
		centerPanel.add(welcomePane);
		
		
		
	}

	
}

