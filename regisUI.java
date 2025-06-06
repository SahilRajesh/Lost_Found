package projectMain;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class regisUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private mainPage parentFrame;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField;

    public regisUI(mainPage parent) {
        this.parentFrame = parent;
        initializePanel();
    }

    /**
     * Create the panel.
     */
    private void initializePanel() {
        setBackground(new Color(255, 51, 0));
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 0, 64));
        panel.setBounds(0, 0, 800, 58);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Registration");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Jokerman", Font.BOLD, 30));
        lblNewLabel.setBounds(270, 0, 218, 58);
        panel.add(lblNewLabel);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(new Color(255, 153, 0));
        panel_1_1.setBounds(189, 80, 379, 300);
        add(panel_1_1);

        JLabel lblNewLabel_1_4 = new JLabel("Name");
        lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_1_4.setBounds(27, 26, 66, 24);
        panel_1_1.add(lblNewLabel_1_4);

        textField_3 = new JTextField();
        textField_3.setToolTipText("Enter your name");
        textField_3.setColumns(10);
        textField_3.setBounds(126, 28, 168, 24);
        panel_1_1.add(textField_3);

        JLabel lblNewLabel_1_1_1 = new JLabel("Phone No");
        lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_1_1_1.setBounds(27, 174, 83, 24);
        panel_1_1.add(lblNewLabel_1_1_1);

        textField_4 = new JTextField();
        textField_4.setToolTipText("Enter phone no");
        textField_4.setColumns(10);
        textField_4.setBounds(140, 174, 168, 24);
        panel_1_1.add(textField_4);

        JButton btnNewButton = new JButton("Register");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		parentFrame.showLoginPanel();
        	}
        });
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(0, 0, 128));
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
        btnNewButton.setBounds(126, 235, 145, 39);
        panel_1_1.add(btnNewButton);

        JLabel lblNewLabel_1_2 = new JLabel("Branch");
        lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_1_2.setBounds(27, 61, 61, 24);
        panel_1_1.add(lblNewLabel_1_2);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "CSE", "ISE", "ECE", "EEE", "MECH", "CIVIL", "Other"
        }));
        comboBox.setToolTipText("Select your branch");
        comboBox.setSelectedIndex(0);
        comboBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
        comboBox.setEditable(true);
        comboBox.setBounds(124, 61, 184, 30);
        panel_1_1.add(comboBox);

        JLabel lblNewLabel_1_3 = new JLabel("USN");
        lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_1_3.setBounds(27, 104, 41, 24);
        panel_1_1.add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_3_1 = new JLabel("Gender");
        lblNewLabel_1_3_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_1_3_1.setBounds(27, 139, 63, 24);
        panel_1_1.add(lblNewLabel_1_3_1);

        JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
        rdbtnNewRadioButton.setBounds(142, 142, 61, 23);
        panel_1_1.add(rdbtnNewRadioButton);

        JRadioButton rdbtnFemale = new JRadioButton("Female");
        rdbtnFemale.setBounds(210, 142, 75, 23);
        panel_1_1.add(rdbtnFemale);

        // Add radio buttons to ButtonGroup
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rdbtnNewRadioButton);
        genderGroup.add(rdbtnFemale);

        textField = new JTextField();
        textField.setToolTipText("Enter your USN");
        textField.setColumns(10);
        textField.setBounds(126, 108, 168, 24);
        panel_1_1.add(textField);
        
                JButton btnNewButton_1 = new JButton("Go back home");
                btnNewButton_1.setBounds(655, 69, 145, 31);
                add(btnNewButton_1);
                btnNewButton_1.setBackground(new Color(0, 255, 255));
                btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));

        btnNewButton_1.addActionListener(e -> {
            // Show the home panel in mainPage
            parentFrame.showHomePanel();
        });
    }
}
