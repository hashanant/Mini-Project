// package tec181;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Participants extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public Participants() {
		Connection conn = Database.dbConnect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		closeButton.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		closeButton.setBounds(166, 312, 89, 23);
		contentPane.add(closeButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(Admin.title);
		textArea.setFont(new Font("Monospaced", Font.BOLD, 20));
		textArea.setBounds(20, 11, 314, 37);
		contentPane.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 59, 389, 228);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Index", "First Name", "Last Name"
			}
		));
		
		// get data from applied students to table
		try {
			PreparedStatement pst = conn.prepareStatement("SELECT * from `applied` WHERE `applied`.title=?");
			pst.setString(1, Admin.title);
			
			ResultSet rs = pst.executeQuery();
						
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			
			int count = 1;
			
			while(rs.next()) {
				String userName = rs.getString("userName");
				
				PreparedStatement state = conn.prepareStatement("SELECT * from `signup` WHERE `signup`.userName=?");
				state.setString(1, userName);
				
				ResultSet set = state.executeQuery();

				while(set.next()) {
					model.addRow(new Object[]{count, set.getString("fName"), set.getString("lName")});	
				}
				count++;
				state.close();
				set.close();
			}
			pst.close();
			rs.close();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
