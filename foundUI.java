package projectMain;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class foundUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private mainPage parentFrame;
    private JComboBox<String> categoryComBx;
    private JComboBox<String> itemNoCombo;

    public foundUI(mainPage parent) {
        this.parentFrame = parent;
        initializePanel();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        // Reload categories
        categoryComBx.removeAllItems();
        categoryComBx.addItem("Select a category");
        loadComboData();
        // Reload item numbers
        itemNoCombo.removeAllItems();
        itemNoCombo.addItem("Select the item no");
        loadItemNumbers(itemNoCombo);
    }

    public void initializePanel() {
        setLayout(new BorderLayout(0, 0));

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(14, 228, 169));
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(12, 182, 135));
        centerPanel.setBounds(100, 50, 575, 300);
        mainPanel.add(centerPanel);
        centerPanel.setLayout(null);

        JLabel headerLbl = new JLabel("Found an item ?");
        headerLbl.setForeground(new Color(255, 239, 38));
        headerLbl.setFont(new Font("Lucida Handwriting", Font.BOLD, 30));
        headerLbl.setBackground(new Color(255, 0, 128));
        headerLbl.setBounds(128, 11, 308, 51);
        centerPanel.add(headerLbl);

        JLabel categoryLbl = new JLabel("Item Category");
        categoryLbl.setForeground(Color.WHITE);
        categoryLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        categoryLbl.setBounds(22, 72, 141, 36);
        centerPanel.add(categoryLbl);

        categoryComBx = new JComboBox<>();
        categoryComBx.setForeground(Color.WHITE);
        categoryComBx.setFont(new Font("Times New Roman", Font.BOLD, 20));
        categoryComBx.setBackground(new Color(128, 0, 128));
        categoryComBx.setBounds(174, 73, 251, 44);
        centerPanel.add(categoryComBx);

        categoryComBx.addItem("Select a category");

        JLabel itemNoLbl = new JLabel("Item no");
        itemNoLbl.setForeground(Color.WHITE);
        itemNoLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        itemNoLbl.setBounds(45, 149, 79, 36);
        centerPanel.add(itemNoLbl);

        itemNoCombo = new JComboBox<>();
        itemNoCombo.setForeground(new Color(255, 255, 255));
        itemNoCombo.setBackground(new Color(255, 128, 0));
        itemNoCombo.setFont(new Font("Times New Roman", Font.BOLD, 20));
        itemNoCombo.setBounds(174, 154, 183, 27);
        centerPanel.add(itemNoCombo);

        itemNoCombo.addItem("Select the item no");

        JButton submitBtn = new JButton("Found");
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String category = (String) categoryComBx.getSelectedItem();
                String itemNo = (String) itemNoCombo.getSelectedItem();

                if ("Select a category".equals(category) || "Select the item no".equals(itemNo)) {
                    JOptionPane.showMessageDialog(foundUI.this, "Please select both Category and Item No.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String insertSql = "INSERT INTO item_history (id, category, isFound) VALUES (?, ?, TRUE) ON DUPLICATE KEY UPDATE isFound=TRUE";
                String deleteSql = "DELETE FROM item_complaint WHERE id = ?";
                try (Connection conn = parentFrame.connectDB();
                     PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                     PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {

                    // Insert into item_history
                    insertStmt.setString(1, itemNo);
                    insertStmt.setString(2, category);
                    int rowsUpdated = insertStmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        // Delete from item_complaint
                        deleteStmt.setString(1, itemNo);
                        deleteStmt.executeUpdate();

      				  	JOptionPane.showMessageDialog(foundUI.this, "Item Marked as found.", "Success", JOptionPane.INFORMATION_MESSAGE);	
      				  	
						// Clear the combo boxes
						categoryComBx.setSelectedIndex(0);
						itemNoCombo.setSelectedIndex(0);
						// Refresh the item numbers
						itemNoCombo.removeAllItems();
						itemNoCombo.addItem("Select the item no");
						loadComboData();
						loadItemNumbers(itemNoCombo);
						
						// Optionally, refresh the dashboard or UI
						parentFrame.showItemAllPanel();
                    } else {
                        JOptionPane.showMessageDialog(foundUI.this, "Failed to update item status.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(foundUI.this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("Times New Roman", Font.BOLD, 25));
        submitBtn.setBackground(new Color(64, 0, 64));
        submitBtn.setBounds(201, 223, 132, 44);
        centerPanel.add(submitBtn);

        JButton backBtn = new JButton("Go Back");
        backBtn.setForeground(new Color(255, 255, 255));
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.showDashboardPanel();
            }
        });
        backBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        backBtn.setBackground(new Color(255, 0, 0));
        backBtn.setBounds(645, 11, 145, 31);
        mainPanel.add(backBtn);
        
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        refreshBtn.setBackground(new Color(0, 255, 255));
        refreshBtn.setBounds(490, 11, 145, 31);
        refreshBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNotify();
            }
        });
        mainPanel.add(refreshBtn);

        loadComboData();
        loadItemNumbers(itemNoCombo);
    }

    private void loadComboData() {
        String sql = "SELECT DISTINCT category FROM item_complaint";
        try (Connection conn = parentFrame.connectDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String category = rs.getString("category");
                categoryComBx.addItem(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadItemNumbers(JComboBox<String> comboBox) {
        String sql = "SELECT id FROM item_complaint";
        try (Connection conn = parentFrame.connectDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                comboBox.addItem(String.valueOf(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
