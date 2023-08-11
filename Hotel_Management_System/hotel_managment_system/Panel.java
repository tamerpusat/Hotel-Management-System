package hotelmanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Panel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel frame = new Panel();
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
	public Panel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddRoom = new JButton("Add Room");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRoom addroom = new AddRoom();
				addroom.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				addroom.setVisible(true);
			}
		});
		btnAddRoom.setBounds(24, 91, 173, 23);
		contentPane.add(btnAddRoom);
		
		JButton btnEditRoom = new JButton("Edit Room");
		btnEditRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomL roomList = new RoomL();
				roomList.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				roomList.setVisible(true);
			}
		});
		btnEditRoom.setBounds(24, 125, 173, 23);
		contentPane.add(btnEditRoom);
		
		JButton btnEditCustomer = new JButton("Edit Customer");
		btnEditCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerL ecustomer = new CustomerL();
				ecustomer.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				ecustomer.setVisible(true);			
			}
		});
		btnEditCustomer.setBounds(231, 159, 173, 23);
		contentPane.add(btnEditCustomer);
		
		JButton btnNewButton = new JButton("Reserve Room");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reserve reserve = new Reserve();
				reserve.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				reserve.setVisible(true);
			}
		});
		btnNewButton.setBounds(24, 193, 173, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Search Avaible Room");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SrcAvailable searcha = new SrcAvailable();
				searcha.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				searcha.setVisible(true);			
				
			}
		});
		btnNewButton_1.setBounds(24, 159, 173, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Search Customer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SrcCustomer searchc = new SrcCustomer();
				searchc.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				searchc.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(231, 125, 173, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Bill");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bill bill = new Bill();
				bill.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				bill.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(231, 91, 173, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Exit");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_4.setBounds(231, 193, 173, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel lblHotelManagementSystem = new JLabel("Hotel Management");
		lblHotelManagementSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelManagementSystem.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblHotelManagementSystem.setBounds(20, 33, 384, 23);
		contentPane.add(lblHotelManagementSystem);
	}

}
