package hotelmanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SrcCustomer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SrcCustomer frame = new SrcCustomer();
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
	public SrcCustomer() {
		try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hotel", "root", "");
                Statement stmt = conn.createStatement();

        ) {




            String strSelect = "select name, surname, roomnumber from customers";

            ResultSet rset = stmt.executeQuery(strSelect);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
				
		
		DefaultListModel<String> search = new DefaultListModel<>();

		while (rset.next())
        {
			
			String s= rset.getString(1) + " " + rset.getString(2) + " " + rset.getString(3);
            search.addElement(s);
        }
		
		JList<String> list = new JList<>(search);
        list.setBounds(100,100,100,100);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(44, 102, 344, 148);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scrollPane.setViewportView(list);
        contentPane.add(scrollPane);
        
        JLabel lblHotelManagementSystem = new JLabel("Hotel Management");
        lblHotelManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
        lblHotelManagementSystem.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblHotelManagementSystem.setBounds(44, 30, 344, 43);
        contentPane.add(lblHotelManagementSystem);
	}
	  catch(SQLException ex) {
         ex.printStackTrace();
     }
	}

}
