package projectMain;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JCheckBox;
import javax.swing.DefaultCellEditor;

public class adminStatus extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private mainPage parentFrame;

    public adminStatus(mainPage parent) {
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
                parentFrame.showAdminPanel();
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
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Item no", "Category", "Description", "Status", "Action"
            }
        ) {
            private static final long serialVersionUID = 1L;
            Class[] columnTypes = new Class[] {
                Object.class, String.class,String.class, String.class, String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            boolean[] columnEditables = new boolean[] {
                false, false, false,false, true
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });

        // Set custom renderer and editor for the "Action" column
        table.getColumn("Action").setCellRenderer(new ButtonRenderer());
        table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), table));

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
        String sql = "SELECT * FROM item_complaint";

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        try (Connection conn = parentFrame.connectDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String category = rs.getString("category");
                String complaint = rs.getString("complaint");
                String status = "Pending"; // Placeholder, update if you have a status column
                String action = "Mark as Found";
                model.addRow(new Object[]{id, category, complaint, status, action});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Custom renderer for the button
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Mark as Found" : value.toString());
            return this;
        }
    }

    // Custom editor for the button
    
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private int selectedRow;
        JTable table;
        boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;
            button = new JButton("Mark as Found");
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int modelRow = table.convertRowIndexToModel(selectedRow);
                    int id = (int) table.getModel().getValueAt(modelRow, 0);
                    String category = (String) table.getModel().getValueAt(modelRow, 1);
                    String complaint = (String) table.getModel().getValueAt(modelRow, 2);
                    boolean isFound = true;

                    // Insert into another table (e.g., found_items)
                    try (Connection conn = parentFrame.connectDB();
                    		 PreparedStatement pstmt = conn.prepareStatement(
                                     "INSERT INTO item_history (id, category,complaint, isFound) VALUES (?, ?, ?, ?)")) {
                                pstmt.setInt(1, id);
                                pstmt.setString(2, category);
                                pstmt.setString(3, complaint);
                                pstmt.setBoolean(4, isFound); // This will insert 1/0 as required
                                pstmt.executeUpdate();

                        // Delete from original table
                        String deleteSql = "DELETE FROM item_complaint WHERE id = " + id;
                        pstmt.executeUpdate(deleteSql);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    // Remove from table UI
                    ((DefaultTableModel) table.getModel()).removeRow(modelRow);
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            selectedRow = row;
            button.setText((value == null) ? "Mark as Found" : value.toString());
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            isPushed = false;
            return "Mark as Found";
        }
    }

}
