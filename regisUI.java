package projectMain;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class regisUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private mainPage parentFrame;
    private JTextField nameFld;
    private JTextField phoneFld;
    private JTextField usnFld;
    private JComboBox<String> branchCbox;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;

    public regisUI(mainPage parent) {
        this.parentFrame = parent;
        initializePanel();
    }

    private void initializePanel() {
        setBackground(new Color(255, 51, 0));
        setLayout(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(128, 0, 64));
        headerPanel.setBounds(0, 0, 800, 58);
        add(headerPanel);
        headerPanel.setLayout(null);

        JLabel headerLbl = new JLabel("Registration");
        headerLbl.setForeground(Color.WHITE);
        headerLbl.setFont(new Font("Jokerman", Font.BOLD, 30));
        headerLbl.setBounds(270, 0, 218, 58);
        headerPanel.add(headerLbl);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBackground(new Color(255, 153, 0));
        centerPanel.setBounds(189, 80, 379, 300);
        add(centerPanel);

        JLabel nameLbl = new JLabel("Name");
        nameLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameLbl.setBounds(27, 26, 66, 24);
        centerPanel.add(nameLbl);

        nameFld = new JTextField();
        nameFld.setToolTipText("Enter your name");
        nameFld.setColumns(10);
        nameFld.setBounds(126, 28, 168, 24);
        centerPanel.add(nameFld);

        JLabel phoneLbl = new JLabel("Phone No");
        phoneLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        phoneLbl.setBounds(27, 174, 83, 24);
        centerPanel.add(phoneLbl);

        phoneFld = new JTextField();
        phoneFld.setToolTipText("Enter phone no");
        phoneFld.setColumns(10);
        phoneFld.setBounds(140, 174, 168, 24);
        centerPanel.add(phoneFld);

        JButton regisBtn = new JButton("Register");
        regisBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameFld.getText();
                String phone = phoneFld.getText();
                String usn = usnFld.getText();
                String branch = (String) branchCbox.getSelectedItem();
                String gender = maleRadio.isSelected() ? "Male" : (femaleRadio.isSelected() ? "Female" : "");

                // Simple validation
                if (name.isEmpty() || phone.isEmpty() || usn.isEmpty() || gender.isEmpty()) {
                    JOptionPane.showMessageDialog(regisUI.this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Insert into database
                Connection conn = parentFrame.connectDB();
                String sql = "INSERT INTO users (name, phone, usn, branch, gender) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, name);
                    stmt.setString(2, phone);
                    stmt.setString(3, usn);
                    stmt.setString(4, branch);
                    stmt.setString(5, gender);
                    int rows = stmt.executeUpdate();
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(regisUI.this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        parentFrame.showLoginPanel();
                    } else {
                        JOptionPane.showMessageDialog(regisUI.this, "Registration failed.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(regisUI.this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        regisBtn.setForeground(new Color(255, 255, 255));
        regisBtn.setBackground(new Color(0, 0, 128));
        regisBtn.setFont(new Font("Times New Roman", Font.BOLD, 25));
        regisBtn.setBounds(126, 235, 145, 39);
        centerPanel.add(regisBtn);

        JLabel branchLbl = new JLabel("Branch");
        branchLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        branchLbl.setBounds(27, 61, 61, 24);
        centerPanel.add(branchLbl);

        branchCbox = new JComboBox<>();
        branchCbox.setModel(new DefaultComboBoxModel<>(new String[] {
            "CSE", "ISE", "ECE", "MBA", "MTECH", "MCA", "Other"
        }));
        branchCbox.setToolTipText("Select your branch");
        branchCbox.setSelectedIndex(0);
        branchCbox.setFont(new Font("Times New Roman", Font.BOLD, 20));
        branchCbox.setEditable(true);
        branchCbox.setBounds(124, 61, 184, 30);
        centerPanel.add(branchCbox);

        JLabel usnLbl = new JLabel("USN");
        usnLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        usnLbl.setBounds(27, 104, 41, 24);
        centerPanel.add(usnLbl);

        JLabel genderLbl = new JLabel("Gender");
        genderLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        genderLbl.setBounds(27, 139, 63, 24);
        centerPanel.add(genderLbl);

        maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(142, 142, 61, 23);
        centerPanel.add(maleRadio);

        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(210, 142, 75, 23);
        centerPanel.add(femaleRadio);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        usnFld = new JTextField();
        usnFld.setToolTipText("Enter your USN");
        usnFld.setColumns(10);
        usnFld.setBounds(126, 108, 168, 24);
        centerPanel.add(usnFld);

        JButton homeBtn = new JButton("Go back home");
        homeBtn.setBounds(655, 69, 145, 31);
        add(homeBtn);
        homeBtn.setBackground(new Color(0, 255, 255));
        homeBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));

        homeBtn.addActionListener(e -> parentFrame.showHomePanel());
    }
}
