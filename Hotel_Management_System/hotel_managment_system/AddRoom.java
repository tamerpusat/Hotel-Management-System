package hotelmanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AddRoom extends JFrame {

	private JPanel contentPane;
	private JTextField number;
	private JTextField description;
	private JTextField price;
	private JLabel lblHotelManagementSystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRoom frame = new AddRoom();
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
	public AddRoom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		number = new JTextField();
		number.setBounds(202, 92, 111, 20);
		contentPane.add(number);
		number.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Room Number");
		lblNewLabel.setLabelFor(number);
		lblNewLabel.setBounds(117, 95, 94, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 try(
	                        Connection conn = DriverManager.getConnection(
	                                "jdbc:mysql://localhost:3306/hotel", "root", "");
	                        Statement stmt = conn.createStatement();

	                ) {

	                    String strSelect = "insert into rooms SET number='";
	                    strSelect += number.getText();
	                    strSelect += "', customer_id=0";
	                    strSelect += ", price='";
	                    strSelect += price.getText();
	                    strSelect += "',description='";
	                    strSelect += description.getText();
	                    strSelect += "'";

	                    if(stmt.executeUpdate(strSelect) == 1){
	                        JOptionPane.showMessageDialog(null, "Room added.");
	                    }else
	                    {
	                        JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
	                    }

	                } catch(SQLException ex) {
	                    ex.printStackTrace();
	                }
				
				
				
			}
		});
		btnKaydet.setBounds(202, 194, 111, 23);
		contentPane.add(btnKaydet);
		
		description = new JTextField();
		description.setBounds(202, 127, 111, 20);
		contentPane.add(description);
		description.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setLabelFor(description);
		lblDescription.setBounds(117, 130, 67, 14);
		contentPane.add(lblDescription);
		
		price = new JTextField();
		price.setBounds(202, 160, 111, 20);
		contentPane.add(price);
		price.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(117, 163, 46, 14);
		contentPane.add(lblPrice);
		
		lblHotelManagementSystem = new JLabel("Hotel Management");
		lblHotelManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelManagementSystem.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblHotelManagementSystem.setBounds(65, 44, 291, 20);
		contentPane.add(lblHotelManagementSystem);
	}

}
