// package tec181;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField fNameField;
	private JTextField lNameField;
	private JTextField unField;
	private JPasswordField pwdField;

	public Registration() {
		Connection conn = Database.dbConnect();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel signUpTitle = new JLabel("Create New Account");
		signUpTitle.setBounds(0, 11, 429, 80);
		layeredPane.add(signUpTitle);
		signUpTitle.setFont(new Font("Simplex", Font.BOLD, 30));
		signUpTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		fNameField = new JTextField();
		fNameField.setBounds(221, 102, 114, 20);
		layeredPane.add(fNameField);
		fNameField.setColumns(10);
		
		JLabel fName = new JLabel("First Name");
		fName.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		fName.setBounds(60, 102, 86, 25);
		layeredPane.add(fName);			
		
		lNameField = new JTextField();
		lNameField.setBounds(221, 133, 114, 20);
		layeredPane.add(lNameField);
		lNameField.setColumns(10);
		
		JLabel lName = new JLabel("Last Name");
		lName.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		lName.setBounds(60, 139, 86, 14);
		layeredPane.add(lName);
		
		unField = new JTextField();
		unField.setBounds(220, 164, 115, 20);
		layeredPane.add(unField);
		unField.setColumns(10);
		
		JLabel userName = new JLabel("Username");
		userName.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		userName.setBounds(60, 170, 86, 14);
		layeredPane.add(userName);
		
		pwdField = new JPasswordField();
		pwdField.setBounds(221, 196, 114, 20);
		layeredPane.add(pwdField);
		
		JLabel pwd = new JLabel("Password");
		pwd.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
		pwd.setBounds(60, 202, 86, 14);
		layeredPane.add(pwd);
		
		JButton signUpButton = new JButton("Sign up");
		signUpButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				try {
					if(unField.getText().length() == 0 || String.valueOf(pwdField.getPassword()).length() == 0) {
						JOptionPane.showMessageDialog(null, "Username or Password can not be empty.", "Username and Password is empty", JOptionPane.WARNING_MESSAGE);
					} else {
						PreparedStatement pst = conn.prepareStatement("SELECT userName FROM `signup` WHERE userName=?");
						pst.setString(1, unField.getText());
						ResultSet rs = pst.executeQuery();
						
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, unField.getText() + " username already taken. Please try with different username.", "Username taken already", JOptionPane.WARNING_MESSAGE);
							unField.setText("");
						} else {
							pst = conn.prepareStatement("INSERT INTO `signup`(fName, lName, userName, pwd) VALUES(?, ?, ?, ?)");					
							pst.setString(1, fNameField.getText());
							pst.setString(2, lNameField.getText());
							pst.setString(3, unField.getText());
							pst.setString(4, String.valueOf(pwdField.getPassword()));
							pst.executeUpdate();
							
							fNameField.setText("");
							lNameField.setText("");
							unField.setText("");
							pwdField.setText("");
							
							JOptionPane.showMessageDialog(null, "Sing up Successful. Welcome to Student Portal", "Welcome to Portal", JOptionPane.INFORMATION_MESSAGE);
							try {
								Student frame = new Student();
								frame.setVisible(true);
								
								dispose();
							} catch (Exception e1) {
								e1.printStackTrace();
							}						
						}
						pst.close();
						rs.close();
					}
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		signUpButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		signUpButton.setBounds(162, 255, 89, 23);
		layeredPane.add(signUpButton);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dispose();	
			}
		});
		loginButton.setBounds(162, 305, 89, 23);
		loginButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		layeredPane.add(loginButton);
		
		JButton homeButton = new JButton("Home");
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		homeButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		homeButton.setBounds(162, 354, 89, 23);
		layeredPane.add(homeButton);
	}
}
