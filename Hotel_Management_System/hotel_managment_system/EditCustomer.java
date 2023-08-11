package hotelmanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class EditCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField surname;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditCustomer frame = new EditCustomer("Ugur Karabulut");
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
	public EditCustomer(String customerName) {
		
		try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hotel", "root", "");
                Statement stmt = conn.createStatement();

        ) {
			
			String[] parts = customerName.split(" ");
			String name_ = parts[0];
			String surname_ = parts[1];
			String strSelect = "select * from customers where name ='";
            strSelect += name_;
            strSelect += "' and surname='";
            strSelect += surname_;
            strSelect += "'";

            ResultSet rset = stmt.executeQuery(strSelect);
            if(rset.first()) {
		
				
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 450, 300);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				
				JLabel lblHotelManagementSystem = new JLabel("Hotel Management");
				lblHotelManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
				lblHotelManagementSystem.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
				lblHotelManagementSystem.setBounds(10, 27, 414, 21);
				contentPane.add(lblHotelManagementSystem);
				
				JLabel lblName = new JLabel("Name");
				lblName.setBounds(60, 115, 125, 14);
				contentPane.add(lblName);
				
				JLabel lblSurname = new JLabel("Surname");
				lblSurname.setBounds(60, 140, 86, 14);
				contentPane.add(lblSurname);
				
				JLabel lblPhone = new JLabel("Phone");
				lblPhone.setBounds(60, 165, 97, 14);
				contentPane.add(lblPhone);
				
				JLabel lblVisitDate = new JLabel("Visit Date");
				lblVisitDate.setBounds(60, 190, 97, 14);
				contentPane.add(lblVisitDate);
				
				name = new JTextField(rset.getString("name"));
				name.setBounds(156, 112, 185, 20);
				contentPane.add(name);
				name.setColumns(10);
				
				surname = new JTextField(rset.getString("surname"));
				surname.setBounds(156, 137, 185, 20);
				contentPane.add(surname);
				surname.setColumns(10);
				
				textField = new JTextField(rset.getString("phone"));
				textField.setBounds(156, 164, 185, 20);
				contentPane.add(textField);
				textField.setColumns(10);
				
				textField_1 = new JTextField(rset.getString("visitdate"));
				textField_1.setBounds(156, 189, 86, 20);
				contentPane.add(textField_1);
				textField_1.setColumns(10);
				
				JButton btnSve = new JButton("Save");
				btnSve.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						 try(
			                        Connection conn = DriverManager.getConnection(
			                                "jdbc:mysql://localhost:3306/hotel", "root", "");
			                        Statement stmt = conn.createStatement();

			                ) {

			                    String strSelect1 = "update customers SET name='";
			                    strSelect1 += name.getText();
			                    strSelect1 += "', surname='";
			                    strSelect1 += surname.getText();
			                    strSelect1 += "', phone='";
			                    strSelect1 += textField.getText();
			                    strSelect1 += "', visitdate='";
			                    strSelect1 += textField_1.getText();
			                    strSelect1 += "'";
			                    strSelect1 += " where name ='";
			                    strSelect1 += name_;
			                    strSelect1 += "' and surname='";
			                    strSelect1 += surname_;
			                    strSelect1 += "'";

			                    if(stmt.executeUpdate(strSelect1) == 1){
			                        JOptionPane.showMessageDialog(null, "Customer edited.");
			                    }else
			                    {
			                        JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
			                    }

			                } catch(SQLException ex) {
			                    ex.printStackTrace();
			                }
						
						
						
					}
				});
				btnSve.setBounds(252, 188, 89, 23);
				contentPane.add(btnSve);
				
				
				
				
				
            }
		
		} catch(SQLException ex) {
            ex.printStackTrace();
        }
		
	}

}
