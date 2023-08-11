package hotelmanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class RoomL extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomL frame = new RoomL();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RoomL() {

		
		
		
		  try(
	                Connection conn = DriverManager.getConnection(
	                        "jdbc:mysql://localhost:3306/hotel", "root", "");
	                Statement stmt = conn.createStatement();

	        ) {




	            String strSelect = "select * from rooms";

	            ResultSet rset = stmt.executeQuery(strSelect);

	            JButton b= new JButton("Show");
	            b.setBounds(200,150,80,30);

	    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		setBounds(100, 100, 450, 300);
	    		contentPane = new JPanel();
	    		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    		setContentPane(contentPane);
	    		getContentPane().setLayout(null);
	   
	    		
	    		DefaultListModel<String> roomList = new DefaultListModel<>();
	            while (rset.next())
	            {
	                roomList.addElement(rset.getString("number"));
	            }
	            contentPane.setLayout(null);
	            
	            JList<String> list = new JList<>(roomList);
	            list.setBounds(100,100,100,100);
	            JScrollPane scrollPane = new JScrollPane();
	            scrollPane.setBounds(98, 96, 140, 130);
	            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
	            scrollPane.setViewportView(list);
	            contentPane.add(scrollPane);
	            
	            JButton btnEdit = new JButton("Edit");
	            btnEdit.addActionListener(new ActionListener() {
	            	public void actionPerformed(ActionEvent e) {
	            		EditRoom editRoom = new EditRoom(list.getSelectedValue());
	            		editRoom.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	            		editRoom.setVisible(true);
	            	}
	            });
	            btnEdit.setBounds(248, 94, 89, 23);
	            contentPane.add(btnEdit);
	            
	            JButton btnRemove = new JButton("Remove");
	            btnRemove.addActionListener(new ActionListener() {
	            	public void actionPerformed(ActionEvent e) {
	            		 try(
	         	                Connection conn = DriverManager.getConnection(
	         	                        "jdbc:mysql://localhost:3306/hotel", "root", "");
	         	                Statement stmt = conn.createStatement();

	         	        ) {    
	            			 String strSelect = "delete from rooms where number='";
	            			 strSelect += list.getSelectedValue();
	            			 strSelect += "'";
	            			 
	            			 if(stmt.executeUpdate(strSelect) == 1) {
	            				 JOptionPane.showMessageDialog(null, "Room deleted.");
	            			 }else {
	            				 JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
	            			 }
	            			 
	            			 
	            			 
	            		 } catch(SQLException ex) {
	        	            ex.printStackTrace();
	        	        }
	            	}
	            });
	            btnRemove.setBounds(248, 128, 89, 23);
	            contentPane.add(btnRemove);
	            
	            JLabel lblHotelManagementSystem = new JLabel("Hotel Management");
	            lblHotelManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
	            lblHotelManagementSystem.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
	            lblHotelManagementSystem.setBounds(48, 41, 324, 23);
	            contentPane.add(lblHotelManagementSystem);
	            
	           
	        } catch(SQLException ex) {
	            ex.printStackTrace();
	        }
		
		
		
		

		
		
		
		
		
	}
}
