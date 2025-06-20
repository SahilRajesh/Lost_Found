package projectMain;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class statusPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private mainPage parentFrame;

    public statusPanel(mainPage parent) {
        this.parentFrame = parent;
        initializePanel();
    }
    

    public void initializePanel() {
        setLayout(new BorderLayout(0, 0));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(64, 0, 64));
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Top panel for header and buttons
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(64, 0, 64));
        topPanel.setLayout(new BorderLayout());

        JLabel headerLbl = new JLabel("Status of Item/s");
        headerLbl.setForeground(Color.WHITE);
        headerLbl.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));
        headerLbl.setBackground(new Color(255, 0, 128));
        headerLbl.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(headerLbl, BorderLayout.CENTER);

        // Button panel for Go Back and Refresh
        JButton backBtn = new JButton("Go Back");
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.showDashboardPanel();
            }
        });
        backBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        backBtn.setBackground(Color.CYAN);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        refreshBtn.setBackground(Color.GREEN);
        refreshBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadTableData();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(64, 0, 64));
        buttonPanel.add(backBtn);
        buttonPanel.add(refreshBtn);

        topPanel.add(buttonPanel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Table and scroll pane
        table = new JTable();
        table.setEnabled(false);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Item no", "Category", "Status"
            }
        ) {
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

        JScrollPane tableScrPane = new JScrollPane(table);
        mainPanel.add(tableScrPane, BorderLayout.CENTER);

        // Load data from database into the table
        if (parentFrame != null && parentFrame.connectDB() != null) {
            loadTableData();
        } else {
            System.err.println("Database connection is not available.");
        }
    }

    // Method to load data from the database into the JTable
    private void loadTableData() {
        String sql = "SELECT id, category FROM item_complaint";

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        try (Connection conn = parentFrame.connectDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String category = rs.getString("category");
                String status = "Pending"; // Placeholder, update if you have a status column
                
                model.addRow(new Object[]{id, category, status});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
