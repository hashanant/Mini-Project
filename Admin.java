// package tec181;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class Admin extends JFrame {
	
	static final int EVENTS = 3;
	static String title;
	
	private JPanel contentPane;

	public Admin() {
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
		
		idArea[1] = new JTextArea();
		idArea[1].setEditable(false);
		idArea[1].setText("1");
		idArea[1].setFont(new Font("Monospaced", Font.BOLD, 20));
		idArea[1].setBounds(10, 11, 35, 32);
		panel.add(idArea[1]);
		
		titleArea[1] = new JTextArea();
		titleArea[1].setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		titleArea[1].setBounds(62, 11, 284, 32);
		panel.add(titleArea[1]);
		
		descriptionArea[1] = new JTextArea();
		descriptionArea[1].setBounds(10, 54, 413, 84);
		panel.add(descriptionArea[1]);
		
		JButton addEvent_1 = new JButton("Add Event");
		addEvent_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					PreparedStatement pst = conn.prepareStatement("INSERT INTO events(id, title, decription) VALUES(1, ?, ?)");
					
					pst.setString(1, titleArea[1].getText());
					pst.setString(2, descriptionArea[1].getText());
					
					if(titleArea[1].getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "Event Title can not be empty", "Title is empty", JOptionPane.WARNING_MESSAGE);
					} else {
						pst.executeUpdate();						
						JOptionPane.showMessageDialog(null, "Event Added");
					}
					pst.close();
				} catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		addEvent_1.setBounds(452, 12, 121, 23);
		panel.add(addEvent_1);
		
		JButton updateEvent_1 = new JButton("Update Event");
		updateEvent_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement pst = conn.prepareStatement("UPDATE `events` SET title=?, decription=? WHERE `events`.`id` = 1");
					pst.setString(1, titleArea[1].getText());
					pst.setString(2, descriptionArea[1].getText());
					
					if(titleArea[1].getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "Event Title can not be empty", "Title is empty", JOptionPane.WARNING_MESSAGE);
					} else {
						pst.executeUpdate();						
						JOptionPane.showMessageDialog(null, "Event has been Updated");
					}
					pst.close();
				} catch(SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		updateEvent_1.setBounds(452, 46, 121, 23);
		panel.add(updateEvent_1);
		
		JButton deleteEvent_1 = new JButton("Delete Event");
		deleteEvent_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String deleteMsg = titleArea[1].getText() + " Deleted Sucessfully";
					
					if(titleArea[1].getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "There is no event. You can not delete.", "Title is empty", JOptionPane.WARNING_MESSAGE);
					} else {
						PreparedStatement pst = conn.prepareStatement("DELETE FROM `events` WHERE `events`.`id` = 1");
						pst.executeUpdate();
						
						pst = conn.prepareStatement("DELETE FROM `applied` WHERE `applied`.`title`=?");
						pst.setString(1, titleArea[1].getText());
						pst.executeUpdate();
						
						titleArea[1].setText("");
						descriptionArea[1].setText("");
						
						JOptionPane.showMessageDialog(null, deleteMsg);					
						pst.close();
					}	
				} catch(SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		deleteEvent_1.setBounds(452, 80, 121, 23);
		panel.add(deleteEvent_1);
		
		JButton parButton_1 = new JButton("Participants");
		parButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(titleArea[1].getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "There is no event. Please create new event.", "Title is empty", JOptionPane.WARNING_MESSAGE);
				} else {
					title = titleArea[1].getText();
					try {
						Participants frame = new Participants();
						frame.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}	
			}
		});
		parButton_1.setBounds(451, 114, 122, 23);
		panel.add(parButton_1);
		
		idArea[2] = new JTextArea();
		idArea[2].setEditable(false);
		idArea[2].setText("2");
		idArea[2].setFont(new Font("Monospaced", Font.BOLD, 20));
		idArea[2].setBounds(10, 149, 35, 32);
		panel.add(idArea[2]);
		
		titleArea[2] = new JTextArea();
		titleArea[2].setFont(new Font("Microsoft YaHei UI", Font.BOLD, 15));
		titleArea[2].setBounds(62, 149, 284, 32);
		panel.add(titleArea[2]);
		
		descriptionArea[2] = new JTextArea();
		descriptionArea[2].setBounds(10, 192, 413, 84);
		panel.add(descriptionArea[2]);
		
		JButton addEvent_2 = new JButton("Add Event");
		addEvent_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					PreparedStatement pst = conn.prepareStatement("INSERT INTO events(id, title, decription) VALUES(2, ?, ?)");
					pst.setString(1, titleArea[2].getText());
					pst.setString(2, descriptionArea[2].getText());
					
					if(titleArea[2].getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "Event Title can not be empty", "Title is empty", JOptionPane.WARNING_MESSAGE);
					} else {
						pst.executeUpdate();						
						JOptionPane.showMessageDialog(null, "Event Added");
					}					
					pst.close();
				} catch(SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		addEvent_2.setBounds(452, 164, 121, 23);
		panel.add(addEvent_2);
		
		JButton updateEvent_2 = new JButton("Update Event");
		updateEvent_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement pst = conn.prepareStatement("UPDATE `events` SET title=?, decription=? WHERE `events`.`id` = 2");
					pst.setString(1, titleArea[2].getText());
					pst.setString(2, descriptionArea[2].getText());
					
					if(titleArea[2].getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "Event Title can not be empty", "Title is empty", JOptionPane.WARNING_MESSAGE);
					} else {
						pst.executeUpdate();						
						JOptionPane.showMessageDialog(null, "Event has been Updated");
					}					
					pst.close();
				} catch(SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		updateEvent_2.setBounds(452, 198, 121, 23);
		panel.add(updateEvent_2);
		
		JButton deleteEvent_2 = new JButton("Delete Event");
		deleteEvent_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String deleteMsg = titleArea[2].getText() + " Deleted Sucessfully";
					
					if(titleArea[2].getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "There is no event. You can not delete.", "Title is empty", JOptionPane.WARNING_MESSAGE);
					} else {
						PreparedStatement pst = conn.prepareStatement("DELETE FROM `events` WHERE `events`.`id` = 2");
						pst.executeUpdate();
						
						pst = conn.prepareStatement("DELETE FROM `applied` WHERE `applied`.`title`=?");
						pst.setString(1, titleArea[2].getText());
						pst.executeUpdate();
						
						titleArea[2].setText("");
						descriptionArea[2].setText("");
						
						JOptionPane.showMessageDialog(null, deleteMsg, "Delete Event", JOptionPane.INFORMATION_MESSAGE);
						pst.close();
					}
				} catch(SQLException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		deleteEvent_2.setBounds(452, 232, 121, 23);
		panel.add(deleteEvent_2);
		
		JButton parButton_2 = new JButton("Participants");
		parButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(titleArea[2].getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "There is no event. Please create new event.", "Title is empty", JOptionPane.WARNING_MESSAGE);
				} else {
					title = titleArea[2].getText();
					try {
						Participants frame = new Participants();
						frame.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		parButton_2.setBounds(452, 266, 121, 23);
		panel.add(parButton_2);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(597, 0, 17, 319);
		panel.add(scrollBar);
		
		JLabel admin = new JLabel("Admin");
		admin.setHorizontalAlignment(SwingConstants.CENTER);
		admin.setBounds(658, 11, 145, 53);
		admin.setFont(new Font("Simplex", Font.BOLD, 25));
		layeredPane.add(admin);
		
		JLabel adminPortal = new JLabel("Portal");
		adminPortal.setHorizontalAlignment(SwingConstants.CENTER);
		adminPortal.setBounds(668, 56, 116, 38);
		adminPortal.setFont(new Font("Simplex", Font.BOLD, 25));
		layeredPane.add(adminPortal);
		
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
