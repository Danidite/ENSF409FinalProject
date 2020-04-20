package client.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class MakeNewUser extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField UsernameResult;
	private JTextField passwordResult;
	private JButton finishButton = new JButton("Finish");
	private JButton backButton = new JButton("Back");
	
	public MakeNewUser() {
		getContentPane().setForeground(Color.WHITE);
		getContentPane().setBackground(Color.RED);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PLEASE ENTER THE NEW USERNAME AND PASSWORD");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(20, 32, 466, 60);
		getContentPane().add(lblNewLabel);
		
		JPanel InfoPanel = new JPanel();
		InfoPanel.setBackground(Color.YELLOW);
		InfoPanel.setBounds(30, 103, 442, 117);
		getContentPane().add(InfoPanel);
		InfoPanel.setLayout(null);
		
		JLabel Username = new JLabel("Username");
		Username.setFont(new Font("Times New Roman", Font.BOLD, 12));
		Username.setHorizontalAlignment(SwingConstants.CENTER);
		Username.setBounds(71, 22, 134, 27);
		InfoPanel.add(Username);
		
		UsernameResult = new JTextField();
		UsernameResult.setBounds(235, 25, 96, 20);
		InfoPanel.add(UsernameResult);
		UsernameResult.setColumns(10);
		
		JLabel Password = new JLabel("Password");
		Password.setHorizontalAlignment(SwingConstants.CENTER);
		Password.setFont(new Font("Times New Roman", Font.BOLD, 12));
		Password.setBounds(71, 79, 134, 27);
		InfoPanel.add(Password);
		
		passwordResult = new JTextField();
		passwordResult.setBounds(235, 82, 96, 20);
		InfoPanel.add(passwordResult);
		passwordResult.setColumns(10);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.RED);
		buttonPanel.setBounds(30, 231, 442, 34);
		getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		finishButton.setBounds(343, 11, 89, 23);
		buttonPanel.add(finishButton);
		
		backButton.setBounds(10, 11, 89, 23);
		buttonPanel.add(backButton);
		setSize(508,311);
	}
	public String getPasswordResult() {
		return passwordResult.getText().trim();
	}
	public String getUserResult() {
		return UsernameResult.getText().trim();
	}
	public void addFinishButtonListener(ActionListener e) {
		finishButton.addActionListener(e);
	}
	public void addBackButtonListener(ActionListener e) {
		backButton.addActionListener(e);
	}
	public void display() {
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}
}
