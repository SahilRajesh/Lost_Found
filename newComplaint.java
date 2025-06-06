package projectMain;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class newComplaint extends JPanel {

	private static final long serialVersionUID = 1L;
	private mainPage parentFrame;
	
	public newComplaint(mainPage parent) {
		this.parentFrame = parent;
		initializePanel();
	}

	/**
	 * Create the panel.
	 */
	public void initializePanel() {
		setBackground(new Color(0, 128, 128));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item Category");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(73, 99, 141, 36);
		add(lblNewLabel);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDescription.setBounds(73, 157, 141, 36);
		add(lblDescription);
		
		JLabel lblLastSeenLocation = new JLabel("Last seen location");
		lblLastSeenLocation.setForeground(Color.WHITE);
		lblLastSeenLocation.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblLastSeenLocation.setBounds(73, 232, 168, 36);
		add(lblLastSeenLocation);
		
		JLabel lblUniqueMarkIf = new JLabel("Unique mark, if any");
		lblUniqueMarkIf.setForeground(Color.WHITE);
		lblUniqueMarkIf.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblUniqueMarkIf.setBounds(537, 232, 183, 36);
		add(lblUniqueMarkIf);
		
		JLabel lblEnterLostItem = new JLabel("Enter Lost item Details");
		lblEnterLostItem.setBackground(new Color(255, 0, 128));
		lblEnterLostItem.setForeground(Color.WHITE);
		lblEnterLostItem.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));
		lblEnterLostItem.setBounds(192, 22, 440, 51);
		add(lblEnterLostItem);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setBackground(new Color(128, 0, 128));
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Electronics", "Stationery", "Backpacks, waterbottles", "Clothing", "Accessories", "Others"}));
		comboBox.setBounds(288, 104, 233, 31);
		add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setRows(3);
		textArea.setLineWrap(true);
		textArea.setColumns(20);
		textArea.setTabSize(20);
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textArea.setBounds(288, 147, 210, 78);
		add(textArea);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		btnNewButton.setBackground(new Color(64, 0, 64));
		btnNewButton.setBounds(332, 292, 132, 44);
		add(btnNewButton);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setTabSize(20);
		textArea_1.setRows(3);
		textArea_1.setLineWrap(true);
		textArea_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textArea_1.setColumns(20);
		textArea_1.setBounds(45, 269, 210, 78);
		add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setTabSize(20);
		textArea_2.setRows(3);
		textArea_2.setLineWrap(true);
		textArea_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textArea_2.setColumns(20);
		textArea_2.setBounds(518, 269, 210, 78);
		add(textArea_2);
		
		JButton btnNewButton_1 = new JButton("Go Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentFrame.showDashboardPanel();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setBounds(655, 11, 145, 31);
		add(btnNewButton_1);

	}
}
