package projectMain;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;



public class loginUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private mainPage parentFrame;
    private JTextField nameFld;
    private JTextField usnFld;
    

    public loginUI(mainPage parent) {
        this.parentFrame = parent;
        initializePanel();
    }

    private void initializePanel() {
        setBackground(new Color(255, 51, 0));
        setLayout(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 0, 0));
        headerPanel.setBounds(0, 0, 800, 57);
        add(headerPanel);
        headerPanel.setLayout(null);

        JLabel headerLbl = new JLabel("Login window");
        headerLbl.setForeground(Color.WHITE);
        headerLbl.setFont(new Font("Jokerman", Font.BOLD, 32));
        headerLbl.setBounds(266, 0, 246, 57);
        headerPanel.add(headerLbl);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(255, 153, 0));
        centerPanel.setBounds(181, 110, 372, 228);
        add(centerPanel);
        centerPanel.setLayout(null);

        MyRound loginBtn = new MyRound("Login");
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameFld.getText();
                String usn = usnFld.getText();
                if (name.isEmpty() || usn.isEmpty()) {
                    JOptionPane.showMessageDialog(loginUI.this, "Please enter both Name and USN.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String role = parentFrame.authenticateUser(name, usn);
                if ("admin".equals(role)) {
                	JOptionPane.showMessageDialog(loginUI.this, "Admin Login Successful! Welcome "+name, "Success", JOptionPane.INFORMATION_MESSAGE);
                	nameFld.setText("");
                	usnFld.setText("");
                    parentFrame.showAdminPanel();
                } else if ("user".equals(role)) {
                	JOptionPane.showMessageDialog(loginUI.this, "User Login Successful! Welcome "+name, "Success", JOptionPane.INFORMATION_MESSAGE);
                	nameFld.setText("");
                	usnFld.setText("");
                    parentFrame.showDashboardPanel();
                } else {
                	JOptionPane.showMessageDialog(loginUI.this, "Invalid Name or USN.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
//            
            }

        });
        loginBtn.setBackground(new Color(0, 255, 0));
        loginBtn.setFont(new Font("Times New Roman", Font.BOLD, 25));
        loginBtn.setBounds(152, 137, 117, 39);
        centerPanel.add(loginBtn);

        JLabel nameLbl = new JLabel("Name");
        nameLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameLbl.setBounds(23, 32, 67, 24);
        centerPanel.add(nameLbl);

        nameFld = new JTextField();
        nameFld.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameFld.setToolTipText("Enter your name");
        nameFld.setColumns(10);
        nameFld.setBounds(125, 29, 176, 35);
        centerPanel.add(nameFld);

        JLabel usnLbl = new JLabel("USN");
        usnLbl.setFont(new Font("Times New Roman", Font.BOLD, 20));
        usnLbl.setBounds(31, 78, 83, 24);
        centerPanel.add(usnLbl);

        usnFld = new JTextField();
        usnFld.setFont(new Font("Times New Roman", Font.BOLD, 20));
        usnFld.setToolTipText("Enter your USN");
        usnFld.setColumns(10);
        usnFld.setBounds(124, 75, 177, 35);
        centerPanel.add(usnFld);
        
        MyRound homeBtn = new MyRound("Go back home");
        homeBtn.setBounds(125, 186, 160, 31);
        centerPanel.add(homeBtn);
        homeBtn.setBackground(new Color(0, 255, 255));
        homeBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        homeBtn.addActionListener(e -> parentFrame.showHomePanel());

        MyRound regisBtn = new MyRound("Not Registered ?");
        regisBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.showRegistrationPanel();
            }
        });
        regisBtn.setFont(new Font("Times New Roman", Font.BOLD, 15));
        regisBtn.setBackground(Color.CYAN);
        regisBtn.setBounds(613, 59, 177, 31);
        add(regisBtn);
    }

   
}
