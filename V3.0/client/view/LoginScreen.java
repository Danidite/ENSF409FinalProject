package client.view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import java.awt.Color;
/**
 * this is the class that creates the login screen. On this, the user will be prompted to enter
 * their username and password. If this information exists in the database, then they will be allowed to 
 * proceed, else they will be given an error. if they are an admin, they will be taken to a different screen. 
 * @author Hashir Ahmed (30070165) and Pin Long (30068063).
 *
 */
public class LoginScreen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField usernameResult;
	private JTextField passwordResult;
	private JButton cancelButton = new JButton("Cancel");
	private JButton loginButton = new JButton("Login");
	
	public LoginScreen() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.RED);
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.RED);
		buttonPanel.setBounds(24, 180, 387, 72);
		getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		
		cancelButton.setBounds(38, 24, 89, 23);
		buttonPanel.add(cancelButton);
		
		loginButton.setBounds(249, 24, 89, 23);
		buttonPanel.add(loginButton);
		
		JPanel getInfoPanel = new JPanel();
		getInfoPanel.setBackground(new Color(255, 255, 102));
		getInfoPanel.setBounds(186, 22, 225, 147);
		getContentPane().add(getInfoPanel);
		getInfoPanel.setLayout(null);
		
		usernameResult = new JTextField();
		usernameResult.setColumns(10);
		usernameResult.setBounds(102, 27, 96, 20);
		getInfoPanel.add(usernameResult);
		
		JLabel userName = new JLabel("Username");
		userName.setFont(new Font("Tahoma", Font.BOLD, 13));
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		userName.setBounds(10, 29, 82, 18);
		getInfoPanel.add(userName);
		
		passwordResult = new JTextField();
		passwordResult.setBounds(102, 91, 96, 20);
		getInfoPanel.add(passwordResult);
		passwordResult.setColumns(10);
		
		JLabel password = new JLabel("Password");
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("Tahoma", Font.BOLD, 13));
		password.setBounds(10, 94, 82, 18);
		getInfoPanel.add(password);
		
		JLabel image = new JLabel("\r\n");
		image.setIcon(new ImageIcon("Images\\UofCLogo.jpg"));
		image.setBounds(44, 54, 103, 85);
		getContentPane().add(image);
		setSize(475,324);
	}
	/**
	 * this is the listener function for the cancel button.
	 * @param e the action listener.
	 */
	public void addCancelButtonListener(ActionListener e) {
		cancelButton.addActionListener(e);
	}
	/**
	 * this is the listener function for the login button
	 * @param e the action listener.
	 */
	public void addLoginButtonListener(ActionListener e) {
		loginButton.addActionListener(e);
	}
	/**
	 * this function will return the password the user entered.
	 * @return the password entered
	 */
	public String getPasswordResult() {
		return passwordResult.getText().trim();
	}
	/**
	 * this function will return the username entered by the user.
	 * @return the username entered
	 */
	public String getUserResult() {
		return usernameResult.getText().trim();
	}
	/**
	 * this function will display this frame when called.
	 */
	public void display() {
		setLocationRelativeTo(null);
		setVisible(true);
	}
	/**
	 * this function will close, or set the visibility of the frame off.
	 */
	public void close() {
		setVisible(false);
	}
}
