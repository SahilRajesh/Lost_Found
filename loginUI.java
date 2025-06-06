
package projectMain;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private mainPage parentFrame;
    private JTextField textField;
    private JTextField textField_1;

    public loginUI(mainPage parent) {
        this.parentFrame = parent;
        initializePanel();
    }

    private void initializePanel() {
        setBackground(new Color(255, 51, 0));
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 0, 64));
        panel.setBounds(0, 0, 800, 67);
        add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login window");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Jokerman", Font.BOLD, 32));
        lblNewLabel.setBounds(230, 0, 246, 64);
        panel.add(lblNewLabel);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBackground(new Color(255, 153, 0));
        panel_1_1.setBounds(155, 105, 372, 228);
        add(panel_1_1);
        panel_1_1.setLayout(null);
        
        JButton btnNewButton_2 = new JButton("Login");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		parentFrame.showDashboardPanel();
        	}
        });
        btnNewButton_2.setBackground(new Color(0, 255, 0));
        btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
        btnNewButton_2.setBounds(124, 137, 117, 39);
        panel_1_1.add(btnNewButton_2);
        
        JLabel lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_1.setBounds(10, 40, 113, 24);
        panel_1_1.add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setToolTipText("Enter your name");
        textField.setColumns(10);
        textField.setBounds(125, 44, 144, 20);
        panel_1_1.add(textField);
        
        JLabel lblNewLabel_1_1 = new JLabel("Phone No");
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel_1_1.setBounds(10, 87, 83, 24);
        panel_1_1.add(lblNewLabel_1_1);
        
        textField_1 = new JTextField();
        textField_1.setToolTipText("Enter phone no");
        textField_1.setColumns(10);
        textField_1.setBounds(124, 91, 145, 20);
        panel_1_1.add(textField_1);
        
        JButton btnNewButton_1 = new JButton("Go back home");
        btnNewButton_1.setBackground(new Color(0, 255, 255));
        btnNewButton_1.setBounds(109, 187, 160, 31);
        panel_1_1.add(btnNewButton_1);
        btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        
        JButton btnNewButton_1_1 = new JButton("Not Registered ?");
        btnNewButton_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        						// Show the registration panel in mainPage
				parentFrame.showRegistrationPanel();
        	}
        });
        btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnNewButton_1_1.setBackground(Color.CYAN);
        btnNewButton_1_1.setBounds(613, 78, 177, 31);
        add(btnNewButton_1_1);
        btnNewButton_1.addActionListener(e -> {
            // Show the home panel in mainPage
            parentFrame.showHomePanel();
		});
    }
}
