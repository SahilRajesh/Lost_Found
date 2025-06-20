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


public class dashboard extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JLabel headerLbl = new JLabel("Dashboard");
	/**
	 * @wbp.nonvisual location=157,-31
	 */
	
	private mainPage parentFrame;
	
	public dashboard(mainPage parent) {
        this.parentFrame = parent;
        
        initializePanel();
    }

	/**
	 * Create the panel.
	 */
	private void initializePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(128, 0, 64));
		add(headerPanel, BorderLayout.NORTH);
		headerLbl.setForeground(new Color(255, 255, 255));
		headerLbl.setFont(new Font("Jokerman", Font.BOLD, 30));
		headerPanel.add(headerLbl);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(17, 170, 174));
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(null);
		
		JButton newComplainBtn = new JButton("New Complaint");
		newComplainBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentFrame.showNewComplaintPanel();
			}
		});
		newComplainBtn.setForeground(new Color(255, 255, 255));
		newComplainBtn.setBackground(new Color(255, 0, 0));
		newComplainBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		newComplainBtn.setBounds(174, 154, 208, 60);
		centerPanel.add(newComplainBtn);
		
		JButton viewStatusBtn = new JButton("View Status");
		viewStatusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentFrame.showStatusPanel();
			}
		});
		viewStatusBtn.setForeground(Color.WHITE);
		viewStatusBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		viewStatusBtn.setBackground(new Color(0, 0, 255));
		viewStatusBtn.setBounds(416, 154, 208, 60);
		centerPanel.add(viewStatusBtn);
		
		JButton viewAllBtn = new JButton("View all complaints");
		viewAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentFrame.showItemAllPanel();
			}
		});
		viewAllBtn.setForeground(Color.WHITE);
		viewAllBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		viewAllBtn.setBackground(new Color(255, 156, 21));
		viewAllBtn.setBounds(174, 225, 208, 60);
		centerPanel.add(viewAllBtn);
		
		JButton itemFoundBtn = new JButton("ItemFound?");
		itemFoundBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentFrame.showFoundPanel();
			}
		});
		itemFoundBtn.setForeground(Color.WHITE);
		itemFoundBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		itemFoundBtn.setBackground(new Color(0, 185, 0));
		itemFoundBtn.setBounds(416, 225, 208, 60);
		centerPanel.add(itemFoundBtn);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(dashboard.this, "Logout Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
		welcomePane.setBackground(new Color(0, 128, 128));
		welcomePane.setEditable(false);
		welcomePane.setFont(new Font("Times New Roman", Font.BOLD, 35));
		welcomePane.setText("WELCOME BACK");
		welcomePane.setBounds(238, 76, 313, 48);
		centerPanel.add(welcomePane);
		
		
		
	}

	
}
