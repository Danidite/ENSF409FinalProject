package client.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * This class is responsible for promting the user to enter the course that they wish to
 * add to/remove from the student of their choice. 
 * @author Hashir Ahmed (30070165) and Pin Long (30068063)
 *
 */
public class ChooseCourse extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField cName = new JTextField(10);
	private JTextField cId = new JTextField(10);
	private JTextField secNum = new JTextField(10);

	private JLabel enterCName = new JLabel("Course Name:");
	private JLabel enterCId = new JLabel("Course ID:");
	private JLabel enterSecNum = new JLabel("Section Number:");
	
	private JButton cont;
	private JButton back;
	private JPanel buttonPanel;
	private JPanel centrePanel;
	
	public ChooseCourse(int i) {
		buttonPanel = new JPanel();
		centrePanel = new JPanel();
		cont = new JButton("Continue");
		back = new JButton("Back");
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		setSize(400,200);
		
		buttonPanel.add(back);
		buttonPanel.add(Box.createHorizontalStrut(15));
		buttonPanel.add(cont);
		
		centrePanel.add(enterCName);
		centrePanel.add(cName);
		centrePanel.add(Box.createVerticalStrut(100));
		centrePanel.add(enterCId);
		centrePanel.add(cId);
		setLocationRelativeTo(null);
		if (i == 1) {
		centrePanel.add(enterSecNum);
		centrePanel.add(secNum);
		setLocationRelativeTo(null);
		}
		add("Center",centrePanel);
		add("South", buttonPanel);
		setLocationRelativeTo(null);
		pack();
		this.setVisible(true);
	}
	
	public void addContinueListener(ActionListener listenContinue) {
		cont.addActionListener(listenContinue);
	}
	public void addBackListener(ActionListener listenBack) {
		back.addActionListener(listenBack);
	}
	public String getCName() {
		return cName.getText().trim();
	}
	public String getCId() {
		return cId.getText().trim();
	}
	public String getSecNum() {
		return secNum.getText().trim();
	}
}
