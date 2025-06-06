package projectMain;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Toolkit;

public class mainPage {

    private JFrame mainFrame;
    private static final String LOGIN_UI = "Login";
    private static final String REGIS_UI = "Regis";
    private static final String HOME_UI = "HomePanel";
    private static final String DASHBOARD_UI = "Dashboard";
    private static final String NEW_COMPLAINT_UI = "NewComplaint";
    private static final String STATUS_UI = "StatusPanel";

    private CardLayout myCard;
    private loginUI logMe;
    private regisUI regMe;
    private dashboard dashMe;
    private newComplaint newCom;
    private statusPanel statusMe;
    
    private JPanel mainBasePan;

    public void showHomePanel() {
        myCard.show(mainBasePan, HOME_UI);
    }
    public void showRegistrationPanel() {
		myCard.show(mainBasePan, REGIS_UI);
	}
    public void showDashboardPanel() {
		myCard.show(mainBasePan, DASHBOARD_UI);
	}
    public void showLoginPanel() {
		myCard.show(mainBasePan, LOGIN_UI);
	}
    public void showNewComplaintPanel() {
    	myCard.show(mainBasePan, NEW_COMPLAINT_UI);
    }
    public void showStatusPanel() {
    	myCard.show(mainBasePan, STATUS_UI);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                mainPage window = new mainPage();
                window.mainFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public mainPage() {
        initialize();
    }

    public void initialize() {
        mainFrame = new JFrame();
        myCard = new CardLayout();
        mainFrame.setTitle("Simple Lost & Found GUI using Java Swing");
        mainFrame.setResizable(false);
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sahil Rajesh\\Downloads\\Lost.jpg"));
        mainFrame.setBounds(100, 100, 800, 529);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(new BorderLayout());

        // Main panel with CardLayout
        mainBasePan = new JPanel();
        mainBasePan.setLayout(myCard);

        // Home panel (now part of CardLayout)
        JPanel mainBodyPan = new JPanel();
        mainBodyPan.setBackground(new Color(0, 128, 128));
        mainBodyPan.setLayout(null);

        JTextPane txtpnWelcomeToLost = new JTextPane();
        txtpnWelcomeToLost.setText("Welcome to Lost and Found Portal");
        txtpnWelcomeToLost.setForeground(Color.YELLOW);
        txtpnWelcomeToLost.setFont(new Font("Lucida Handwriting", Font.BOLD, 20));
        txtpnWelcomeToLost.setEditable(false);
        txtpnWelcomeToLost.setBackground(new Color(255, 0, 128));
        txtpnWelcomeToLost.setBounds(221, 53, 347, 77);
        mainBodyPan.add(txtpnWelcomeToLost);

        JTextPane txtpnHereYouCan = new JTextPane();
        txtpnHereYouCan.setText("Here, you can report for missing items or found items");
        txtpnHereYouCan.setForeground(Color.YELLOW);
        txtpnHereYouCan.setFont(new Font("Lucida Handwriting", Font.BOLD, 20));
        txtpnHereYouCan.setEditable(false);
        txtpnHereYouCan.setBackground(new Color(255, 0, 128));
        txtpnHereYouCan.setBounds(209, 153, 370, 77);
        mainBodyPan.add(txtpnHereYouCan);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> myCard.show(mainBasePan, LOGIN_UI));
        btnLogin.setForeground(new Color(255, 0, 128));
        btnLogin.setFont(new Font("Rockwell", Font.BOLD, 25));
        btnLogin.setBackground(Color.BLACK);
        btnLogin.setBounds(327, 266, 124, 39);
        mainBodyPan.add(btnLogin);

        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(e -> myCard.show(mainBasePan, REGIS_UI));
        btnRegister.setForeground(new Color(255, 0, 128));
        btnRegister.setFont(new Font("Rockwell", Font.BOLD, 25));
        btnRegister.setBackground(Color.BLACK);
        btnRegister.setBounds(316, 324, 146, 39);
        mainBodyPan.add(btnRegister);

        // Initialize panels
        logMe = new loginUI(this);
        regMe = new regisUI(this);
        dashMe = new dashboard(this);
        newCom = new newComplaint(this);
        statusMe = new statusPanel(this);

        // Add panels to CardLayout
        mainBasePan.add(mainBodyPan, HOME_UI);
        mainBasePan.add(logMe, LOGIN_UI);
        mainBasePan.add(regMe, REGIS_UI);
        mainBasePan.add(dashMe, DASHBOARD_UI);
        mainBasePan.add(newCom, NEW_COMPLAINT_UI);
        mainBasePan.add(statusMe, STATUS_UI);

        mainFrame.getContentPane().add(mainBasePan, BorderLayout.CENTER);

        // Header
        JPanel mainHeaderPan = new JPanel();
        mainHeaderPan.setBackground(new Color(255, 0, 128));
        JLabel lblNewLabel = new JLabel("Lost and Found");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Jokerman", Font.BOLD, 40));
        mainHeaderPan.add(lblNewLabel);
        mainFrame.getContentPane().add(mainHeaderPan, BorderLayout.NORTH);

        // Footer
        JPanel mainFooterPan = new JPanel();
        mainFooterPan.setBackground(new Color(0, 0, 0));
        JLabel lblNewLabel_1 = new JLabel("All Rights Reserved. NHCE Project 2025 ©");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        mainFooterPan.add(lblNewLabel_1);
        mainFrame.getContentPane().add(mainFooterPan, BorderLayout.SOUTH);

        // Show home panel by default
        myCard.show(mainBasePan, HOME_UI);
    }
}
