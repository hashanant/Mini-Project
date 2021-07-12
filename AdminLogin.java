// package tec181;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField unField;
	private JPasswordField pwdField;

	public AdminLogin() {
		Connection conn = Database.dbConnect();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel loginTitle = new JLabel("Admin Login");
		loginTitle.setBounds(0, 11, 334, 80);
		layeredPane.add(loginTitle);
		loginTitle.setFont(new Font("Simplex", Font.BOLD, 30));
		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		unField = new JTextField();
		unField.setBounds(171, 102, 110, 20);
		layeredPane.add(unField);
		unField.setColumns(10);
		
		JLabel userName = new JLabel("Username");
		userName.setHorizontalAlignment(SwingConstants.LEFT);
		userName.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		userName.setBounds(62, 102, 99, 25);
		layeredPane.add(userName);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(171, 132, 110, 20);
		layeredPane.add(pwdField);
		
		JLabel pwd = new JLabel("Password");
		pwd.setHorizontalAlignment(SwingConstants.LEFT);
		pwd.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));		
		pwd.setBounds(62, 128, 99, 25);
		layeredPane.add(pwd);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement pst = conn.prepareStatement("SELECT * FROM `admin` WHERE userName=? AND pwd=?");
					pst.setString(1, unField.getText());
					pst.setString(2, String.valueOf(pwdField.getPassword()));
					
					ResultSet rs = pst.executeQuery();
					
					if(unField.getText().length() == 0 || String.valueOf(pwdField.getPassword()).length() == 0) {
						JOptionPane.showMessageDialog(null, "Username or Password can not be empty.", "Username and Password is empty", JOptionPane.WARNING_MESSAGE);
					} else {						
						if(rs.next()) {
							unField.setText("");
							pwdField.setText("");
							JOptionPane.showMessageDialog(null, "Admin Login Successful.", "Admin Login", JOptionPane.PLAIN_MESSAGE);
							
							try {
								Admin frame = new Admin();
								frame.setVisible(true);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "You are not admin. Please try Student Login.", "Incorrect Username or Password", JOptionPane.ERROR_MESSAGE);
							unField.setText("");
							pwdField.setText("");
						}
					}
					
					rs.close();
					pst.close();
				} catch(SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		loginButton.setBounds(119, 188, 89, 23);
		loginButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		layeredPane.add(loginButton);
		
		JButton stdlButton = new JButton("Student Login");
		stdlButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		stdlButton.setBounds(89, 234, 149, 25);
		stdlButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		layeredPane.add(stdlButton);
	}

}
