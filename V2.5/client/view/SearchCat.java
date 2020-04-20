package client.view;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
/**
 * 
 * @author Hashir Ahmed (30070165) and Pin Long (30068063)
 * this class deals with the search catalogue button, it will ask the user which course they
 * are looking for, and look to see if that course does exist.
 */
public class SearchCat {
	private JTextField cName = new JTextField(5);
    private JTextField cId = new JTextField(5);
    private int result;
	public SearchCat() {
	      JPanel myPanel = new JPanel();
	      myPanel.setBackground(Color.RED);
	      myPanel.setForeground(Color.WHITE);
	      JLabel label = new JLabel("Course Name:");
	      label.setForeground(Color.WHITE);
	      myPanel.add(label);
	      myPanel.add(cName);
	      myPanel.add(Box.createHorizontalStrut(15)); 
	      JLabel label_1 = new JLabel("Course ID:");
	      label_1.setForeground(Color.WHITE);
	      myPanel.add(label_1);
	      myPanel.add(cId);
	      
	      setResult(JOptionPane.showConfirmDialog(null, myPanel, "Please Enter the name and Id of the course", JOptionPane.OK_CANCEL_OPTION));
	}
	
	public String getCourseName() {
		return cName.getText().trim();
	}
	public String getCourseID() {
		return cId.getText().trim();
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}
