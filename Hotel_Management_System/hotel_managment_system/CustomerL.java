package hotelmanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CustomerL extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerL frame = new CustomerL();
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
	public CustomerL() {
		try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hotel", "root", "");
                Statement stmt = conn.createStatement();

        ) {




            String strSelect = "select name, surname from customers";

            ResultSet rset = stmt.executeQuery(strSelect);

            JButton b= new JButton("Show");
            b.setBounds(200,150,80,30);

    		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		setBounds(100, 100, 450, 300);
    		contentPane = new JPanel();
    		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    		setContentPane(contentPane);
    		getContentPane().setLayout(null);
   
    		
    		DefaultListModel<String> customerList = new DefaultListModel<>();
            while (rset.next())
            {
            	String s= rset.getString(1) + " " + rset.getString(2);
            	customerList.addElement(s);
            }
            contentPane.setLayout(null);
            
            JList<String> list = new JList<>(customerList);
            list.setBounds(100,100,100,100);
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(98, 96, 140, 130);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
            scrollPane.setViewportView(list);
            contentPane.add(scrollPane);
            
            JButton btnEdit = new JButton("Edit");
            btnEdit.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		EditCustomer editCustomer = new EditCustomer(list.getSelectedValue());
            		editCustomer.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            		editCustomer.setVisible(true);
            	}
            });
            btnEdit.setBounds(248, 94, 89, 23);
            contentPane.add(btnEdit);
            
            
            
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
