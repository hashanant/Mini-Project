// package tec181;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to Department Event Portal");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(111, 11, 609, 137);
		contentPane.add(lblNewLabel);
		
		JButton signUpButton = new JButton("New user?");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Registration frame = new Registration();
					frame.setVisible(true);
					
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		signUpButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		signUpButton.setBounds(177, 256, 109, 33);
		contentPane.add(signUpButton);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
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
		loginButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		loginButton.setBounds(361, 256, 109, 33);
		contentPane.add(loginButton);
		
		JButton adminLoginButton = new JButton("Admin Login");
		adminLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdminLogin frame = new AdminLogin();
					frame.setVisible(true);
					
					dispose();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		adminLoginButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		adminLoginButton.setBounds(548, 256, 123, 33);
		contentPane.add(adminLoginButton);
	}

}
