package projectMain;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class newComplaint extends JPanel {

	private static final long serialVersionUID = 1L;
	private mainPage parentFrame;
	private JComboBox<String> categoryComboBx;
	
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
		
		JLabel categoryLbl = new JLabel("Item Category");
		categoryLbl.setForeground(new Color(255, 255, 255));
		categoryLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
		categoryLbl.setBounds(73, 103, 141, 36);
		add(categoryLbl);
		
		JLabel descriptionLbl = new JLabel("Description");
		descriptionLbl.setForeground(Color.WHITE);
		descriptionLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
		descriptionLbl.setBounds(92, 200, 112, 36);
		add(descriptionLbl);
		
		JLabel locationLbl = new JLabel("Last seen location");
		locationLbl.setForeground(Color.WHITE);
		locationLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
		locationLbl.setBounds(73, 276, 168, 36);
		add(locationLbl);
		
		JLabel uniqueLbl = new JLabel("Unique mark, if any");
		uniqueLbl.setForeground(Color.WHITE);
		uniqueLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
		uniqueLbl.setBounds(537, 276, 183, 36);
		add(uniqueLbl);
		
		JLabel headingLbl = new JLabel("Enter Lost item Details");
		headingLbl.setBackground(new Color(255, 0, 128));
		headingLbl.setForeground(Color.WHITE);
		headingLbl.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));
		headingLbl.setBounds(192, 22, 440, 51);
		add(headingLbl);
		
		categoryComboBx = new JComboBox<>();
		categoryComboBx.setForeground(new Color(255, 255, 255));
		categoryComboBx.setBackground(new Color(128, 0, 128));
		categoryComboBx.setFont(new Font("Times New Roman", Font.BOLD, 20));
		categoryComboBx.setModel(new DefaultComboBoxModel<String>(new String[] {"Select a category","Electronics", "Stationery", "Backpacks, waterbottles", "Clothing", "Accessories", "Others"}));
		categoryComboBx.setBounds(248, 99, 251, 44);
		add(categoryComboBx);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.setForeground(new Color(255, 255, 255));
		submitBtn.setFont(new Font("Times New Roman", Font.BOLD, 25));
		submitBtn.setBackground(new Color(64, 0, 64));
		submitBtn.setBounds(328, 323, 132, 44);
		add(submitBtn);
		
		JButton backBtn = new JButton("Go Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentFrame.showDashboardPanel();
			}
		});
		backBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		backBtn.setBackground(Color.CYAN);
		backBtn.setBounds(655, 11, 145, 31);
		add(backBtn);
		
		JScrollPane descriptionPane = new JScrollPane();
		descriptionPane.setBounds(233, 172, 303, 93);
		add(descriptionPane);
		
		JTextArea descriptionTar = new JTextArea();
		descriptionTar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		descriptionTar.setRows(3);
		descriptionTar.setLineWrap(true);
		descriptionPane.setViewportView(descriptionTar);
		
		JScrollPane locationPane = new JScrollPane();
		locationPane.setBounds(10, 309, 284, 65);
		add(locationPane);
		
		JTextArea locationTar = new JTextArea();
		locationPane.setViewportView(locationTar);
		locationTar.setRows(3);
		locationTar.setLineWrap(true);
		locationTar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JScrollPane uniquePane = new JScrollPane();
		uniquePane.setBounds(485, 309, 284, 65);
		add(uniquePane);
		
		JTextArea uniqueTar = new JTextArea();
		uniqueTar.setRows(3);
		uniqueTar.setLineWrap(true);
		uniqueTar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		uniquePane.setViewportView(uniqueTar);
		
		submitBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String category = (String) categoryComboBx.getSelectedItem();
		        String description = descriptionTar.getText();
		        String lastSeenLocation = locationTar.getText();
		        String uniqueMark = uniqueTar.getText();
		        
		        if (categoryComboBx.getSelectedIndex() == 0 || description.isEmpty() || lastSeenLocation.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please fill in all required fields.","Insufficient Details",JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        // Database connection details
		        

		        String sql = "INSERT INTO item_complaint (category, complaint, last_seen_location, unique_mark) VALUES (?, ?, ?, ?)";

		        try (Connection conn = parentFrame.connectDB();
		             PreparedStatement stmt = conn.prepareStatement(sql)) {

		            stmt.setString(1, category);
		            stmt.setString(2, description);
		            stmt.setString(3, lastSeenLocation);
		            stmt.setString(4, uniqueMark);

		            int rowsInserted = stmt.executeUpdate();
		            if (rowsInserted > 0) {
		                JOptionPane.showMessageDialog(null, "Complaint submitted successfully!");
		                descriptionTar.setText("");
		                locationTar.setText("");
		                uniqueTar.setText("");
		                categoryComboBx.setSelectedIndex(0);
		                
		                parentFrame.showStatusPanel();
		            } else {
		                JOptionPane.showMessageDialog(null, "Failed to submit complaint.");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
		        }
		    }
		});

	}
}
