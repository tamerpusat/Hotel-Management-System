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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Reserve extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField surname;
	private JTextField phone;
	private JTextField visitdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reserve frame = new Reserve();
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
	public Reserve() {
		
		try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hotel", "root", "");
                Statement stmt = conn.createStatement();

        ) {




            String strSelect = "select * from rooms where customer_id = 0";

            ResultSet rset = stmt.executeQuery(strSelect);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		DefaultListModel<String> search = new DefaultListModel<>();
		
		while (rset.next())
        {
            search.addElement(rset.getString("number"));
        }
		contentPane.setLayout(null);
		
		JList<String> list = new JList<>(search);
        list.setBounds(100,100,100,100);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(38, 99, 140, 128);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scrollPane.setViewportView(list);
        contentPane.add(scrollPane);
	

		
		JLabel lblCustomerName = new JLabel("Name");
		lblCustomerName.setBounds(213, 101, 76, 14);
		contentPane.add(lblCustomerName);
		
		name = new JTextField();
		name.setBounds(303, 98, 99, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Surname");
		lblNewLabel.setBounds(213, 126, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(213, 151, 76, 14);
		contentPane.add(lblPhone);
		
		JLabel lblDays = new JLabel("Visit Date");
		lblDays.setBounds(213, 176, 76, 14);
		contentPane.add(lblDays);
		
		surname = new JTextField();
		surname.setBounds(303, 123, 99, 20);
		contentPane.add(surname);
		surname.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(303, 148, 99, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		visitdate = new JTextField();
		visitdate.setBounds(303, 173, 99, 20);
		contentPane.add(visitdate);
		visitdate.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setBounds(303, 204, 99, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try(
                        Connection conn = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/hotel", "root", "");
                        Statement stmt = conn.createStatement();

                ) {

                    String strSelect = "insert into customers SET name='";
                    strSelect += name.getText();
                    strSelect += "', surname='";
                    strSelect += surname.getText();
                    strSelect += "', phone='";
                    strSelect += phone.getText();
                    strSelect += "', visitdate='";
                    strSelect += visitdate.getText();
                    strSelect += "', roomnumber='";
                    strSelect += list.getSelectedValue();
                    strSelect += "'";
                    
                    if(stmt.executeUpdate(strSelect) == 1) {
                    	JOptionPane.showMessageDialog(null, "Customer Added.");
                    }else
                    {
                        JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    
                    String strSelect1 = "select * from customers where name='";
                    strSelect1 += name.getText();
                    strSelect1 += "' and surname='";
                    strSelect1 += surname.getText();
                    strSelect1 += "' and phone='";
                    strSelect1 += phone.getText();
                    strSelect1 += "' and visitdate='";
                    strSelect1 += visitdate.getText();
                    strSelect1 += "'";
                    
                    
                    
                    ResultSet rset = stmt.executeQuery(strSelect1);
                    
                    if(rset.first()) {
                    
                    String strSelect2 = "update rooms SET customer_id='";
                    strSelect2 += rset.getString("id");
                    strSelect2 += "' WHERE number='";
                    strSelect2 += list.getSelectedValue();
                    strSelect2 += "'";
                  
                    
                    if(stmt.executeUpdate(strSelect2) == 1){
                        JOptionPane.showMessageDialog(null, "Room reserved.");
                    }else
                    {
                        JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                    }
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
				
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel lblHotelManagementSystem = new JLabel("Hotel Management");
		lblHotelManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelManagementSystem.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblHotelManagementSystem.setBounds(38, 30, 364, 44);
		contentPane.add(lblHotelManagementSystem);
		}
		  catch(SQLException ex) {
	         ex.printStackTrace();
	     }
	}

}
