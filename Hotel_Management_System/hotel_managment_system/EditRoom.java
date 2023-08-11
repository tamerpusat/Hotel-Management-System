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

public class EditRoom extends JFrame {

	private JPanel contentPane;
	private JTextField number;
	private JTextField description;
	private JTextField price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditRoom frame = new EditRoom("0");
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
	public EditRoom(String roomNumber) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hotel", "root", "");
                Statement stmt = conn.createStatement();

        ) {
		
			String strSelect = "select * from rooms where number ='";
            strSelect += roomNumber;
            strSelect += "'";

            ResultSet rset = stmt.executeQuery(strSelect);
            if(rset.first()) {
		number = new JTextField(rset.getString("number"));
		number.setBounds(95, 91, 111, 20);
		contentPane.add(number);
		number.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Room Number");
		lblNewLabel.setLabelFor(number);
		lblNewLabel.setBounds(10, 94, 94, 14);
		contentPane.add(lblNewLabel);
		

		description = new JTextField(rset.getString("description"));
		description.setBounds(95, 126, 111, 20);
		contentPane.add(description);
		description.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setLabelFor(description);
		lblDescription.setBounds(10, 129, 67, 14);
		contentPane.add(lblDescription);
		
		price = new JTextField(rset.getString("price"));
		price.setBounds(95, 159, 111, 20);
		contentPane.add(price);
		price.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 162, 46, 14);
		contentPane.add(lblPrice);
		
            }
		} catch(SQLException ex) {
            ex.printStackTrace();
        }
		
		JButton btnKaydet = new JButton("Confirm");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 try(
	                        Connection conn = DriverManager.getConnection(
	                                "jdbc:mysql://localhost:3306/hotel", "root", "");
	                        Statement stmt = conn.createStatement();

	                ) {

	                    String strSelect = "update rooms SET number='";
	                    strSelect += number.getText();
	                    strSelect += "', customer_id=0";
	                    strSelect += ", price='";
	                    strSelect += price.getText();
	                    strSelect += "',description='";
	                    strSelect += description.getText();
	                    strSelect += "' where number = '";
	                    strSelect += roomNumber;
	                    strSelect += "'";
	                    if(stmt.executeUpdate(strSelect) == 1){
	                        JOptionPane.showMessageDialog(null, "Room edited.");
	                    }else
	                    {
	                        JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
	                    }

	                } catch(SQLException ex) {
	                    ex.printStackTrace();
	                }
				
				
				
			}
		});
		btnKaydet.setBounds(95, 193, 111, 23);
		contentPane.add(btnKaydet);
		
	}

}
