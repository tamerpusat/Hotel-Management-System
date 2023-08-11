package hotelmanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bill extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bill frame = new Bill();
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
	public Bill() {
		try(
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/hotel", "root", "");
                Statement stmt = conn.createStatement();

        ) {




            String strSelect = "select * from rooms where customer_id != 0";

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
            search.addElement(rset.getString("number"));
        }
		
		JList<String> list = new JList<>(search);
        list.setBounds(100,100,100,100);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(35, 120, 232, 110);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        scrollPane.setViewportView(list);
        contentPane.add(scrollPane);
        
        JLabel lblHotelManagementSystem = new JLabel("Hotel Management");
        lblHotelManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
        lblHotelManagementSystem.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblHotelManagementSystem.setBounds(35, 42, 355, 41);
        contentPane.add(lblHotelManagementSystem);
        
        JButton btnConfirm = new JButton("Checkout");
        btnConfirm.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Checkout checkout = new Checkout(list.getSelectedValue());
        		checkout.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        		checkout.setVisible(true);
        	}
        });
        btnConfirm.setBounds(277, 207, 147, 23);
        contentPane.add(btnConfirm);
	}
	  catch(SQLException ex) {
         ex.printStackTrace();
     }
	}

}
