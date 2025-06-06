package projectMain;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class statusPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	
	private mainPage parentFrame;
	public statusPanel(mainPage parent) {
		this.parentFrame = parent;
		initializePanel();
	}

	/**
	 * Create the panel.
	 */
	public void initializePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 0, 64));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Item no", "Category", "Status", "Action"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(136, 127, 471, 185);
		panel.add(table);
		
		JLabel lblStatusOfItems = new JLabel("Status of Item/s");
		lblStatusOfItems.setForeground(Color.WHITE);
		lblStatusOfItems.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));
		lblStatusOfItems.setBackground(new Color(255, 0, 128));
		lblStatusOfItems.setBounds(236, 39, 304, 51);
		panel.add(lblStatusOfItems);
		
		JButton btnNewButton_1 = new JButton("Go Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentFrame.showDashboardPanel();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setBounds(645, 11, 145, 31);
		panel.add(btnNewButton_1);

	}
}
