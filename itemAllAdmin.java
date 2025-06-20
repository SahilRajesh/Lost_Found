package projectMain;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class itemAllAdmin extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private mainPage parentFrame;

    public itemAllAdmin(mainPage parent) {
        this.parentFrame = parent;
        initializePanel();
    }
    
    public void initializePanel() {
        setBackground(new Color(128, 0, 255));
        setLayout(null);

        // Header label
        JLabel headerLbl = new JLabel("History");
        headerLbl.setForeground(Color.WHITE);
        headerLbl.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));
        headerLbl.setBackground(new Color(255, 0, 128));
        headerLbl.setBounds(303, 26, 149, 51);
        add(headerLbl);

        // Button panel with right alignment
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(500, 11, 300, 40);

        JButton backBtn = new JButton("Go Back");
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.showAdminPanel();
            }
        });
        backBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        backBtn.setBackground(new Color(0, 255, 255));
        buttonPanel.add(backBtn);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        refreshBtn.setBackground(Color.GREEN);
        refreshBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadTableData();
            }
        });
        buttonPanel.add(refreshBtn);

        add(buttonPanel);

        // Table and scroll pane
        table = new JTable();
        table.setEnabled(false);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Item no", "Item Category", "Is Found"
            }
        ) {
            Class[] columnTypes = new Class[] {
                Integer.class, String.class, Boolean.class
            };
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            boolean[] columnEditables = new boolean[] {
                false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(85);
        table.getColumnModel().getColumn(2).setResizable(false);

        JScrollPane tableScrPane = new JScrollPane(table);
        tableScrPane.setBounds(136, 127, 471, 185);
        add(tableScrPane);

        // Load data from database into the table
        if (parentFrame != null && parentFrame.connectDB() != null) {
            loadTableData();
        } else {
            System.err.println("Database connection is not available.");
        }
    }

    private void loadTableData() {
        String sql = "SELECT * FROM item_history";
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        try (Connection conn = parentFrame.connectDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String category = rs.getString("category");
                Boolean status = rs.getBoolean("isFound");
                model.addRow(new Object[]{id, category, status});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
