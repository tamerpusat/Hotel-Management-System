package hotelmanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;

public class Checkout extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checkout frame = new Checkout("105");
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
	public Checkout(String number) {
		
		try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hotel", "root", "");
                Statement stmt = conn.createStatement();

        ) {
			
			String strSelect = "select * from rooms where number ='";
            strSelect += number;
            strSelect += "'";
            
            ResultSet rset = stmt.executeQuery(strSelect);
            
            if(rset.first()) {
            	String myString2 = rset.getString("price");
    			String strSelect2 = "select * from customers where id =";
        		String myString3 = rset.getString("customer_id");
        		int y = Integer.parseInt(myString3);
        		
                strSelect2 += y;
                strSelect2 += "";
                
                ResultSet rset2 = stmt.executeQuery(strSelect2);
                
                if(rset2.first()) {
            	
        		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		setBounds(100, 100, 450, 300);
        		contentPane = new JPanel();
        		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        		setContentPane(contentPane);
        		contentPane.setLayout(null);
        		
        		JLabel lblName = new JLabel("Name");
        		lblName.setBounds(27, 101, 82, 14);
        		contentPane.add(lblName);
        		
        		JLabel lblSurname = new JLabel("Surname");
        		lblSurname.setBounds(27, 126, 82, 14);
        		contentPane.add(lblSurname);
        		
        		JLabel lblPhone = new JLabel("Phone");
        		lblPhone.setBounds(27, 151, 82, 14);
        		contentPane.add(lblPhone);
        		
        		JLabel lblVisitedDate = new JLabel("Visited Date");
        		lblVisitedDate.setBounds(27, 176, 82, 14);
        		contentPane.add(lblVisitedDate);
        		
        		JLabel name = new JLabel(rset2.getString("name"));
        		name.setBounds(119, 101, 82, 14);
        		contentPane.add(name);
        		
        		JLabel surname = new JLabel(rset2.getString("surname"));
        		surname.setBounds(119, 126, 82, 14);
        		contentPane.add(surname);
        		
        		JLabel phone = new JLabel(rset2.getString("phone"));
        		phone.setBounds(116, 151, 85, 14);
        		contentPane.add(phone);
        		
        		JLabel visited = new JLabel(rset2.getString("visitdate"));
        		visited.setBounds(119, 176, 82, 14);
        		contentPane.add(visited);
        		

        		
        		JLabel lblTotal = new JLabel("Total");
        		lblTotal.setBounds(27, 201, 46, 14);
        		contentPane.add(lblTotal);
        		
        		String myString = rset2.getString("visitdate");
        		int v = Integer.parseInt(myString);
        		
           		
        		int x = Integer.parseInt(myString2);
        		
        		int totalprice = x * v;
        			
        		String totalpriceString = Integer.toString(totalprice);
    
        		
        		JLabel total = new JLabel(totalpriceString);
        		total.setBounds(116, 201, 46, 14);
        		contentPane.add(total);
        		
        		
        		JButton btnConfirmCheckout = new JButton("Confirm Checkout");
        		btnConfirmCheckout.setBounds(211, 197, 213, 23);
        		contentPane.add(btnConfirmCheckout);
        		btnConfirmCheckout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						 try(
			                        Connection conn = DriverManager.getConnection(
			                                "jdbc:mysql://localhost:3306/hotel", "root", "");
			                        Statement stmt = conn.createStatement();

			                ) {

			                    String strSelect1 = "update rooms SET customer_id=0";
			                    strSelect1 += " where number ='";
			                    strSelect1 += number;
			                    strSelect1 += "'";
			                    
			                    String strSelect2 = "delete from customers where id=";
		            			 strSelect2 += y;
		            			 strSelect2 += "";
		            			 


			                    if(stmt.executeUpdate(strSelect1) == 1 && stmt.executeUpdate(strSelect2) == 1){
			                        JOptionPane.showMessageDialog(null, "Checkout confirmed room is empty and customer deleted.");
			                    }else
			                    {
			                        JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
			                    }

			                } catch(SQLException ex) {
			                    ex.printStackTrace();
			                }
						
						
						
					}
				});
        		
        		
        		JLabel lblHotelManagementSystem = new JLabel("Hotel Management");
        		lblHotelManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
        		lblHotelManagementSystem.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        		lblHotelManagementSystem.setBounds(27, 48, 397, 23);
        		contentPane.add(lblHotelManagementSystem);
        		
                }else {
                	
                }
            }
		

		} catch(SQLException ex) {
            ex.printStackTrace();
        }
		
	}

}
