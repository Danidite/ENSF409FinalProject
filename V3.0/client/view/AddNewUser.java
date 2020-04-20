package client.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
/**
 * This class will open a frame that will only be prompted if the user is an admin, if the 
 * user is an admin, this frame will allow them to choose whether they would like to add a new user
 * to the data base, or simply continue on to the program.
 * @author Hashir Ahmed(30070165) and Pin Long (30068063)
 * @since 20th April, 2020
 *
 */
public class AddNewUser extends JFrame {
	private static final long serialVersionUID =1L;
	private JButton newUserButton = new JButton("ADD NEW USER");
	private JButton conButton = new JButton("CONTINUE");
	
	public AddNewUser() {
		getContentPane().setBackground(new Color(255, 51, 51));
		getContentPane().setForeground(new Color(255, 51, 51));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME ADMIN");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(100, 11, 219, 92);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("WOULD YOU LIKE TO ADD A NEW USER?");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(41, 114, 365, 57);
		getContentPane().add(lblNewLabel_1);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(255, 51, 51));
		buttonPanel.setBounds(24, 204, 389, 48);
		getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		
		newUserButton.setBounds(252, 11, 137, 26);
		buttonPanel.add(newUserButton);
		
		conButton.setBounds(10, 13, 127, 26);
		buttonPanel.add(conButton);
		setSize(454,312);
	}
	/**
	 * The continue button listener, if the user simply wants to continue to the program.
	 * @param e the action listener.
	 */
	public void addConButtonListener(ActionListener e) {
		conButton.addActionListener(e);
	}
	/**
	 * The add new user button listener, if the user wants to add a new user to the database.
	 * @param e the action listener.
	 */
	public void addNewUserButtonListener(ActionListener e) {
		newUserButton.addActionListener(e);
	}
	public void display() {
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}

}
