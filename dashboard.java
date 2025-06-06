package projectMain;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dashboard extends JPanel {

	private static final long serialVersionUID = 1L;
	private final JLabel lblNewLabel = new JLabel("Dashboard");
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 0, 64));
		add(panel, BorderLayout.NORTH);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Jokerman", Font.BOLD, 30));
		panel.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(17, 170, 174));
		add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(null);
		
		JButton btnNewButton = new JButton("New Complaint");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentFrame.showNewComplaintPanel();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBounds(284, 40, 208, 60);
		panel_3.add(btnNewButton);
		
		JButton btnViewStatus = new JButton("View Status");
		btnViewStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentFrame.showStatusPanel();
			}
		});
		btnViewStatus.setForeground(Color.WHITE);
		btnViewStatus.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnViewStatus.setBackground(new Color(0, 0, 255));
		btnViewStatus.setBounds(284, 111, 208, 60);
		panel_3.add(btnViewStatus);
		
		JButton btnViewAllComplaints = new JButton("View all complaints");
		btnViewAllComplaints.setForeground(Color.WHITE);
		btnViewAllComplaints.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnViewAllComplaints.setBackground(new Color(255, 156, 21));
		btnViewAllComplaints.setBounds(284, 183, 208, 60);
		panel_3.add(btnViewAllComplaints);
		
		JButton btnItemfound = new JButton("ItemFound?");
		btnItemfound.setForeground(Color.WHITE);
		btnItemfound.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnItemfound.setBackground(new Color(0, 185, 0));
		btnItemfound.setBounds(284, 254, 208, 60);
		panel_3.add(btnItemfound);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentFrame.showHomePanel();
			}
		});
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnLogout.setBackground(new Color(134, 0, 255));
		btnLogout.setBounds(10, 11, 142, 45);
		panel_3.add(btnLogout);

	}
}
