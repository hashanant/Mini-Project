// package tec181;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Student extends JFrame {

	static final int EVENTS = 3;
	
	private JPanel contentPane;

	public Student() {
		JTextArea[] idArea = new JTextArea[EVENTS];
		JTextArea[] titleArea = new JTextArea[EVENTS];
		JTextArea[] descriptionArea = new JTextArea[EVENTS];
		
		Connection conn = Database.dbConnect();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(5, 5, 835, 386);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
		
		JLabel ongEvent = new JLabel("Ongoing Events");
		ongEvent.setBounds(0, 11, 252, 53);
		layeredPane.add(ongEvent);
		ongEvent.setFont(new Font("Simplex", Font.BOLD, 25));
		ongEvent.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 56, 614, 319);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(597, 0, 17, 319);
		panel.add(scrollBar);
		
		idArea[1] = new JTextArea();
		idArea[1].setEditable(false);
		idArea[1].setFont(new Font("Monospaced", Font.BOLD, 20));
		idArea[1].setBounds(10, 11, 35, 32);
		panel.add(idArea[1]);
		
		titleArea[1] = new JTextArea();
		titleArea[1].setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		titleArea[1].setEditable(false);
		titleArea[1].setBounds(62, 11, 284, 32);
		panel.add(titleArea[1]);
		
		descriptionArea[1] = new JTextArea();
		descriptionArea[1].setEditable(false);
		descriptionArea[1].setBounds(10, 54, 413, 84);
		panel.add(descriptionArea[1]);
		
		JButton applyButton_1 = new JButton("Apply");
		applyButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					PreparedStatement pst = conn.prepareStatement("SELECT * FROM `applied` WHERE userName=? AND title=?");
					pst.setString(1, Login.login);
					pst.setString(2, titleArea[1].getText());
					if(titleArea[1].getText().length() == 0)  {
						JOptionPane.showMessageDialog(null, "There is no event! You can not apply.", "No Event", JOptionPane.ERROR_MESSAGE);
					} else {
						ResultSet rs = pst.executeQuery();
						
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "You already applied for this course.", "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
						} else {
							pst = conn.prepareStatement("INSERT INTO applied(userName, title) VALUES(?, ?)");
							pst.setString(1, Login.login);
							pst.setString(2, titleArea[1].getText());
							pst.executeUpdate();
							
							JOptionPane.showMessageDialog(null, Login.login + " sucessfully apllied for " + titleArea[1].getText(), "Conformation", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					pst.close();
				} catch(SQLException e1) {
					System.out.println(e1.getMessage());
				}				
			}
		});
		applyButton_1.setBounds(461, 55, 89, 23);
		panel.add(applyButton_1);
		
		idArea[2] = new JTextArea();
		idArea[2].setEditable(false);
		idArea[2].setFont(new Font("Monospaced", Font.BOLD, 20));
		idArea[2].setBounds(10, 149, 35, 32);
		panel.add(idArea[2]);
		
		titleArea[2] = new JTextArea();
		titleArea[2].setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		titleArea[2].setEditable(false);
		titleArea[2].setBounds(62, 149, 284, 32);
		panel.add(titleArea[2]);
		
		descriptionArea[2] = new JTextArea();
		descriptionArea[2].setEditable(false);
		descriptionArea[2].setBounds(10, 192, 413, 84);
		panel.add(descriptionArea[2]);
		
		JButton applyButton_2 = new JButton("Apply");
		applyButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					PreparedStatement pst = conn.prepareStatement("SELECT * FROM `applied` WHERE userName=? AND title=?");
					pst.setString(1, Login.login);
					pst.setString(2, titleArea[2].getText());
					if(titleArea[2].getText().length() == 0)  {
						JOptionPane.showMessageDialog(null, "There is no event! You can not apply.", "No Event", JOptionPane.ERROR_MESSAGE);
					} else {
						ResultSet rs = pst.executeQuery();
						
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "You already applied for this course.", "", JOptionPane.ERROR_MESSAGE);
						} else {
							pst = conn.prepareStatement("INSERT INTO applied(userName, title) VALUES(?, ?)");
							pst.setString(1, Login.login);
							pst.setString(2, titleArea[2].getText());
							pst.executeUpdate();
							
							JOptionPane.showMessageDialog(null, Login.login + " Sucessfully apllied for " + titleArea[2].getText(), "Conformation", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					pst.close();
				} catch(SQLException e1) {
					System.out.println(e1.getMessage());
				}				
			}
		});
		applyButton_2.setBounds(461, 182, 89, 23);
		panel.add(applyButton_2);
		
		JLabel admin = new JLabel("Student");
		admin.setHorizontalAlignment(SwingConstants.CENTER);
		admin.setBounds(658, 11, 145, 53);
		admin.setFont(new Font("Simplex", Font.BOLD, 25));
		layeredPane.add(admin);
		
		JLabel studentPortal = new JLabel("Portal");
		studentPortal.setHorizontalAlignment(SwingConstants.CENTER);
		studentPortal.setBounds(668, 56, 116, 38);
		studentPortal.setFont(new Font("Simplex", Font.BOLD, 25));
		layeredPane.add(studentPortal);
		
		JButton logoutButton = new JButton("Logout ");
		logoutButton.addActionListener(new ActionListener() {
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
		logoutButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		logoutButton.setBounds(680, 291, 89, 23);
		layeredPane.add(logoutButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exitButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
		exitButton.setBounds(680, 336, 89, 23);
		layeredPane.add(exitButton);
		
		// get events from database
		try {			
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM `events`");
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int count = rsmd.getColumnCount();
			
			for(int i=1; i<=count; i++) {
				pst = conn.prepareStatement("SELECT * FROM `events` WHERE id=?");
				pst.setInt(1, i);
				
				rs = pst.executeQuery();
				
				while(rs.next()) {
					idArea[i].setText(rs.getString("id"));
					titleArea[i].setText(rs.getString("title"));
					descriptionArea[i].setText(rs.getString("decription"));
				}
				pst.close();
			}
			rs.close();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
