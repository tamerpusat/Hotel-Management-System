package hotelmanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.sql.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login_ extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_ frame = new Login_();
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
	public Login_() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHotelManagementSystem = new JLabel("Hotel Management");
		lblHotelManagementSystem.setBounds(73, 53, 213, 21);
		lblHotelManagementSystem.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		contentPane.add(lblHotelManagementSystem);
		
		textField = new JTextField();
		textField.setBounds(182, 100, 104, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(73, 102, 119, 17);
		contentPane.add(lblUsername);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 128, 104, 21);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(73, 131, 99, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try(
	                        Connection conn = DriverManager.getConnection(
	                                "jdbc:mysql://localhost:3306/hotel", "root", "");
	                        Statement stmt = conn.createStatement();

	                ) {

	                    String strSelect = "select * from admins where username='";
	                    strSelect += textField.getText();
	                    strSelect += "' and password ='";
	                    strSelect += passwordField.getText();
	                    strSelect += "'";

	                    ResultSet rset = stmt.executeQuery(strSelect);

	                    if(rset.first()) {
	                        JOptionPane.showMessageDialog(null, "Login Successful ");
	                        stmt.close();
	                        conn.close();
	                        dispose();

	                        Panel panel = new Panel();
	                        panel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	                        panel.setVisible(true);
	                    }else
	                    {
	                        JOptionPane.showMessageDialog(null, "Incorrect username or password", "Error", JOptionPane.WARNING_MESSAGE);
	                        return;
	                    }

	                } catch(SQLException ex) {
	                    ex.printStackTrace();
	                }
			}
		});
		btnLogin.setBounds(182, 164, 104, 23);
		contentPane.add(btnLogin);
	}
}
