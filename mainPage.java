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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mainPage {

    private JFrame mainFrame;
    private static final String LOGIN_UI = "Login";
    private static final String REGIS_UI = "Regis";
    private static final String HOME_UI = "HomePanel";
    private static final String DASHBOARD_UI = "Dashboard";
    private static final String NEW_COMPLAINT_UI = "NewComplaint";
    private static final String STATUS_UI = "StatusPanel";
    private static final String ITEM_ALL_UI = "ItemAll";
    private static final String FOUND_UI = "FoundPanel";
    private static final String ADMIN_UI = "AdminPanel";
    private static final String ADMIN_STATUS_UI = "AdminStatus";
    private static final String ADMIN_ITEM_UI = "AdminItem";

    private CardLayout myCard;
    private loginUI logMe;
    private regisUI regMe;
    private dashboard dashMe;
    private newComplaint newCom;
    private statusPanel statusMe;
    private itemAll itemAllMe;
    private foundUI foundMe; 
    private adminUI adminMe;
    private adminStatus adminStat;
    private itemAllAdmin itemAllAdminMe;
    
    private JPanel mainBasePan;

    // JDBC variables
    private Connection conn;
    private final String DB_URL = "jdbc:mysql://localhost:3306/projectLost";
    private final String DB_USER = "root";
    private final String DB_PASS = "1011";

    public void showHomePanel() {
        myCard.show(mainBasePan, HOME_UI);
    }
    public void showRegistrationPanel() {
        myCard.show(mainBasePan, REGIS_UI);
    }
    public void showLoginPanel() {
        myCard.show(mainBasePan, LOGIN_UI);
    }
    public void showDashboardPanel() {
        myCard.show(mainBasePan, DASHBOARD_UI);
    }
    
    public void showNewComplaintPanel() {
        myCard.show(mainBasePan, NEW_COMPLAINT_UI);
    }
    public void showStatusPanel() {
        myCard.show(mainBasePan, STATUS_UI);
    }
    public void showItemAllPanel() {
        myCard.show(mainBasePan, ITEM_ALL_UI);
    }
    public void showFoundPanel() {
        myCard.show(mainBasePan, FOUND_UI);
    }
    public void showAdminPanel() {
		myCard.show(mainBasePan, ADMIN_UI);
	}
    public void showAdminStatus() {
    	myCard.show(mainBasePan, ADMIN_STATUS_UI);    
    }
    public void showAdminItemAll() {
		myCard.show(mainBasePan, ADMIN_ITEM_UI);
	}

    // JDBC connect method
    public Connection connectDB() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return conn;
    }
    
    public String authenticateUser(String name, String usn) {
        String sql = "SELECT * FROM users WHERE BINARY name = ? AND BINARY usn = ?";
        String sql2 = "SELECT * FROM admins WHERE BINARY name = ? AND BINARY usn = ?";
        try (Connection conn = connectDB();
        		PreparedStatement userStmt = conn.prepareStatement(sql);
        				PreparedStatement adminStmt = conn.prepareStatement(sql2)) {
        	userStmt.setString(1, name);
        	userStmt.setString(2, usn);
            adminStmt.setString(1, name);
            adminStmt.setString(2, usn);
            try (ResultSet adminRs = adminStmt.executeQuery()) {
                if (adminRs.next()) {
                    return "admin";
                }
            }
            try (ResultSet userRs = userStmt.executeQuery()) {
                if (userRs.next()) {
                    return "user";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "none";
    }


    // JDBC close method
    public void closeMyDB() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        connectDB();
        initialize();
        // Optionally, add a shutdown hook to close DB on exit
        Runtime.getRuntime().addShutdownHook(new Thread(this::closeMyDB));
    }

    public void initialize() {
        mainFrame = new JFrame();
        myCard = new CardLayout();
        mainFrame.setTitle("Simple Lost & Found GUI using Java Swing");
        mainFrame.setResizable(false);
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(mainPage.class.getResource("/projectMain/Lost.jpg")));
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

        JTextPane firstTxtPane = new JTextPane();
        firstTxtPane.setText("Welcome to Lost and Found Portal");
        firstTxtPane.setForeground(Color.YELLOW);
        firstTxtPane.setFont(new Font("Lucida Handwriting", Font.BOLD, 20));
        firstTxtPane.setEditable(false);
        firstTxtPane.setBackground(new Color(255, 0, 128));
        firstTxtPane.setBounds(221, 53, 347, 77);
        mainBodyPan.add(firstTxtPane);

        JTextPane secondTxtPane = new JTextPane();
        secondTxtPane.setText("Here, you can report for missing items or found items");
        secondTxtPane.setForeground(Color.YELLOW);
        secondTxtPane.setFont(new Font("Lucida Handwriting", Font.BOLD, 20));
        secondTxtPane.setEditable(false);
        secondTxtPane.setBackground(new Color(255, 0, 128));
        secondTxtPane.setBounds(209, 153, 370, 77);
        mainBodyPan.add(secondTxtPane);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> myCard.show(mainBasePan, LOGIN_UI));
        loginBtn.setForeground(new Color(255, 0, 128));
        loginBtn.setFont(new Font("Rockwell", Font.BOLD, 25));
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setBounds(327, 266, 124, 39);
        mainBodyPan.add(loginBtn);

        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(e -> myCard.show(mainBasePan, REGIS_UI));
        registerBtn.setForeground(new Color(255, 0, 128));
        registerBtn.setFont(new Font("Rockwell", Font.BOLD, 25));
        registerBtn.setBackground(Color.BLACK);
        registerBtn.setBounds(316, 324, 146, 39);
        mainBodyPan.add(registerBtn);

        // Initialize panels
        logMe = new loginUI(this);
        regMe = new regisUI(this);
        dashMe = new dashboard(this);
        newCom = new newComplaint(this);
        statusMe = new statusPanel(this);
        itemAllMe = new itemAll(this);
        foundMe = new foundUI(this); 
        adminMe = new adminUI(this); 
        adminStat = new adminStatus(this);
        itemAllAdminMe = new itemAllAdmin(this);

        // Add panels to CardLayout
        mainBasePan.add(mainBodyPan, HOME_UI);
        mainBasePan.add(logMe, LOGIN_UI);
        mainBasePan.add(regMe, REGIS_UI);
        mainBasePan.add(dashMe, DASHBOARD_UI);
        mainBasePan.add(newCom, NEW_COMPLAINT_UI);
        mainBasePan.add(statusMe, STATUS_UI);
        mainBasePan.add(itemAllMe, ITEM_ALL_UI);
        mainBasePan.add(foundMe, FOUND_UI); 
        mainBasePan.add(adminMe, ADMIN_UI); 
        mainBasePan.add(adminStat, ADMIN_STATUS_UI);
        mainBasePan.add(itemAllAdminMe, ADMIN_ITEM_UI);

        mainFrame.getContentPane().add(mainBasePan, BorderLayout.CENTER);

        // Header
        JPanel mainHeaderPan = new JPanel();
        mainHeaderPan.setBackground(new Color(255, 0, 128));
        JLabel headerLbl = new JLabel("Lost and Found");
        headerLbl.setForeground(new Color(255, 255, 255));
        headerLbl.setFont(new Font("Jokerman", Font.BOLD, 40));
        mainHeaderPan.add(headerLbl);
        mainFrame.getContentPane().add(mainHeaderPan, BorderLayout.NORTH);

        // Footer
        JPanel mainFooterPan = new JPanel();
        mainFooterPan.setBackground(new Color(0, 0, 0));
        JLabel footerLbl = new JLabel("All Rights Reserved. NHCE Project 2025 ©");
        footerLbl.setForeground(new Color(255, 255, 255));
        footerLbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        mainFooterPan.add(footerLbl);
        mainFrame.getContentPane().add(mainFooterPan, BorderLayout.SOUTH);

        // Show home panel by default
        myCard.show(mainBasePan, HOME_UI);
    }
}
